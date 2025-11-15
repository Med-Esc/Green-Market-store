package cart;

public class CartItem {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final String imagePath;
    private int quantity;

    public CartItem(String id, String name, String description, double price, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = 1;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return price * quantity; }

    // Setters
    public void setQuantity(int quantity) { 
        this.quantity = Math.max(1, quantity);
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }
}
