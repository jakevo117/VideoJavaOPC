package Model;

import java.util.Date;

public class Rent {
    private Item item;
    private Date rentDate;
    private Date returnDate;

    public Rent(Item item, Date rentDate, Date returnDate) {
        this.item = item;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
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
