package Home;

import Prodects.ProductsPage;
import cart.CartPage;
import filtering.FilterPage;
import java.awt.*;
import javax.swing.*;

public class Header extends JPanel {

    private static final Color PRIMARY_GREEN = new Color(34, 139, 34);
    private static final Color DARK_GRAY = new Color(60, 60, 60);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color BORDER_GRAY = new Color(230, 230, 230);

    public Header() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(0, 75));

        // subtle bottom border
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_GRAY),
            BorderFactory.createEmptyBorder(0, 0, 2, 0)
        ));

        // Left: leaf icon + brand text (leaf green, text black)
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 0));

        JLabel leafLabel = new JLabel(new LeafIcon(20, PRIMARY_GREEN));
        leafLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));

        JLabel brand = new JLabel("GreenMarket");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 20));
        brand.setForeground(Color.BLACK);

        leftPanel.add(leafLabel);
        leftPanel.add(brand);

        // Center: large search bar
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JPanel searchWrapper = new JPanel(new BorderLayout());
        searchWrapper.setBackground(Color.WHITE);

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(new Color(130, 130, 130));
        searchField.setBackground(LIGHT_GRAY);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        // make the search field wide by pref size; BorderLayout.CENTER will expand it
        searchField.setPreferredSize(new Dimension(600, 44));
        searchField.setText("Search plants...");

        // Small monochrome magnifier icon
        JLabel magnifier = new JLabel(new MagnifierIcon(16, Color.DARK_GRAY));
        magnifier.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

        searchWrapper.add(magnifier, BorderLayout.WEST);
        searchWrapper.add(searchField, BorderLayout.CENTER);

        centerPanel.add(searchWrapper, BorderLayout.CENTER);

        // placeholder behaviour
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if ("Search plants...".equals(searchField.getText())) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
                searchField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_GREEN, 2),
                    BorderFactory.createEmptyBorder(9, 11, 9, 11)
                ));
                searchField.setBackground(Color.WHITE);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search plants...");
                    searchField.setForeground(new Color(130, 130, 130));
                }
                searchField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
                searchField.setBackground(LIGHT_GRAY);
            }
        });

        // Right: navigation with small monochrome icons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 18));

        JButton homeBtn = createNavButton("Home", new HouseIcon(16, Color.BLACK));
        JButton catalogBtn = createNavButton("Plants Catalog", new SproutIcon(16, Color.BLACK));
        JButton cartBtn = createNavButton("Cart", new CartIcon(16, Color.BLACK));
        JButton filterBtn = createNavButton("Filter Options", new FilterIcon(16, Color.BLACK));
        
        homeBtn.addActionListener(e -> {
            java.awt.Window top = SwingUtilities.getWindowAncestor(this);
            if (top instanceof JFrame) {
                ((JFrame) top).dispose();
            }
            SwingUtilities.invokeLater(() -> {
                Home home = new Home();
                home.setVisible(true);
            });
        });

        // Add action listener to open FilterPage
        filterBtn.addActionListener(e -> {
            // Close the current window
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
            
            // Open FilterPage
            SwingUtilities.invokeLater(() -> {
                FilterPage filterPage = new FilterPage();
                filterPage.setVisible(true);
            });
        });
        
        // Add action listener to open ProductsPage
        catalogBtn.addActionListener(e -> {
            // Close the current window
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
            
            // Open ProductsPage
            SwingUtilities.invokeLater(() -> {
                ProductsPage productsPage = new ProductsPage();
                productsPage.setVisible(true);
            });
        });

        cartBtn.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.dispose();
            }
            SwingUtilities.invokeLater(() -> {
                CartPage cartPage = new CartPage();
                cartPage.setVisible(true);
            });
        });

        rightPanel.add(homeBtn);
        rightPanel.add(catalogBtn);
        rightPanel.add(cartBtn);
        rightPanel.add(filterBtn);

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JButton createNavButton(String text, Icon icon) {
        JButton button = new JButton(text, icon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(8);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setForeground(DARK_GRAY);
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(PRIMARY_GREEN);
                button.setBackground(new Color(245, 255, 245));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(DARK_GRAY);
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }

    /* --- Small vector icons used by the header (monochrome) --- */
    private static class LeafIcon implements Icon {
        private final int size;
        private final Color color;

        LeafIcon(int size, Color color) { this.size = size; this.color = color; }

        public int getIconWidth() { return size; }
        public int getIconHeight() { return size; }

        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            int w = size; int h = size;
            // simple leaf: two ovals and a stem
            g2.fillOval(x + w/4, y + h/6, w/2, h/2);
            g2.fillOval(x + w/6, y + h/3, w/2, h/2);
            g2.setStroke(new BasicStroke(Math.max(1, size/10))); g2.setColor(color.darker());
            g2.drawLine(x + w/2, y + h/2, x + w/2, y + h);
            g2.dispose();
        }
    }

    private static class MagnifierIcon implements Icon {
        private final int size; private final Color color;
        MagnifierIcon(int size, Color color) { this.size = size; this.color = color; }
        public int getIconWidth() { return size; } public int getIconHeight() { return size; }
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color); g2.setStroke(new BasicStroke(2f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int d = size - 6; g2.drawOval(x+1, y+1, d, d);
            g2.drawLine(x + d, y + d, x + size, y + size);
            g2.dispose();
        }
    }

    private static class HouseIcon implements Icon {
        private final int size; private final Color color;
        HouseIcon(int size, Color color) { this.size = size; this.color = color; }
        public int getIconWidth() { return size; } public int getIconHeight() { return size; }
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color); g2.setStroke(new BasicStroke(2f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = size; int h = size;
            g2.drawRect(x + w/4, y + h/3, w/2, h/3);
            g2.drawLine(x, y + h/3, x + w/2, y);
            g2.drawLine(x + w/2, y, x + w, y + h/3);
            g2.dispose();
        }
    }

    private static class SproutIcon implements Icon {
        private final int size; private final Color color;
        SproutIcon(int size, Color color) { this.size = size; this.color = color; }
        public int getIconWidth() { return size; } public int getIconHeight() { return size; }
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color); g2.setStroke(new BasicStroke(2f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = size, h = size;
            g2.drawLine(x + w/2, y + h, x + w/2, y + h/2);
            g2.fillOval(x + w/2 - 2, y + h/2 - 6, 8, 12);
            g2.fillOval(x + w/6, y + h/2 - 4, 8, 10);
            g2.dispose();
        }
    }

    private static class CartIcon implements Icon {
        private final int size; private final Color color;
        CartIcon(int size, Color color) { this.size = size; this.color = color; }
        public int getIconWidth() { return size; } public int getIconHeight() { return size; }
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color); g2.setStroke(new BasicStroke(2f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = size, h = size;
            g2.drawRect(x + 2, y + 4, w - 8, h - 10);
            g2.drawLine(x + 2, y + 4, x, y + 2);
            g2.fillOval(x + 3, y + h - 5, 4, 4);
            g2.fillOval(x + w - 8, y + h - 5, 4, 4);
            g2.dispose();
        }
    }

    private static class FilterIcon implements Icon {
        private final int size; private final Color color;
        FilterIcon(int size, Color color) { this.size = size; this.color = color; }
        public int getIconWidth() { return size; } public int getIconHeight() { return size; }
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color); g2.setStroke(new BasicStroke(2f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = size, h = size;
            g2.drawLine(x, y + 2, x + w, y + 2);
            g2.drawLine(x + 3, y + h/2, x + w - 3, y + h/2);
            g2.drawLine(x + w/4, y + h - 3, x + w - w/4, y + h - 3);
            g2.dispose();
        }
    }
}
