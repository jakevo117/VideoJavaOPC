package Repository;
import Model.Category;
import Util.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryRepository {
    private Category categoryRepository;

    public ArrayList<Category> getListCategory(){
        ArrayList<Category> categoryList = new ArrayList<>();
        try{
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "SELECT * FROM category";
                ResultSet res = cn.prepareStatement(queries).executeQuery();
                while (res.next()){
                    Category category = new Category(res);
                    categoryList.add(category);
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return categoryList;
    }

    public void addCategory(String categoryName){
        try{
            Connection cn = SqlConnection.getConnection();
            if (cn != null){
                String queries = "INSERT INTO category(categoryName, createdAt, updatedAt) " +
                        "VALUES(?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(queries);
                ps.setString(1, categoryName);
                ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
