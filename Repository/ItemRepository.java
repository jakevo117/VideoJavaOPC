package Repository;

import Model.Category;
import Model.Item;
import Util.Capitalize;
import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemRepository {
    public ArrayList<Item> getListItemDetail(){
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT itemID, title, store_item.categoryID, categoryName, itemPrice\n" +
                        "FROM store_item\n" +
                        "JOIN category\n" +
                        "ON store_item.categoryID = category.categoryID";
                ResultSet res = cn.prepareStatement(queries).executeQuery();
                while (res.next()){
                    Category category = new Category(res);
                    Item item = new Item(res, category);
                    itemList.add(item);
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return itemList;
    }

    public ArrayList<Item> getListItem(){
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM store_item";
                ResultSet res = cn.prepareStatement(queries).executeQuery();
                while (res.next()){
                    Item item = new Item(res);
                    itemList.add(item);
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return itemList;
    }

    public Item getItemById(int itemID){
        Item item = null;
        try{
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM store_item WHERE itemID = ?";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setInt(1, itemID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    item = new Item(rs);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return item;
    }

    public void addNewItem(String title, int categoryID, double price){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "INSERT INTO store_item (title, categoryID, itemPrice, createdAt, updatedAt)\n" +
                        "VALUES (?, ?, ?, ?, ?);";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setString(1, Capitalize.capitalizeWord(title));
                ps.setInt(2, categoryID);
                ps.setDouble(3, price);
                ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeItemById(Item itemToDelete){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "DELETE FROM store_item\n" +
                        "WHERE itemID = ?";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setInt(1, itemToDelete.getItemId());
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void EditItemInfoDB(Item itemToEdit, String fieldCase, String newValue, int newCategoryID, double newPrice){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "UPDATE store_item\n" +
                        "SET " + fieldCase + "= ?\n" +
                        "WHERE itemID = ?;";
                PreparedStatement ps = cn.prepareStatement(queries);
                if (fieldCase.equalsIgnoreCase("title")){
                    ps.setString(1, Capitalize.capitalizeWord(newValue));
                } else if (fieldCase.equalsIgnoreCase("categoryID")){
                    ps.setInt(1, newCategoryID);
                } else {
                    ps.setDouble(1, newPrice);
                }
                ps.setInt(2, itemToEdit.getItemId());
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isEmpty(){
        Connection cn = SqlConnection.getConnection();
        return cn == null;
    }
}
