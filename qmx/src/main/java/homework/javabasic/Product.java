package homework.javabasic;

public class Product {
    private int id;
    private String description;
    private int amount;
    private int credit;

    public Product(int id, String description, int amount, int credit) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", credit=" + credit +
                '}';
    }
}
