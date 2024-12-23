package Model;

import java.sql.ResultSet;

public class Category {
    private int categoryID;
    private String categoryName;

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public Category(ResultSet rs) {
        try {
            this.categoryID = rs.getInt("categoryID");
            this.categoryName = rs.getString("categoryName");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return
                categoryID +
                ". " + categoryName + '\'';
    }
}
