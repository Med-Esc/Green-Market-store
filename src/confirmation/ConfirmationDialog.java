package confirmation;

import javax.swing.*;
import java.awt.*;

public class ConfirmationDialog extends JDialog {
    private static final Color PRIMARY_COLOR = new Color(128, 0, 128);
    private boolean confirmed = false;
    
    public ConfirmationDialog(JFrame parent, String title, String message) {
        super(parent, title, true);
        setLayout(new BorderLayout(20, 20));
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        // Message label
        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        messageLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JButton confirmButton = createButton("Confirm", true);
        JButton cancelButton = createButton("Cancel", false);
        
        confirmButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        
        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);
        
        // Add components
        add(messageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Make Enter key trigger confirm
        getRootPane().setDefaultButton(confirmButton);
    }
    
    private JButton createButton(String text, boolean isPrimary) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 35));
        
        if (isPrimary) {
            button.setBackground(PRIMARY_COLOR);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        }
        
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        
        return button;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public static boolean showConfirmation(Component parent, String title, String message) {
        ConfirmationDialog dialog = new ConfirmationDialog(
            (JFrame) SwingUtilities.getWindowAncestor(parent),
            title,
            message
        );
        dialog.setVisible(true);
        return dialog.isConfirmed();
    }
}
