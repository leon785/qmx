package homework.javabasic;

import java.util.ArrayList;
import java.util.List;


public class User {
    private int id;
    private int creditLeft;
    private List<Product> purchased;

    public User(int id, int creditLeft) {
        this.id = id;
        this.creditLeft = creditLeft;
        this.purchased = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreditLeft() {
        return creditLeft;
    }

    public void setCreditLeft(int creditLeft) {
        this.creditLeft = creditLeft;
    }

    public List<Product> getPurchased() {
        return purchased;
    }

    public void addPurchased(Product product) {
        purchased.add(product);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", creditLeft=" + creditLeft +
                ", purchased=" + purchased +
                '}';
    }
}
