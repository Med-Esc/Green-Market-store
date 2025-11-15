package filtering;

import javax.swing.*;
import java.awt.*;

public class FilterCard extends JPanel {
    private final String title;
    
    public FilterCard(String title, JComponent content) {
        this.title = title;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        
        add(titleLabel, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
    }
    
    public String getTitle() {
        return title;
    }
}
