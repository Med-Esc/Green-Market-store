package Home;

import Prodects.ProductsPage;
import java.awt.*;
import javax.swing.*;

public class HeroSection extends JPanel {
    
    public HeroSection() {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 250, 255));
        setPreferredSize(new Dimension(0, 300));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        // Welcome title
        JLabel welcomeLabel = new JLabel("Welcome to GreenMarket");
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        welcomeLabel.setForeground(new Color(30, 30, 30));
        add(welcomeLabel, gbc);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Discover a variety of plants that bring nature into your home.");
        subtitleLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        add(subtitleLabel, gbc);
        
        // CTA Button
        gbc.insets = new Insets(20, 0, 0, 0);
        JButton ctaButton = new JButton("EXPLORE PLANTS CATALOG");
        ctaButton.setFont(new Font("Monospaced", Font.BOLD, 12));
        ctaButton.setForeground(Color.WHITE);
        ctaButton.setBackground(new Color(138, 43, 226)); // Purple color
        ctaButton.setFocusPainted(false);
        ctaButton.setBorderPainted(false);
        ctaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ctaButton.setPreferredSize(new Dimension(250, 45));
        
        ctaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ctaButton.setBackground(new Color(120, 35, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ctaButton.setBackground(new Color(138, 43, 226));
            }
        });
        
        ctaButton.addActionListener(e -> {
            java.awt.Window window = SwingUtilities.getWindowAncestor(HeroSection.this);
            if (window instanceof JFrame) {
                ((JFrame) window).dispose();
            }
            ProductsPage productsPage = new ProductsPage();
            productsPage.setVisible(true);
        });
        
        add(ctaButton, gbc);
    }
}
