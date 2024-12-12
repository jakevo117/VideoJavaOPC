package Model;

import Util.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rent {
    private Item item;
    private int userId;
    private int rentId;
    private int rentQuantity;
    private RentingStatus status;
    private LocalDate rentDate;
    private long returnDate;
    private LocalDateTime dateTimeRent;

    public Rent(int rentId, int userId, Item item, int rentQuantity, long returnDate, RentingStatus status) {
        this.rentId = rentId;
        this.item = item;
        this.userId = userId;
        this.rentQuantity = rentQuantity;
        this.rentDate = LocalDate.now();
        this.dateTimeRent = LocalDateTime.now();
        this.status = status;
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

    public RentingStatus getStatus() {
        return status;
    }

    public void setStatus(RentingStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTimeRent() {
        return dateTimeRent;
    }

    public void setDateTimeRent(LocalDateTime dateTimeRent) {
        this.dateTimeRent = dateTimeRent;
    }

    public void printRentList (){
        System.out.println("Rent ID: " + rentId);
        System.out.println("User ID: " + userId);
        System.out.println("Number of copies rent: " + rentQuantity);
        System.out.println("Rent movie: " + item.getTitle());
        if (status == RentingStatus.PAID){
            System.out.println("Status: Paid");
            System.out.println("Rent date: " + rentDate);
            System.out.println("Return date: " + rentDate.plusDays(returnDate));
        } else if (status == RentingStatus.UNPAID) {
            System.out.println("Status: Unpaid");
            System.out.println("Rent request date and time: " + DateTime.formatDateTime(dateTimeRent));
        } else {
            System.out.println("Status: Completed");
            System.out.println("Return date time: " + DateTime.formatDateTime(dateTimeRent));
        }
        System.out.println();
    }
}
