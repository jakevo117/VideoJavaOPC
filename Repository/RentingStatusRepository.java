package Repository;

import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class RentingStatusRepository {
    public Map<Integer, String> getRentingStatus() {
        Map<Integer, String> databaseRentStatus = new HashMap<>();
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null) {
                String query = "SELECT * FROM renting_status";
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int rentStatusID = rs.getInt("rentingStatusID");
                    String rentStatusName = rs.getString("rentingStatusName");
                    databaseRentStatus.put(rentStatusID, rentStatusName);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return databaseRentStatus;
    }
}
