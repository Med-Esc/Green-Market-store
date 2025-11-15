package filtering;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Hashtable;

public class FilterPanel extends JPanel {
    private static final Color PRIMARY_GREEN = new Color(40, 103, 76);
    
    public FilterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(300, 800));
        
        // Category Filter
        add(createCategoryFilter());
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Price Range Filter
        add(createPriceFilter());
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Size Filter
        add(createSizeFilter());
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Care Level Filter
        add(createCareLevelFilter());
        
        // Apply Filters Button
        JButton applyButton = new JButton("Apply Filters");
        applyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        applyButton.setBackground(PRIMARY_GREEN);
        applyButton.setForeground(Color.WHITE);
        applyButton.setFont(new Font("Arial", Font.BOLD, 14));
        applyButton.setBorderPainted(false);
        applyButton.setFocusPainted(false);
        applyButton.setPreferredSize(new Dimension(200, 40));
        applyButton.setMaximumSize(new Dimension(200, 40));
        
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(applyButton);
    }
    
    private JComponent createCategoryFilter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        String[] categories = {
            "All Plants",
            "Indoor Plants",
            "Outdoor Plants",
            "Flowering Plants",
            "Succulents",
            "Cacti",
            "Tropical Plants"
        };
        
        ButtonGroup group = new ButtonGroup();
        for (String category : categories) {
            JRadioButton button = new JRadioButton(category);
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            group.add(button);
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Select first option by default
        if (categories.length > 0) {
            ((JRadioButton)panel.getComponent(0)).setSelected(true);
        }
        
        return new FilterCard("Category", panel);
    }
    
    private JComponent createPriceFilter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JSlider priceSlider = new JSlider(0, 200, 200);
        priceSlider.setMajorTickSpacing(50);
        priceSlider.setMinorTickSpacing(10);
        priceSlider.setPaintTicks(true);
        priceSlider.setPaintLabels(true);
        priceSlider.setSnapToTicks(true);
        
        // Custom label table
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("$0"));
        labelTable.put(50, new JLabel("$50"));
        labelTable.put(100, new JLabel("$100"));
        labelTable.put(150, new JLabel("$150"));
        labelTable.put(200, new JLabel("$200+"));
        priceSlider.setLabelTable(labelTable);
        
        // Current price range display
        JLabel priceRangeLabel = new JLabel("Price: $0 - $200");
        priceRangeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceRangeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        priceSlider.addChangeListener(e -> {
            int value = ((JSlider)e.getSource()).getValue();
            priceRangeLabel.setText("Price: $0 - $" + value);
        });
        
        panel.add(priceRangeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(priceSlider);
        
        return new FilterCard("Price Range", panel);
    }
    
    private JComponent createSizeFilter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        String[] sizes = {
            "Small (under 1ft)",
            "Medium (1-3ft)",
            "Large (3-6ft)",
            "Extra Large (6ft+)"
        };
        
        for (String size : sizes) {
            JCheckBox checkBox = new JCheckBox(size);
            checkBox.setBackground(Color.WHITE);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(checkBox);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        return new FilterCard("Size", panel);
    }
    
    private JComponent createCareLevelFilter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        String[] careLevels = {
            "Beginner Friendly",
            "Moderate Care",
            "Expert Level"
        };
        
        for (String level : careLevels) {
            JCheckBox checkBox = new JCheckBox(level);
            checkBox.setBackground(Color.WHITE);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(checkBox);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        return new FilterCard("Care Level", panel);
    }
}
