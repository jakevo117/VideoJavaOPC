package Model;

public class Item {
    private int itemId;
    private String title;
    private Category category;
    private Boolean isAvailable;
    private double price;

    public Item(int itemId, String title, Category category, double price) {
        this.itemId = itemId;
        this.title = title;
        this.category = category;
        this.price = price;
        this.isAvailable = false;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        title = capitalizeWord(title);
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

    public static String capitalizeWord(String input){
        String[] words = input.split("\\s");
        StringBuilder result = new StringBuilder();
        for (String word: words){
            result.append(Character.toTitleCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return result.toString().trim();
    }
    public void printItemInfo(){
        title = capitalizeWord(title);
        System.out.println("Movie ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Category: " + category);
        System.out.println("Price: " + price);
        System.out.println();
    }

    public void printItemInfo(int quantity){
        title = capitalizeWord(title);
        System.out.println("Movie ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Category: " + category);
        System.out.println("Price: " + price);
        if (quantity > 0){
            System.out.println("In-stock");
            System.out.println("Quantity: " + quantity);
        } else {
            System.out.println("Out of stock");
            System.out.println("Quantity: " + quantity);
        }

        System.out.println();
    }
}
