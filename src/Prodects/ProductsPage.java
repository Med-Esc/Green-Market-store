package Prodects;

import Home.Home;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ProductsPage extends JFrame {
    private JPanel mainPanel;
    private JPanel productsPanel;
    private JComboBox<String> sortComboBox;
    private JComboBox<String> categoryComboBox;
    private JLabel cartCountLabel;
    private int cartItemCount = 0;
    
    // Sample product data
    private List<Product> products = Arrays.asList(
        new Product("Snake Plant", 29.99, "src/Assets/snake_plant.jpg"),
        new Product("Monstera", 49.99, "src/Assets/monstera.jpg"),
        new Product("Fiddle Leaf Fig", 59.99, "src/Assets/fiddle_leaf.jpg"),
        new Product("Pothos", 24.99, "src/Assets/pothos.jpg"),
        new Product("ZZ Plant", 34.99, "src/Assets/zz_plant.jpg"),
        new Product("Rubber Plant", 39.99, "src/Assets/rubber_plant.jpg")
    );

    public ProductsPage() {
        setTitle("GreenMarket - Shop Plants");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        initComponents();
        setupLayout();
    }
    
    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JPanel headerPanel = createHeader();
        
        // Create filter/sort panel
        JPanel filterPanel = createFilterPanel();
        
        // Create products grid
        productsPanel = new JPanel(new GridLayout(0, 3, 20, 30));
        productsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add products to the grid
        displayProducts(products);
        
        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(filterPanel, BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(40, 103, 76));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        // Logo
        JLabel logoLabel = new JLabel("GreenMarket");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(Color.WHITE);
        
        // Navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        navPanel.setOpaque(false);
        
        JButton homeBtn = createNavButton("Home");
        JButton shopBtn = createNavButton("Shop");
        shopBtn.setForeground(Color.YELLOW);
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
        
        // Cart icon
        JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cartPanel.setOpaque(false);
        
        JButton cartButton = new JButton("Cart (0)");
        cartButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cartButton.setBackground(Color.WHITE);
        cartButton.setForeground(new Color(40, 103, 76));
        cartButton.setBorderPainted(false);
        cartButton.setFocusPainted(false);
        cartButton.addActionListener(e -> showCartDialog());
        
        cartCountLabel = new JLabel("0");
        cartCountLabel.setForeground(Color.WHITE);
        cartCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        cartPanel.add(cartButton);
        cartPanel.add(cartCountLabel);
        
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(navPanel, BorderLayout.CENTER);
        headerPanel.add(cartPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        // Category filter
        JLabel categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>(new String[]{"All Plants", "Indoor Plants", "Outdoor Plants", "Flowering Plants", "Succulents"});
        categoryComboBox.setPreferredSize(new Dimension(150, 30));
        
        // Sort by
        JLabel sortLabel = new JLabel("Sort by:");
        sortComboBox = new JComboBox<>(new String[]{"Default", "Price: Low to High", "Price: High to Low", "Name: A-Z", "Name: Z-A"});
        sortComboBox.setPreferredSize(new Dimension(150, 30));
        sortComboBox.addActionListener(e -> sortProducts());
        
        filterPanel.add(categoryLabel);
        filterPanel.add(categoryComboBox);
        filterPanel.add(Box.createHorizontalStrut(30));
        filterPanel.add(sortLabel);
        filterPanel.add(sortComboBox);
        
        return filterPanel;
    }
    
    private void displayProducts(List<Product> productsToShow) {
        productsPanel.removeAll();
        
        for (Product product : productsToShow) {
            JPanel productCard = createProductCard(product);
            productsPanel.add(productCard);
        }
        
        productsPanel.revalidate();
        productsPanel.repaint();
    }
    
    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        card.setPreferredSize(new Dimension(250, 350));
        
        // Product image
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setIcon(null);
            imageLabel.setText("No Image");
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        
        // Product info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(new Color(40, 103, 76));
        
        JButton viewButton = new JButton("View Details");
        viewButton.setBackground(new Color(40, 103, 76));
        viewButton.setForeground(Color.WHITE);
        viewButton.setBorderPainted(false);
        viewButton.setFocusPainted(false);
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewButton.addActionListener(e -> showProductDetails(product));
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(viewButton);
        
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void sortProducts() {
        String selectedSort = (String) sortComboBox.getSelectedItem();
        List<Product> sortedProducts = new ArrayList<>(products);
        
        switch (selectedSort) {
            case "Price: Low to High":
                sortedProducts.sort(Comparator.comparing(Product::getPrice));
                break;
            case "Price: High to Low":
                sortedProducts.sort(Comparator.comparing(Product::getPrice).reversed());
                break;
            case "Name: A-Z":
                sortedProducts.sort(Comparator.comparing(Product::getName));
                break;
            case "Name: Z-A":
                sortedProducts.sort(Comparator.comparing(Product::getName).reversed());
                break;
            default:
                // Default sorting
                break;
        }
        
        displayProducts(sortedProducts);
    }
    
    private void showProductDetails(Product product) {
        JDialog detailsDialog = new JDialog(this, product.getName(), true);
        detailsDialog.setSize(600, 500);
        detailsDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Product image
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("No Image Available");
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        
        // Product details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 22));
        priceLabel.setForeground(new Color(40, 103, 76));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        
        JTextArea descriptionArea = new JTextArea("This is a beautiful " + product.getName() + " that will enhance the beauty of your home or office. Perfect for plant lovers of all levels.");
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(panel.getBackground());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(new Color(40, 103, 76));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 16));
        addToCartButton.setBorderPainted(false);
        addToCartButton.setFocusPainted(false);
        addToCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addToCartButton.addActionListener(e -> {
            addToCart(product);
            detailsDialog.dispose();
        });
        
        detailsPanel.add(nameLabel);
        detailsPanel.add(priceLabel);
        detailsPanel.add(descriptionArea);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        detailsPanel.add(addToCartButton);
        
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(detailsPanel, BorderLayout.CENTER);
        
        detailsDialog.add(panel);
        detailsDialog.setVisible(true);
    }
    
    private void addToCart(Product product) {
        cartItemCount++;
        cartCountLabel.setText(String.valueOf(cartItemCount));

        // Show toast notification
        String message = product.getName() + " has been added to your cart!\n" +
                       "Total items in cart: " + cartItemCount;
        JOptionPane.showMessageDialog(this, message, "Added to Cart", 
                                   JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCartDialog() {
        // ... (rest of the code remains the same)
        JDialog cartDialog = new JDialog(this, "Your Cart", true);
        cartDialog.setSize(500, 500);
        cartDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        if (cartItemCount == 0) {
            JLabel emptyLabel = new JLabel("Your cart is empty", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.add(emptyLabel, BorderLayout.CENTER);
        } else {
            // Cart items list
            JPanel itemsPanel = new JPanel();
            itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
            
            // Add sample cart items (in a real app, this would come from a cart model)
            for (int i = 0; i < 3 && i < products.size(); i++) {
                Product p = products.get(i);
                JPanel itemPanel = createCartItem(p, i);
                itemsPanel.add(itemPanel);
                if (i < 2) itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            
            JScrollPane scrollPane = new JScrollPane(itemsPanel);
            scrollPane.setBorder(null);
            
            // Cart summary
            JPanel summaryPanel = new JPanel(new BorderLayout());
            summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
            
            JPanel totalPanel = new JPanel(new BorderLayout());
            JLabel totalLabel = new JLabel("Total:");
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            
            double total = products.stream().limit(3).mapToDouble(Product::getPrice).sum();
            JLabel amountLabel = new JLabel("$" + String.format("%.2f", total));
            amountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            amountLabel.setForeground(new Color(40, 103, 76));
            
            totalPanel.add(totalLabel, BorderLayout.WEST);
            totalPanel.add(amountLabel, BorderLayout.EAST);
            
            JButton checkoutButton = new JButton("Proceed to Checkout");
            checkoutButton.setBackground(new Color(40, 103, 76));
            checkoutButton.setForeground(Color.WHITE);
            checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
            checkoutButton.setBorderPainted(false);
            checkoutButton.setFocusPainted(false);
            checkoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            checkoutButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(cartDialog, 
                    "Proceeding to checkout is not implemented in this demo.", 
                    "Checkout", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
            
            summaryPanel.add(totalPanel, BorderLayout.NORTH);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.CENTER);
            summaryPanel.add(checkoutButton, BorderLayout.SOUTH);
            
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(summaryPanel, BorderLayout.SOUTH);
        }
        
        cartDialog.add(panel);
        cartDialog.setVisible(true);
    }
    
    private JPanel createCartItem(Product product, int index) {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            itemPanel.getBorder(), 
            BorderFactory.createEmptyBorder(0, 0, 10, 0)
        ));
        
        // Product image
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("No Image");
            imageLabel.setPreferredSize(new Dimension(80, 80));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }
        
        // Product info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(100, 100, 100));
        
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton minusButton = new JButton("-");
        JLabel quantityLabel = new JLabel("1");
        JButton plusButton = new JButton("+");
        
        minusButton.setPreferredSize(new Dimension(25, 25));
        plusButton.setPreferredSize(new Dimension(25, 25));
        
        minusButton.addActionListener(e -> {
            int qty = Integer.parseInt(quantityLabel.getText());
            if (qty > 1) {
                quantityLabel.setText(String.valueOf(qty - 1));
            }
        });
        
        plusButton.addActionListener(e -> {
            int qty = Integer.parseInt(quantityLabel.getText());
            quantityLabel.setText(String.valueOf(qty + 1));
        });
        
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(quantityPanel);
        
        // Remove button
        JButton removeButton = new JButton("×");
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        removeButton.setForeground(Color.GRAY);
        removeButton.setContentAreaFilled(false);
        removeButton.setBorderPainted(false);
        removeButton.setFocusPainted(false);
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                null, 
                "Are you sure you want to remove this item from your cart?",
                "Remove Item",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (cartItemCount > 0) {
                    cartItemCount--;
                    cartCountLabel.setText(String.valueOf(cartItemCount));
                    JOptionPane.showMessageDialog(
                        null,
                        "Item removed from cart.",
                        "Item Removed",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        
        itemPanel.add(imageLabel, BorderLayout.WEST);
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(removeButton, BorderLayout.EAST);
        
        return itemPanel;
    }
    
    private void setupLayout() {
        // Set up the main frame layout
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ProductsPage productsPage = new ProductsPage();
            productsPage.setVisible(true);
        });
    }
}

class Product {
    private String name;
    private double price;
    private String imagePath;
    
    public Product(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getImagePath() {
        return imagePath;
    }
}
