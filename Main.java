import Controller.StoreController;
import Util.Reader;
import Util.SqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        StoreController user = new StoreController();

        System.out.println("                                            ==========================");
        System.out.println("                                            Welcome to the video store");

        Reader reader = Reader.getReader();
        while (true){
            System.out.println("                                            What would you want to do: ");
            System.out.println("                                            ===========================");
            System.out.println("1.  Add User                2.  Delete User      3.  Search User      4.  Edit User Information      5.  View All User Information");
            System.out.println("6.  Add Movie               7.  Delete Movie     8.  Search Movie     9.  Edit Movie Information     10. View All Movie Information");
            System.out.println("11. Add movie quantity      12. Rent a movie     13. View Storage     14. Change renting status      15. View Renting List          ");
            System.out.println("16.    ");
            System.out.print("\n");
            System.out.println("                                                                                                     17. EXIT");

            int option = reader.getNumber("Input your choice: ");
            switch (option) {
                case 1 -> user.addUser();
                case 2 -> user.deleteUser();
                case 3 -> user.searchUser();
                case 4 -> user.editUserInfo();
                case 5 -> user.listUserInfo();
//                case 6 -> user.addItem();
//                case 7 -> user.deleteItem();
//                case 8 -> user.searchMovie();
//                case 9 -> user.editItemInfo();
//                case 10 -> user.listItem();
//                case 11 -> user.addItemQuantity();
//                case 12 -> user.rentMovie();
//                case 13 -> user.listStorage();
//                case 14 -> user.statusChange();
//                case 15 -> user.rentingList();
                case 17 -> {
                    System.out.println("Exiting....");
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose again");
            }
        }
    }
}
