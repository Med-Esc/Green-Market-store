package cart;

import Home.Home;
import Prodects.ProductsPage;
import filtering.FilterPage;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class CartPage extends JFrame implements CartManager.CartUpdateListener {
    private static final Color PRIMARY_COLOR = new Color(128, 0, 128);
    private final JPanel itemsPanel;
    private final JLabel totalLabel;
    private final CartManager cartManager;
    
    public CartPage() {
        cartManager = CartManager.getInstance();
        cartManager.addCartUpdateListener(this);
        
        setTitle("GreenMarket - Your Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(Color.WHITE);
        
        // Header
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        
        // Cart items scroll pane
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
        
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        
        JButton updateCartBtn = createButton("UPDATE CART");
        JButton checkoutBtn = createButton("CHECKOUT");
        
        buttonsPanel.add(totalLabel);
        buttonsPanel.add(updateCartBtn);
        buttonsPanel.add(checkoutBtn);
        
        // Footer
        JPanel footer = createFooter();
        
        // Add components to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        
        // Load cart items
        updateCartItems();
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY_COLOR);
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        // Logo
        JLabel logo = new JLabel("GreenMarket");
        logo.setFont(new Font("Monospaced", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        
        // Search bar
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(PRIMARY_COLOR);
        
        JTextField searchField = new JTextField("Search plants...");
        searchField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(300, 35));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        searchPanel.add(searchField, BorderLayout.CENTER);
        
        // Navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        navPanel.setOpaque(false);

        JButton homeBtn = new JButton("Home");
        JButton catalogBtn = new JButton("Plants Catalog");
        JButton cartBtn = new JButton("Cart");
        JButton filterBtn = new JButton("Filter Options");

        JButton[] buttons = {homeBtn, catalogBtn, cartBtn, filterBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Monospaced", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            navPanel.add(btn);
        }

        homeBtn.addActionListener(e -> {
            dispose();
            Home home = new Home();
            home.setVisible(true);
        });

        catalogBtn.addActionListener(e -> {
            dispose();
            ProductsPage productsPage = new ProductsPage();
            productsPage.setVisible(true);
        });

        filterBtn.addActionListener(e -> {
            dispose();
            FilterPage filterPage = new FilterPage();
            filterPage.setVisible(true);
        });
        
        header.add(logo, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.CENTER);
        header.add(navPanel, BorderLayout.EAST);
        
        return header;
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        
        // Add shadow effect
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 0, 100), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        return button;
    }
    
    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Divider line
        JSeparator divider = new JSeparator();
        divider.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        
        // Links
        JPanel linksPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        linksPanel.setBackground(Color.WHITE);
        
        String[] links = {"Contact Us", "Privacy Policy", "Terms of Use"};
        for (String link : links) {
            JLabel linkLabel = new JLabel(link);
            linkLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
            linkLabel.setForeground(Color.GRAY);
            linksPanel.add(linkLabel);
        }
        
        // Copyright
        JLabel copyright = new JLabel("© 2023 GreenMarket");
        copyright.setFont(new Font("Monospaced", Font.PLAIN, 12));
        copyright.setForeground(Color.GRAY);
        copyright.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        footer.add(divider);
        footer.add(Box.createRigidArea(new Dimension(0, 10)));
        footer.add(linksPanel);
        footer.add(copyright);
        
        return footer;
    }
    
    private void updateCartItems() {
        itemsPanel.removeAll();
        
        List<CartItem> items = cartManager.getItems();
        
        if (items.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty");
            emptyLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
            itemsPanel.add(emptyLabel);
        } else {
            // Add title
            JLabel titleLabel = new JLabel("Your Cart");
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            itemsPanel.add(titleLabel);
            
            // Add items
            for (CartItem item : items) {
                CartItemPanel itemPanel = new CartItemPanel(item, cartManager);
                itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                itemsPanel.add(itemPanel);
            }
        }
        
        // Update total
        updateTotal();
        
        // Refresh the panel
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }
    
    private void updateTotal() {
        double total = cartManager.getTotalPrice();
        totalLabel.setText(String.format("Total: $%.2f", total));
    }
    
    @Override
    public void onCartUpdated() {
        updateCartItems();
    }
    
    public static void main(String[] args) {
        // For testing
        CartManager cartManager = CartManager.getInstance();
        cartManager.addItem(new CartItem("1", "Succulent Plant", "A beautiful and easy-to-care-for plant.", 15.99, ""));
        cartManager.addItem(new CartItem("2", "Orchid Plant", "An exotic plant with stunning flowers.", 25.99, ""));
        cartManager.addItem(new CartItem("3", "Bonsai Tree", "A miniature tree for bonsai enthusiasts.", 45.99, ""));
        
        SwingUtilities.invokeLater(() -> {
            CartPage cartPage = new CartPage();
            cartPage.setVisible(true);
        });
    }
}
