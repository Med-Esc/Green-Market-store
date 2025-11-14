package Home;

import java.awt.*;
import javax.swing.*;

public class CartModal extends JDialog {
    
    public CartModal(JFrame parent) {
        super(parent, "Shopping Cart", true);
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(parent);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(34, 139, 34));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("🛒 Your Cart");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Cart items area
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);
        cartItemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Sample cart items
        cartItemsPanel.add(createCartItem("Monstera Deliciosa", "$29.99", 1));
        cartItemsPanel.add(Box.createVerticalStrut(10));
        cartItemsPanel.add(createCartItem("Snake Plant", "$19.99", 2));
        
        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
        
        // Footer with total and buttons
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel totalLabel = new JLabel("Total: $69.97");
        totalLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        totalLabel.setForeground(new Color(34, 139, 34));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setFont(new Font("Monospaced", Font.BOLD, 12));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setBackground(new Color(34, 139, 34));
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBorderPainted(false);
        checkoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkoutBtn.setPreferredSize(new Dimension(120, 35));
        
        JButton closeBtn = new JButton("Close");
        closeBtn.setFont(new Font("Monospaced", Font.PLAIN, 12));
        closeBtn.setForeground(new Color(60, 60, 60));
        closeBtn.setBackground(new Color(240, 240, 240));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.setPreferredSize(new Dimension(100, 35));
        closeBtn.addActionListener(e -> dispose());
        
        buttonPanel.add(closeBtn);
        buttonPanel.add(checkoutBtn);
        
        footerPanel.add(totalLabel, BorderLayout.WEST);
        footerPanel.add(buttonPanel, BorderLayout.EAST);
        
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createCartItem(String name, String price, int quantity) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        
        JLabel qtyLabel = new JLabel("Qty: " + quantity);
        qtyLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        qtyLabel.setForeground(Color.GRAY);
        
        leftPanel.add(nameLabel, BorderLayout.NORTH);
        leftPanel.add(qtyLabel, BorderLayout.SOUTH);
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        priceLabel.setForeground(new Color(34, 139, 34));
        
        JButton removeBtn = new JButton("Remove");
        removeBtn.setFont(new Font("Monospaced", Font.PLAIN, 10));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setBackground(new Color(220, 53, 69));
        removeBtn.setFocusPainted(false);
        removeBtn.setBorderPainted(false);
        removeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeBtn.setPreferredSize(new Dimension(80, 25));
        
        removeBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Remove " + name + " from cart?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (result == JOptionPane.YES_OPTION) {
                itemPanel.setVisible(false);
                ToastNotification.showToast((JFrame) getOwner(), name + " removed from cart");
            }
        });
        
        rightPanel.add(priceLabel);
        rightPanel.add(removeBtn);
        
        itemPanel.add(leftPanel, BorderLayout.WEST);
        itemPanel.add(rightPanel, BorderLayout.EAST);
        
        return itemPanel;
    }
    
    public static void showCart(JFrame parent) {
        CartModal modal = new CartModal(parent);
        modal.setVisible(true);
    }
}
