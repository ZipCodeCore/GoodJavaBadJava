import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBall extends JPanel implements ActionListener {
    
    // Ball properties
    private int ballX = 50;   // Initial x position
    private int ballY = 50;   // Initial y position
    private int ballDiameter = 20;
    private int ballDeltaX = 2;  // Change in x (velocity)
    private int ballDeltaY = 2;  // Change in y (velocity)

    public BouncingBall() {
        Timer timer = new Timer(10, this); // Refresh every 10ms
        timer.start();
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball Example");
        BouncingBall bouncingBall = new BouncingBall();
        frame.add(bouncingBall);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
