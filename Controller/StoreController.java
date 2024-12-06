package Controller;

import Model.Category;
import Model.Item;
import Model.User;
import Service.ItemService;
import Service.UserService;
import Util.Reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreController {
    private UserService userService;
    private ItemService itemService;

    private Reader reader = Reader.getReader();

    public StoreController() {
        this.userService = new UserService();
        this.itemService = new ItemService();
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
                if (userService.checkExist(email, "email")) {
                    throw new Exception("Email is already existed. Please choose another one.");
                }

                password = reader.getNonEmptyString("Input password: ");
                birthday = reader.getValidDate("Input date of birth ");

                userService.addUser(userName, password, email, birthday);
                System.out.println("User is created successfully");
                System.out.println();
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void listUserInfo() {
        try {
            if (userService.checkEmpty()) {
                throw new Exception("The user list is empty");
            }
            System.out.println();
            System.out.println("=== The user list ===");
            userService.listUserInfo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchUser() {
        try {
            if (userService.checkEmpty()) {
                throw new Exception("The user list is empty");
            }
            String searchUser = reader.getNonEmptyString("Enter the username you want to find: ");
            System.out.println("Searching user....");

            User user = userService.searchUser(searchUser);
            if (user == null) {
                throw new Exception("The user is not exist");
            }
            user.printUserInfo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser() {
        if (userService.checkEmpty()) {
            System.out.println("The user list is empty");
            return;
        }
        while (true) {
            try {
                String deleteUser = reader.getNonEmptyString("Enter the username of user you want to delete: ");
                User foundUser = userService.searchUser(deleteUser);
                if (foundUser == null) {
                    throw new Exception("User not found");
                }

                boolean isAccept = reader.getConfirm();
                if (isAccept) {
                    userService.deleteUser(foundUser);
                    System.out.println("User is removed successfully");
                } else {
                    System.out.println("Cancelling deletion");
                }
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void editUserInfo() {
        try {
            if (userService.checkEmpty()) {
                throw new Exception("The user list is empty");
            }

            String userToEdit = reader.getNonEmptyString("Enter the username of user you want to edit: ");
            User foundUser = userService.searchUser(userToEdit);
            if (foundUser == null) {
                throw new Exception("User not found");
            }

            System.out.println("What would you want to change: ");
            System.out.println("1. Email    2. Date of birth    3. Cancel change");

            List<Integer> listChoices = new ArrayList<Integer>();
            Collections.addAll(listChoices, 1, 2, 3);

            int choice = reader.getNumberChoice("Input your choice: ", listChoices);

            switch (choice) {
                case 1 -> {
                    String userEmail = reader.getNonEmptyString("Enter new email: ");
                    if (!userService.checkExist(userEmail, "email")) {
                        foundUser.setEmail(userEmail);
                    }
                }
                case 2 -> {
                    String birthdayStr = reader.getNonEmptyString("Enter the new date of birth (yyyy-mm-dd): ");
                    foundUser.setBirthDate(LocalDate.parse(birthdayStr));
                }
                case 3 -> throw new Exception("Cancelling change");
            }

            userService.editUserInfo(foundUser);
            System.out.println("User stat changed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItem() {
        try {
            String inputTitle = "";
            String inputCategory = "";
            double inputPrice = 0.0;
            int inputQuantity = 0;

            inputTitle = reader.getNonEmptyString("Input movie title: ");
            if (itemService.checkExistItem(inputTitle)) {
                System.out.println("The movie is already in the store");
                inputQuantity = reader.getNumber("Input the number of copies you want to add: ");
                itemService.addQuantity(inputQuantity, inputTitle);
                System.out.println(inputQuantity + " copies added successfully");
                System.out.println();

            } else {
                inputCategory = reader.getNonEmptyString("Input the movie category (Default is GENERAL): ");
                Category selectCate = Category.getCateOrDefault(inputCategory);
                if (selectCate == Category.GENERAL) {
                    System.out.println("Cannot found category. Defaulting to GENERAL");
                }
                inputPrice = reader.getDoubleNumber("Input the price of the movie: ");
                inputQuantity = reader.getNumber("Input the quantity of the movie: ");
                itemService.addItem(inputTitle, selectCate, inputPrice, inputQuantity);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchMovie() {
        try {
            if (itemService.checkEmptyList()) {
                throw new Exception("The movie list is empty");
            }

            String searchItem = reader.getNonEmptyString("Enter the movie title you want to find: ");
            System.out.println();

            Item item = itemService.searchItem(searchItem);
            if (item == null) {
                throw new Exception("The movie is not exist");
            }
            item.printItemInfo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listItem() {
        try {
            if (itemService.checkEmptyList()) {
                throw new Exception("The movie list is empty");
            }
            System.out.println();
            System.out.println("=== The movie list ===");
            itemService.listItem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteItem() {
        if (itemService.checkEmptyList()) {
            System.out.println("The movie list is empty");
            return;
        }
        while (true) {
            try {
                String deleteItem = reader.getNonEmptyString("Enter the title of movie you want to delete: ");
                Item foundItem = itemService.searchItem(deleteItem);
                if (foundItem == null) {
                    throw new Exception("Item not found");
                }

                boolean isAccept = reader.getConfirm();
                if (isAccept) {
                    itemService.deleteItem(foundItem);
                    System.out.println("Item is removed successfully");
                } else {
                    System.out.println("Cancelling deletion");
                }
                return;
                }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void editItemInfo() {
        try {
            if (itemService.checkEmptyList()) {
                throw new Exception("The movie list is empty");
            }

            String itemToEdit = reader.getNonEmptyString("Enter the title of movie you want to edit: ");
            Item foundItem = itemService.searchItem(itemToEdit);
            if (foundItem == null) {
                throw new Exception("Item not found");
            }

            System.out.println("What would you want to change: ");
            System.out.println("1. Title     2. Category    3. Price    4.Quantity    5. Cancel change");

            List<Integer> listItemChoice = new ArrayList<Integer>();
            Collections.addAll(listItemChoice, 1, 2, 3);
            System.out.println();

            int choice = reader.getNumberChoice("Input your choice: ", listItemChoice);
            itemService.editItemInfo(foundItem);
            switch (choice) {
                case 1 -> {
                    String newTitle = reader.getNonEmptyString("Input the new title to the movie: ");
                    foundItem.setTitle(newTitle);
                    System.out.println("Movie title changed successfully");
                }
                case 2 -> {
                    String category = reader.getNonEmptyString("Enter the category you want to change to (Default is GENERAL): ");
                    Category newCate = Category.getCateOrDefault(category);
                    if (newCate == Category.GENERAL) {
                        System.out.println("The type cannot be found. Defaulting to GENERAL");
                    }
                    foundItem.setCategory(newCate);
                    System.out.println("Category changed successfully");
                }
                case 3 -> {
                    double newPrice = reader.getDoubleNumber("Enter the price you want to change: ");
                    foundItem.setPrice(newPrice);
                    System.out.println("Price changed successfully.");
                }
                case 4 -> {
                    int newQuantity = reader.getNumber("Enter the quantity you want to change: ");
                    foundItem.setQuantity(newQuantity);
                    System.out.println("Quantity changed successfully.");
                }
                case 5 -> throw new Exception("Cancelling change");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}