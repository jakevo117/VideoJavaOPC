package Controller;

import Model.User;
import Service.ItemService;
import Service.UserService;
import Util.Reader;

import java.time.LocalDate;

public class StoreController {
    private UserService userService;
    private ItemService itemService;

    private Reader reader = Reader.getReader();
    public StoreController() {
        this.userService = new UserService();
//        this.itemService = new ItemService();
    }

    public void addUser() {
        String userName = "";
        String email = "";
        String password = "";
        LocalDate birthday;

        System.out.println();
        System.out.println("Adding user");

        while (true) {
            try {
                String inputName = userName == "" ? reader.getNonEmptyString("Input username: ") : userName;
                if (userService.checkExist(inputName, "username")) {
                    throw new Exception("Username is already existed. Please choose another one.");
                }
                userName = inputName;

                email = reader.getNonEmptyString("Input email: ");
                if (userService.checkExist(email, "email")){
                    throw new Exception("Email is already existed. Please choose another one.");
                }

                password = reader.getNonEmptyString("Input password: ");
                birthday = reader.getValidDate("Input date of birth ");

                userService.addUser(userName, password, email, birthday);
                System.out.println("User is created successfully");
                System.out.println();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
