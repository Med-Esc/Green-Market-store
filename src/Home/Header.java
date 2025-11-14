package Home;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    
    public Header() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(0, 60));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        // Left side - Logo
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        leftPanel.setBackground(Color.WHITE);
        
        JLabel logo = new JLabel("🌿 GreenMarket");
        logo.setFont(new Font("Monospaced", Font.BOLD, 16));
        logo.setForeground(new Color(34, 139, 34));
        leftPanel.add(logo);
        
        // Center - Search bar
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(Color.WHITE);
        
        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Monospaced", Font.PLAIN, 12));
        searchField.setText("Search plants...");
        searchField.setForeground(Color.GRAY);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search plants...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search plants...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        
        centerPanel.add(searchField);
        
        // Right side - Navigation buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        rightPanel.setBackground(Color.WHITE);
        
        JButton homeBtn = createNavButton("🏠 Home");
        JButton catalogBtn = createNavButton("🌱 Plants Catalog");
        JButton cartBtn = createNavButton("🛒 Cart");
        JButton filterBtn = createNavButton("⚙ Filter Options");
        
        rightPanel.add(homeBtn);
        rightPanel.add(catalogBtn);
        rightPanel.add(cartBtn);
        rightPanel.add(filterBtn);
        
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
    
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.PLAIN, 11));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setForeground(new Color(60, 60, 60));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(34, 139, 34));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(60, 60, 60));
            }
        });
        
        return button;
    }
}
