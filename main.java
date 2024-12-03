import Model.Controller;
import java.time.LocalDate;
import java.util.Scanner;

public class main {
    public static void main(String[] args)
    {
        Controller user = new Controller();
        System.out.println("                                ==========================");
        System.out.println("                                Welcome to the video store");

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("                                What would you want to do: ");
            System.out.println("                                ===========================");
            System.out.println("1. Add User     2. Delete User      3. Search User      4. Edit User Information       5. View User Information");
            System.out.println("6. Add Item     7. Delete Item      8. Search Item      9. View Transaction            10. Exit the store");
            System.out.print("Input your choice: ");
            String choice = sc.nextLine().trim();

            if (choice.isEmpty()){
                System.out.println("Choice cannot be empty.");
                continue;
            }

            if (!choice.matches("\\d+")){
                System.out.println("Choice cannot be words. Please choose again");
                continue;
            }

            int option = Integer.parseInt(choice);
            switch (option) {
                case 1 -> user.addUser();
                case 2 -> user.deleteUser();
                case 3 -> user.searchUser();
                case 4 -> user.editUserInfo();
                case 5 -> user.listUserInfo();
                case 10 -> {
                    System.out.println("Exiting....");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose again");
            }
        }
    }
}
