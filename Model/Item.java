package Model;

public class Item {
    private String title;
    private Category category;
    private Boolean isAvailable;

    public Item (){

    }
    public Item(String title, Category category, Boolean isAvailable) {
        this.title = title;
        this.category = category;
        this.isAvailable = true;
    }

    public String getItemID() {
        return title;
    }

    public void setItemID(String itemID) {
        this.title = itemID;
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
        isAvailable = available;
    }
}
