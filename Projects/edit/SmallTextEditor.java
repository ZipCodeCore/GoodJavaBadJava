import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SmallTextEditor extends JFrame {

    private JEditorPane editorPane;

    public SmallTextEditor() {
        super("Text Editor");

        editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(editorPane.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmallTextEditor::new);
    }
}
