package Model;

import Util.Capitalize;
import java.sql.ResultSet;

public class Item {
    private int itemId;
    private String title;
    private int categoryID;
    private String categoryName;
    private Boolean isAvailable;
    private double price;

    public Item(int itemId, String title, int categoryID, double price) {
        this.itemId = itemId;
        this.title = title;
        this.categoryID = categoryID;
        this.price = price;
        this.isAvailable = false;
    }

    public Item (ResultSet res, Category category){
        try {
            this.categoryName = category.getCategoryName();
            this.itemId = res.getInt("itemID");
            this.title = res.getString("title");
            this.categoryID = res.getInt("categoryID");
            this.price = res.getFloat("itemPrice");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Item (ResultSet res){
        try {
            this.itemId = res.getInt("itemID");
            this.title = res.getString("title");
            this.categoryID = res.getInt("categoryID");
            this.price = res.getFloat("itemPrice");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        title = Capitalize.capitalizeWord(title);
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategory(int categoryID) {
        this.categoryID = categoryID;
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


    public void printItemInfo(){
        title = Capitalize.capitalizeWord(title);
        System.out.println("Movie ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("CategoryID: " + categoryID);
        System.out.println("Category name: " + categoryName);
        System.out.println("Price: " + String.format("%.2f", price));
        System.out.println();
    }

    public void printItemInfoDetail(int quantity){
        title = Capitalize.capitalizeWord(title);
        System.out.println("Movie ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("CategoryID: " + categoryID);
        if (categoryName != null){
            System.out.println("Category name: " + categoryName);
        }
        System.out.println("Price: " + String.format("%.2f", price));
        System.out.println();
    }
}
