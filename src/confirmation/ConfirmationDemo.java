package confirmation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfirmationDemo extends JFrame {
    
    public ConfirmationDemo() {
        setTitle("Confirmation Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Main panel with some padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // Instructions
        JLabel instructions = new JLabel("<html><div style='text-align: center;'>"
                + "<h2>Confirmation Dialog Demo</h2>"
                + "<p>Click the button below to see the confirmation dialog in action.</p>"
                + "</div></html>");
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // Button to trigger the confirmation
        JButton showDialogButton = new JButton("Remove Item from Cart");
        styleButton(showDialogButton);
        
        // Result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // Button action
        showDialogButton.addActionListener((ActionEvent e) -> {
            boolean confirmed = ConfirmationDialog.showConfirmation(
                this,
                "Confirm Removal",
                "Are you sure you want to remove this item from your cart?"
            );
            
            if (confirmed) {
                resultLabel.setText("✅ Item removed from cart!");
            } else {
                resultLabel.setText("❌ Removal cancelled.");
            }
        });
        
        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showDialogButton);
        
        mainPanel.add(instructions, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setBackground(new Color(128, 0, 128));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(250, 40));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ConfirmationDemo demo = new ConfirmationDemo();
            demo.setVisible(true);
        });
    }
}
