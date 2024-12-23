package Repository;

import Model.Rent;
import Util.DateTime;
import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class RentRepository {

    public ArrayList<Rent> getListRent(){
        ArrayList<Rent> rentList = new ArrayList<>();
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM renting";
                ResultSet res = cn.prepareStatement(queries).executeQuery();
                while (res.next()){
                    Rent rent = new Rent(res);
                    rentList.add(rent);
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rentList;
    }

    public void rentMovie(int itemID, int userID, int rentQuantity, int rentingStatusID, long numberOfDayRent){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "INSERT INTO renting (itemID, userID, rentQuantity, rentingStatusID, rentDate, numberOfDayRent, createdAt, updatedAt)\n" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setInt(1, itemID);
                ps.setInt(2, userID);
                ps.setInt(3, rentQuantity);
                ps.setInt(4, rentingStatusID);
                ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
                ps.setLong(6, numberOfDayRent);
                ps.setTimestamp(7,  new java.sql.Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(8,  new java.sql.Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateRentingStatus(int rentId, int statusId) {
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null) {
                String query = "UPDATE renting SET rentingStatusID = ? WHERE rentID = ?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setInt(1, statusId);
                ps.setInt(2, rentId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isEmpty(){
        Connection cn = SqlConnection.getConnection();
        return cn == null;
    }

}
