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
