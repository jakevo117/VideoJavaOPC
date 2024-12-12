package Model;

import Util.DateTime;
import java.time.LocalDateTime;

public class Storage {
    private int itemId;
    private int code;
    private Type type;
    private int quantity;
    private boolean addRemove;
    private LocalDateTime itemAddDate;
    private LocalDateTime itemRemoveDate;

    public Storage(int itemId, int quantity, boolean addRemove){
        this.itemId = itemId;
        this.quantity = quantity;
        this.addRemove = addRemove;
        if (addRemove){
            this.itemAddDate = LocalDateTime.now();
        } else {
            this.itemRemoveDate = LocalDateTime.now();
        }
    }
    public Storage(int itemId, int code, Type type, int quantity, boolean addRemove) {
        this(itemId, quantity, addRemove);
        this.code = code;
        this.type = type;
        if (addRemove){
            this.itemAddDate = LocalDateTime.now();
        } else {
            this.itemRemoveDate = LocalDateTime.now();
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
        return itemAddDate;
    }

    public void setItemAddDate(LocalDateTime itemAddDate) {
        this.itemAddDate = itemAddDate;
    }

    public LocalDateTime getItemRemoveDate() {
        return itemRemoveDate;
    }

    public void setItemRemoveDate(LocalDateTime itemRemoveDate) {
        this.itemRemoveDate = itemRemoveDate;
    }

    public void printStorageInfo(){
        System.out.println("Movie ID: " + itemId);
        if (quantity > 0) {
            System.out.println("Quantity: " + quantity);
        } else {
            System.out.println("Quantity: " + quantity);
        }
        if (this.addRemove){
            String addDateTime = DateTime.formatDateTime(itemAddDate);
            System.out.println("Add datetime: " + addDateTime);
        } else {
            String removeDateTime = DateTime.formatDateTime(itemRemoveDate);
            System.out.println("Remove datetime: " + removeDateTime);
        }
        System.out.println();
    }
}
