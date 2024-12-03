package Model;

public class Item {
    private String title;
    private Category category;
    private Boolean isAvailable;
    private double price;

    public Item(String title, Category category, Boolean isAvailable, double price) {
        this.title = title;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        this.isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
