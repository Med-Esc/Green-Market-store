package Home;

import javax.swing.*;
import java.awt.*;

public class ToastNotification {
    
    public static void showToast(JFrame parent, String message) {
        // Create toast window
        JWindow toast = new JWindow(parent);
        toast.setLayout(new BorderLayout());
        
        // Create panel with message
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        
        JLabel messageLabel = new JLabel("✓ " + message);
        messageLabel.setFont(new Font("Monospaced", Font.BOLD, 13));
        messageLabel.setForeground(Color.WHITE);
        panel.add(messageLabel);
        
        toast.add(panel);
        toast.pack();
        
        // Position at bottom center of parent
        Dimension parentSize = parent.getSize();
        Point parentLocation = parent.getLocation();
        Dimension toastSize = toast.getSize();
        
        int x = parentLocation.x + (parentSize.width - toastSize.width) / 2;
        int y = parentLocation.y + parentSize.height - toastSize.height - 100;
        
        toast.setLocation(x, y);
        toast.setOpacity(0.95f);
        toast.setVisible(true);
        
        // Auto-hide after 2.5 seconds
        Timer timer = new Timer(2500, e -> {
            toast.setVisible(false);
            toast.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }
}
