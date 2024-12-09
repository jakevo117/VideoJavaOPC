package Model;

import java.time.LocalDate;
import java.util.Date;

public class Rent {
    private Item item;
    private int userId;
    private int rentId;
    private int rentQuantity;
    private LocalDate rentDate;
    private long returnDate;

    public Rent(int userId, Item item, int rentQuantity, long returnDate) {
        this.item = item;
        this.userId = userId;
        this.rentQuantity = rentQuantity;
        this.rentId = rentId;
        this.rentDate = LocalDate.now();
        this.returnDate = returnDate;
    }

    public Item getItem() {
        return item;
    }
    public int getUserId() {
        return userId;
    }

    public int getRentId() {
        return rentId;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getRentQuantity() {
        return rentQuantity;
    }

    public void setRentQuantity(int rentQuantity) {
        this.rentQuantity = rentQuantity;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(long returnDate) {
        this.returnDate = returnDate;
    }

    public void printRentList (){
        System.out.println("User ID: " + userId);
        System.out.println("Number of copies: " + rentQuantity);
        System.out.println("Rent movie: " + item.getTitle());
        System.out.println("Rent date: " + rentDate);
        System.out.println("Return date: " + rentDate.plusDays(returnDate));
    }
}
