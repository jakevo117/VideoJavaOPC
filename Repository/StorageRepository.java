package Repository;

import Model.Storage;
import Util.Capitalize;
import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StorageRepository {
    public ArrayList<Storage> getListStorage(){
        ArrayList<Storage> storageList = new ArrayList<>();
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM store_storage";
                ResultSet res = cn.prepareStatement(queries).executeQuery();
                while (res.next()){
                    Storage storage = new Storage(res);
                    storageList.add(storage);
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return storageList;
    }

    public void addNewQuantity(int itemID, int code, int typeID, int itemQuantity){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "INSERT INTO store_storage (itemID, code, typeID, itemQuantity, itemAmountChangeDay, createdAt, updatedAt)\n" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setInt(1, itemID);
                ps.setInt(2, code);
                ps.setInt(3, typeID);
                ps.setInt(4, itemQuantity);
                ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isEmpty(){
        Connection cn = SqlConnection.getConnection();
        return cn == null;
    }
}
