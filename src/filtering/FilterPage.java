package filtering;

import Home.Home;
import Prodects.ProductsPage;
import cart.CartPage;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class FilterPage extends JFrame {
    private static final Color PRIMARY_GREEN = new Color(40, 103, 76);
    
    public FilterPage() {
        setTitle("GreenMarket - Filter Products");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Main container with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Header
        JPanel header = createHeader();
        
        // Content area with filter panel and product grid
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Filter panel (left)
        FilterPanel filterPanel = new FilterPanel();
        
        // Product grid (right)
        JPanel productGrid = createProductGrid();
        JScrollPane scrollPane = new JScrollPane(productGrid);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Add components to content panel
        contentPanel.add(filterPanel, BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add components to main panel
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY_GREEN);
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        // Logo
        JLabel logo = new JLabel("GreenMarket");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        
        // Navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        navPanel.setOpaque(false);
        
        JButton homeBtn = createNavButton("Home");
        JButton shopBtn = createNavButton("Shop");
        JButton aboutBtn = createNavButton("About");
        JButton contactBtn = createNavButton("Contact");
        
        navPanel.add(homeBtn);
        navPanel.add(shopBtn);
        navPanel.add(aboutBtn);
        navPanel.add(contactBtn);

        homeBtn.addActionListener(e -> {
            dispose();
            Home home = new Home();
            home.setVisible(true);
        });

        shopBtn.addActionListener(e -> {
            dispose();
            ProductsPage productsPage = new ProductsPage();
            productsPage.setVisible(true);
        });
        
        // Cart button
        JButton cartBtn = new JButton("Cart (0)");
        cartBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        cartBtn.setBackground(Color.WHITE);
        cartBtn.setForeground(PRIMARY_GREEN);
        cartBtn.setBorderPainted(false);
        cartBtn.setFocusPainted(false);
        cartBtn.addActionListener(e -> {
            dispose();
            CartPage cartPage = new CartPage();
            cartPage.setVisible(true);
        });
        
        header.add(logo, BorderLayout.WEST);
        header.add(navPanel, BorderLayout.CENTER);
        header.add(cartBtn, BorderLayout.EAST);
        
        return header;
    }
    
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (text.equals("Shop")) {
            button.setForeground(Color.YELLOW);
        }
        
        return button;
    }
    
    private JPanel createProductGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBackground(Color.WHITE);
        
        // Sample product data
        List<String> products = Arrays.asList(
            "Snake Plant", "Monstera", "Fiddle Leaf Fig",
            "ZZ Plant", "Rubber Plant", "Peace Lily",
            "Pothos", "Spider Plant", "Aloe Vera"
        );
        
        for (String product : products) {
            JPanel productCard = createProductCard(product);
            gridPanel.add(productCard);
        }
        
        return gridPanel;
    }
    
    private JPanel createProductCard(String productName) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        card.setPreferredSize(new Dimension(250, 350));
        
        // Product image placeholder
        JLabel imageLabel = new JLabel(productName, JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(250, 200));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(245, 245, 245));
        
        // Product info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel nameLabel = new JLabel(productName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel priceLabel = new JLabel("$" + (int)(Math.random() * 100 + 10) + ".99");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(PRIMARY_GREEN);
        
        JButton viewButton = new JButton("View Details");
        viewButton.setBackground(PRIMARY_GREEN);
        viewButton.setForeground(Color.WHITE);
        viewButton.setBorderPainted(false);
        viewButton.setFocusPainted(false);
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(viewButton);
        
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            FilterPage filterPage = new FilterPage();
            filterPage.setVisible(true);
        });
    }
}
