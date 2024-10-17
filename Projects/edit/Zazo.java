//package rocks.zipcode.zazo;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.event.DocumentEvent;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class FileTreeModel extends DefaultTreeModel {

    public FileTreeModel(File root) {
        super(new FileTreeNode(root));
        buildTree(root, (FileTreeNode) getRoot());
    }

    private void buildTree(File file, FileTreeNode parent) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                FileTreeNode node = new FileTreeNode(child);
                parent.add(node);
                buildTree(child, node);
            }
        }
    }
}

class FileTreeNode extends DefaultMutableTreeNode {
    private File file;
    private String name;

    public FileTreeNode(File file) {
        super(file);
        this.file = file;
        this.name = file.getName();
    }

    public File getFile() {
        return file;
    }

    public String toString() {
        return name;
    }
}

public class Zazo extends JPanel implements TreeSelectionListener, DocumentListener {
    private JEditorPane editorPane;
    private JTree tree;
    private static boolean DEBUG = false;
    private String currentFileName = "";
    private boolean hasChanges = false;

    // Optionally play with line styles. Possible values are
    // "Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    // Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public Zazo() {
        super(new GridLayout(1, 0));

        TreeModel model = new FileTreeModel(new File(System.getProperty("user.dir")));
        tree = new JTree(model);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        // Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        // Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        // Create the editing pane.
        editorPane = new JTextPane();
        editorPane.setEditable(true);
        editorPane.setFont(new FontUIResource("Monospaced", Font.PLAIN, 16));
        editorPane.setMargin(new Insets(5, 5, 5, 5));

        
        // put editor in scroll pane
        JScrollPane editorView = new JScrollPane(editorPane);

        // Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(editorView);

        Dimension minimumSize = new Dimension(250, 100);
        editorView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(250);
        splitPane.setPreferredSize(new Dimension(1024, 600));

        // Add the split pane to this panel.
        add(splitPane);
    }

    public void insertUpdate(DocumentEvent e) {
        System.out.println("changing insert");
        hasChanges = true;
    }
    public void removeUpdate(DocumentEvent e) {
        System.out.println("changing remove");
        hasChanges = true;
    }
    public void changedUpdate(DocumentEvent e) {
        //System.out.println("changing changed");
        //hasChanges = true;
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null)
            return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            displayFile(((File) nodeInfo).toURI().toString());
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private void displayFile(String url) {
        try {
            if (url != null) {
                saveFileIfChanged(url);
                editorPane.setPage(url);
                editorPane.getDocument().addDocumentListener(this);        
            } else { // null url
                editorPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null file name.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }

    private void saveFileIfChanged(String url) {
        if (hasChanges) {
            System.out.println("Saving file: " + currentFileName);
            try  {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(new URI(currentFileName))));
                System.out.println("File Open For Writing: " + currentFileName);
                writer.write(editorPane.getText());
                hasChanges = false;
                writer.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("No changes to save.");
        }
        currentFileName = url;
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        // Create and set up the window.
        JFrame frame = new JFrame("Zazo - autosaving on Focus Change");

        // BUG: with the way autosave is implemented, EXIT_ON_CLOSE will NOT save an pending changes,
        // as the focus change event will not be triggered.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content to the window.
        frame.add(new Zazo());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
