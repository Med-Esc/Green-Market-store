package Home;

import java.awt.*;
import javax.swing.*;

public class Home extends JFrame {
    
    public Home() {
        setTitle("GreenMarket - Online Plant Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Add all components
        mainPanel.add(new Header(), BorderLayout.NORTH);
        
        // Center content with scrolling
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        
        contentPanel.add(new HeroSection());
        contentPanel.add(new FeaturedPlants());
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(new Footer(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void connectCartButton() {
        // Find and connect the cart button in the header
        JPanel header = (JPanel) ((JPanel) getContentPane().getComponent(0)).getComponent(0);
        findAndConnectCartButton(header);
    }
    
    private void findAndConnectCartButton(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if (btn.getText().contains("Cart")) {
                    // No-op: header now provides its own cart navigation behavior
                }
            } else if (comp instanceof Container) {
                findAndConnectCartButton((Container) comp);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Home home = new Home();
            home.setVisible(true);
        });
    }
}
