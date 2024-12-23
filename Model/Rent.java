package Model;

import Util.DateTime;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Rent {
    private int rentId;
    private int itemID;
    private int userId;
    private int rentQuantity;
    private int rentingStatusID;
    private LocalDate rentDate;
    private long returnDate;

    public Rent(int rentId, int userId, int itemID, int rentQuantity, long returnDate, int rentingStatusID) {
        this.rentId = rentId;
        this.itemID = itemID;
        this.userId = userId;
        this.rentQuantity = rentQuantity;
        this.rentingStatusID = rentingStatusID;
        this.rentDate = LocalDate.now();
        this.returnDate = returnDate;
    }

    public Rent(ResultSet rs) {
        try {
            this.rentId = rs.getInt("rentID");
            this.itemID = rs.getInt("itemID");
            this.userId = rs.getInt("userID");
            this.rentQuantity = rs.getInt("rentQuantity");
            this.rentingStatusID = rs.getInt("rentingStatusID");
            this.rentDate = rs.getDate("rentDate").toLocalDate();
            this.returnDate = rs.getLong("numberOfDayRent");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getItemID() {
        return itemID;
    }
    public int getUserId() {
        return userId;
    }

    public int getRentId() {
        return rentId;
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

    public int getRentingStatusID() {
        return rentingStatusID;
    }

    public void setRentingStatusID(int rentingStatusID) {
        this.rentingStatusID = rentingStatusID;
    }

//    public LocalDateTime getDateTimeRent() {
//        return dateTimeRent;
//    }
//
//    public void setDateTimeRent(LocalDateTime dateTimeRent) {
//        this.dateTimeRent = dateTimeRent;
//    }

    public void printRentList (){
        System.out.println("Rent ID: " + rentId);
        System.out.println("User ID: " + userId);
        System.out.println("Rent movieID: " + itemID);
        System.out.println("Number of copies rent: " + rentQuantity);
        if (rentingStatusID == 2){
            System.out.println("Status: Paid");
            System.out.println("Rent date: " + DateTime.formatDate(rentDate));
            System.out.println("Return date: " + DateTime.formatDate(rentDate.plusDays(returnDate)));
        } else if (rentingStatusID == 1) {
            System.out.println("Status: Unpaid");
            System.out.println("Rent request date and time: " + DateTime.formatDate(rentDate));
        } else {
            System.out.println("Status: Completed");
            System.out.println("Return date time: " + DateTime.formatDate(rentDate));
        }
        System.out.println();
    }
}
