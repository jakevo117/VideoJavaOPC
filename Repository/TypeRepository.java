package Repository;

import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class TypeRepository {
    public Map<Integer, String> getTypeList() {
        Map<Integer, String> databaseType = new HashMap<>();
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null) {
                String query = "SELECT * FROM activity_type";
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int typeID = rs.getInt("typeID");
                    String typeLabel = rs.getString("typeLabel");
                    databaseType.put(typeID, typeLabel);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return databaseType;
    }
}
