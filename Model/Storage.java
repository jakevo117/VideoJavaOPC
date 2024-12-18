package Model;

import Util.DateTime;
import java.time.LocalDateTime;

public class Storage {
    private int itemId;
    private int code;
    private Type type;
    private int quantity;
    private LocalDateTime itemDate;

    public Storage(int itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemDate = LocalDateTime.now();
    }
    public Storage(int itemId, int code, Type type, int quantity) {
        this(itemId, quantity);
        this.code = code;
        this.type = type;
        this.itemDate = LocalDateTime.now();
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
        System.out.println("Add datetime: " + itemDate);
        System.out.println();
    }
}
