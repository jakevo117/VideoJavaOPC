package Model;

import java.time.LocalDate;
import java.util.Date;

public class Storage {
    private int itemId;
    private int quantity;
    private LocalDate itemAddDate;

    public Storage(){

    }
    public Storage(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemAddDate = LocalDate.now();
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

    public LocalDate getItemAddDate() {
        return itemAddDate;
    }

    public void setItemAddDate(LocalDate itemAddDate) {
        this.itemAddDate = itemAddDate;
    }

    public void printStorageInfo(){
        System.out.println("Movie ID: " + itemId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Add date: " + itemAddDate);
    }
}
