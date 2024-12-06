package Model;

public class Item {
    private int itemId;
    private String title;
    private Category category;
    private Boolean isAvailable;
    private double price;
    private int quantity;
    public static int newItemId = 0;
    public Item(String title, Category category, double price, int quantity) {
        this.itemId = ++newItemId;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public Item(String title, Category category, double price, int quantity, Boolean isAvailable) {
        this(title, category, price, quantity);
        this.isAvailable = true;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        if (isAvailable){
            System.out.println("Status: In-stock");
            System.out.println("Quantity: " + quantity);
        }
        else {
            System.out.println("Status: Out-of-stock");
        }
        System.out.println("Price: " + price);
        System.out.println();
    }
}
