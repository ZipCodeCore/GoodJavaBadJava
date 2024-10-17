import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BouncingBallInteractive extends JPanel implements ActionListener {
    
    // Ball properties
    private int ballX = 50;   // Initial x position
    private int ballY = 50;   // Initial y position
    private int ballDiameter = 20;
    private int ballDeltaX = 2;  // Change in x (velocity)
    private int ballDeltaY = 2;  // Change in y (velocity)
    
    private Timer timer;
    private boolean isBouncing = true;  // Control flag for pausing and resuming

    public BouncingBallInteractive() {
        timer = new Timer(10, this); // Refresh every 10ms
        timer.start();
        
        // Add key listener to toggle bouncing
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    toggleBouncing();
                }
            }
        });
        setFocusable(true);  // Necessary for key events
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Clear the frame and draw the ball
        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isBouncing) {
            // Update ball position
            ballX += ballDeltaX;
            ballY += ballDeltaY;
            
            // Check for collision with the edges of the panel
            if (ballX <= 0 || ballX + ballDiameter >= getWidth()) {
                ballDeltaX *= -1;  // Reverse direction on X-axis
            }
            
            if (ballY <= 0 || ballY + ballDiameter >= getHeight()) {
                ballDeltaY *= -1;  // Reverse direction on Y-axis
            }
            
            // Repaint the panel
            repaint();
        }
    }

    // Method to toggle the bouncing on and off
    public void toggleBouncing() {
        isBouncing = !isBouncing;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball Example");
        BouncingBallInteractive bouncingBall = new BouncingBallInteractive();
        frame.add(bouncingBall);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
