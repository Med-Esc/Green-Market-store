package cart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartItemPanel extends JPanel {
    private static final Color PRIMARY_COLOR = new Color(128, 0, 128);
    private static final Color SECONDARY_COLOR = new Color(100, 0, 100);
    private final CartItem item;
    private final JLabel quantityLabel;
    private final JLabel totalLabel;
    
    public CartItemPanel(CartItem item, CartManager cartManager) {
        this.item = item;
        setLayout(new BorderLayout(15, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        setPreferredSize(new Dimension(800, 100));
        
        // Image panel (left)
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(80, 80));
        imagePanel.setBackground(new Color(245, 245, 245));
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        
        // For demo, using a label with text instead of actual image
        JLabel imageLabel = new JLabel("🖼", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        imagePanel.add(imageLabel);
        
        // Info panel (center)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        
        JLabel descLabel = new JLabel(item.getDescription());
        descLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        descLabel.setForeground(Color.GRAY);
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(descLabel);
        
        // Price and quantity panel (right)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        // Price
        JLabel priceLabel = new JLabel(String.format("$%.2f", item.getPrice()));
        priceLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        priceLabel.setPreferredSize(new Dimension(70, 30));
        
        // Quantity controls
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
        quantityPanel.setBackground(Color.WHITE);
        
        JButton minusBtn = new JButton("-");
        styleButton(minusBtn);
        minusBtn.addActionListener(e -> {
            int newQty = Math.max(1, item.getQuantity() - 1);
            cartManager.updateQuantity(item.getId(), newQty);
        });
        
        quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
        quantityLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setPreferredSize(new Dimension(30, 25));
        
        JButton plusBtn = new JButton("+");
        styleButton(plusBtn);
        plusBtn.addActionListener(e -> {
            int newQty = item.getQuantity() + 1;
            cartManager.updateQuantity(item.getId(), newQty);
        });
        
        quantityPanel.add(minusBtn);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusBtn);
        
        // Total price
        totalLabel = new JLabel(String.format("$%.2f", item.getTotalPrice()));
        totalLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        totalLabel.setPreferredSize(new Dimension(80, 30));
        
        // Remove button
        JButton removeBtn = new JButton("🗑");
        styleButton(removeBtn);
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        removeBtn.addActionListener(e -> cartManager.removeItem(item.getId()));
        
        // Add components to right panel
        rightPanel.add(priceLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        rightPanel.add(quantityPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        rightPanel.add(totalLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        rightPanel.add(removeBtn);
        
        // Add all to main panel
        add(imagePanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 12));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(30, 25));
    }
    
    public void updateQuantity() {
        quantityLabel.setText(String.valueOf(item.getQuantity()));
        totalLabel.setText(String.format("$%.2f", item.getTotalPrice()));
    }
}
