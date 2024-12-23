package Model;

import Util.DateTime;

import java.sql.ResultSet;
import java.time.LocalDateTime;

public class Storage {
    private int storageID;
    private int itemId;
    private int code;
    private int typeID;
    private int quantity;
    private LocalDateTime itemDate;

    public Storage(int itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemDate = LocalDateTime.now();
    }
    public Storage(int itemId, int code, int typeID, int quantity) {
        this(itemId, quantity);
        this.code = code;
        this.typeID = typeID;
        this.itemDate = LocalDateTime.now();
    }

    public Storage(ResultSet rs){
        try {
            this.storageID = rs.getInt("storageID");
            this.itemId = rs.getInt("itemID");
            this.code = rs.getInt("code");
            this.typeID = rs.getInt("typeID");
            this.quantity = rs.getInt("itemQuantity");
            this.itemDate = rs.getTimestamp("itemAmountChangeDay").toLocalDateTime();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getItemAddDate() {
        return itemDate;
    }

    public void setItemAddDate(LocalDateTime itemAddDate) {
        this.itemDate = itemAddDate;
    }

    public void printStorageInfo(){
        System.out.println("Movie ID: " + itemId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Add datetime: " + DateTime.formatDateTime(itemDate));
        System.out.println();
    }
}
