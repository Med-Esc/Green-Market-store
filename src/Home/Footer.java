package Home;

import java.awt.*;
import javax.swing.*;

public class Footer extends JPanel {

    public Footer() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 70));
        setBackground(new Color(245, 240, 255));

        // Gradient background
        setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        // ----- LEFT: Links -----
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 15));
        leftPanel.setOpaque(false);

        leftPanel.add(createLink("Home"));
        leftPanel.add(createLink("About"));
        leftPanel.add(createLink("Contact"));
        leftPanel.add(createLink("Privacy Policy"));
        leftPanel.add(createLink("Terms"));

        // ----- CENTER: Copyright -----
        JLabel centerLabel = new JLabel("© 2025 ModernUI Inc. All rights reserved.");
        centerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerLabel.setForeground(new Color(80, 70, 100));
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(centerLabel, BorderLayout.CENTER);

        // ----- RIGHT: Social Icons -----
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        rightPanel.setOpaque(false);

        rightPanel.add(createSocialIcon("facebook"));
        rightPanel.add(createSocialIcon("x"));
        rightPanel.add(createSocialIcon("instagram"));

        // Add panels
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    // Create a clickable footer link
    private JLabel createLink(String text) {
        JLabel link = new JLabel(text);
        link.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        link.setForeground(new Color(70, 60, 100));
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        link.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link.setForeground(new Color(120, 70, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                link.setForeground(new Color(70, 60, 100));
            }
        });

        return link;
    }

    // Create circular social icon buttons
    private JButton createSocialIcon(String platform) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();

                // Circle background
                g2.setColor(getBackground());
                g2.fillOval(0, 0, w, h);

                // Border
                g2.setColor(getForeground());
                g2.setStroke(new BasicStroke(1.6f));
                g2.drawOval(0, 0, w - 1, h - 1);

                // Draw platform symbol
                g2.setColor(getForeground());
                switch (platform.toLowerCase()) {
                    case "facebook" -> {
                        g2.setFont(new Font("Arial", Font.BOLD, 16));
                        FontMetrics fm = g2.getFontMetrics();
                        g2.drawString("f", (w - fm.stringWidth("f")) / 2, (h + fm.getAscent() / 2) / 2 + 3);
                    }
                    case "x" -> {
                        g2.setStroke(new BasicStroke(2f));
                        g2.drawLine(8, 8, w - 8, h - 8);
                        g2.drawLine(w - 8, 8, 8, h - 8);
                    }
                    case "instagram" -> {
                        g2.setStroke(new BasicStroke(1.8f));
                        g2.drawRoundRect(6, 6, w - 12, h - 12, 6, 6);
                        g2.fillOval(w / 2 - 3, h / 2 - 3, 6, 6);
                        g2.fillOval(w - 13, 8, 2, 2);
                    }
                }

                g2.dispose();
            }
        };

        btn.setPreferredSize(new Dimension(28, 28));
        btn.setBackground(new Color(245, 240, 255));
        btn.setForeground(new Color(70, 60, 100));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(120, 70, 230));
                btn.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(70, 60, 100));
                btn.repaint();
            }
        });

        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gradient background
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(250, 245, 255),
                getWidth(), getHeight(), new Color(235, 225, 250)
        );
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
