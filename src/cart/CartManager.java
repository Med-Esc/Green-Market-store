package cart;

import java.util.*;

public class CartManager {
    private static CartManager instance;
    private final List<CartItem> items;
    private final Set<CartUpdateListener> listeners;

    private CartManager() {
        items = new ArrayList<>();
        listeners = new HashSet<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        // Check if item already exists in cart
        for (CartItem cartItem : items) {
            if (cartItem.getId().equals(item.getId())) {
                cartItem.incrementQuantity();
                notifyListeners();
                return;
            }
        }
        // If not, add new item
        items.add(item);
        notifyListeners();
    }

    public void removeItem(String itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
        notifyListeners();
    }

    public void updateQuantity(String itemId, int quantity) {
        for (CartItem item : items) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        notifyListeners();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        return items.stream()
                   .mapToDouble(CartItem::getTotalPrice)
                   .sum();
    }

    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
        notifyListeners();
    }

    public void addCartUpdateListener(CartUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeCartUpdateListener(CartUpdateListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (CartUpdateListener listener : listeners) {
            listener.onCartUpdated();
        }
    }

    public interface CartUpdateListener {
        void onCartUpdated();
    }
}
