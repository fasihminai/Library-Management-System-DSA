import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {

    public SplashScreen() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Library System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 42, 86));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("DSA Project - Java Swing");
        sub.setForeground(Color.LIGHT_GRAY);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton start = new JButton("GET STARTED");
        start.setFont(new Font("Arial", Font.BOLD, 16));
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        start.addActionListener(e -> {
            new MainGUI();
            dispose();
        });

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(sub);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(start);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SplashScreen();
    }
}