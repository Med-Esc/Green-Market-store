import Home.Home;
import Prodects.ProductsPage;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Uncomment the line below to launch the ProductsPage directly
            // new ProductsPage().setVisible(true);
            
            // Or use the Home page as the entry point
            Home home = new Home();
            home.setVisible(true);
        });
    }
}