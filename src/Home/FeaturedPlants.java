package Home;

import javax.swing.*;
import java.awt.*;

public class FeaturedPlants extends JPanel {
    
    public FeaturedPlants() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        
        // Title section
        JLabel titleLabel = new JLabel("Featured Plants");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 30, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        // Plants grid
        JPanel plantsGrid = new JPanel(new GridLayout(1, 3, 30, 0));
        plantsGrid.setBackground(Color.WHITE);
        
        // Create three plant cards
        plantsGrid.add(createPlantCard("Monstera Deliciosa", "$29.99", "🌿"));
        plantsGrid.add(createPlantCard("Snake Plant", "$19.99", "🌱"));
        plantsGrid.add(createPlantCard("Fiddle Leaf Fig", "$39.99", "🍃"));
        
        add(plantsGrid, BorderLayout.CENTER);
    }
    
    private JPanel createPlantCard(String name, String price, String emoji) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Image placeholder
        JPanel imagePlaceholder = new JPanel();
        imagePlaceholder.setLayout(new GridBagLayout());
        imagePlaceholder.setPreferredSize(new Dimension(0, 220));
        imagePlaceholder.setBackground(new Color(240, 240, 240));
        
        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        imagePlaceholder.add(emojiLabel);
        
        card.add(imagePlaceholder, BorderLayout.CENTER);
        
        // Info section
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        nameLabel.setForeground(new Color(30, 30, 30));
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
        priceLabel.setForeground(new Color(34, 139, 34));
        
        JButton addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setFont(new Font("Monospaced", Font.PLAIN, 11));
        addToCartBtn.setForeground(Color.WHITE);
        addToCartBtn.setBackground(new Color(34, 139, 34));
        addToCartBtn.setFocusPainted(false);
        addToCartBtn.setBorderPainted(false);
        addToCartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addToCartBtn.setPreferredSize(new Dimension(0, 35));
        
        addToCartBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addToCartBtn.setBackground(new Color(28, 120, 28));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addToCartBtn.setBackground(new Color(34, 139, 34));
            }
        });
        
        // Trigger toast notification on click
        addToCartBtn.addActionListener(e -> {
            ToastNotification.showToast((JFrame) SwingUtilities.getWindowAncestor(this), 
                name + " added to cart!");
        });
        
        JPanel topInfo = new JPanel(new BorderLayout());
        topInfo.setBackground(Color.WHITE);
        topInfo.add(nameLabel, BorderLayout.WEST);
        topInfo.add(priceLabel, BorderLayout.EAST);
        
        infoPanel.add(topInfo, BorderLayout.NORTH);
        infoPanel.add(addToCartBtn, BorderLayout.SOUTH);
        
        card.add(infoPanel, BorderLayout.SOUTH);
        
        return card;
    }
}
