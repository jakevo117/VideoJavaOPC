package Repository;
import Model.User;
import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class UserRepository {
    public ArrayList<User> getListUser(){
        ArrayList<User> userList = new ArrayList<>();
        try{
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM store_user";
                ResultSet res = cn.prepareStatement(queries).executeQuery();
                while (res.next()){
                    User user = new User(res);
                    userList.add(user);
                    }
                }

            } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public User getUserById(int userID){
        User user = null;
        try{
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM store_user WHERE userID = ?";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setInt(1, userID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    user = new User(rs);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void addNewUser(String userName, String password, String email, Date birthDate){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "INSERT INTO store_user (userName, password, email, birthDate, createdAt, updatedAt)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?);";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setDate(4, birthDate);
                ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(User userToDelete){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "DELETE FROM store_user\n" +
                        "WHERE userID = ?";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setInt(1, userToDelete.getUserId());
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void EditUserInfoDB(User userToEdit, String fieldCase, String newValue){
        try {
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "UPDATE store_user\n" +
                        "SET " + fieldCase + "= ?\n" +
                        "WHERE userID = ?;";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setString(1, newValue);
                ps.setInt(2, userToEdit.getUserId());
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
