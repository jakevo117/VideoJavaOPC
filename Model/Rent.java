package Model;

import java.util.Date;

public class Rent {
    private User user;
    private Item item;
    private Date rentDate;
    private Date returnDate;

    public Rent(){

    }

    public Rent(User user, Item item, Date rentDate, Date returnDate) {
        this.user = user;
        this.item = item;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
