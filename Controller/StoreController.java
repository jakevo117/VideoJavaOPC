package Controller;

import Model.*;
import Service.ItemService;
import Service.RentingService;
import Service.StorageService;
import Service.UserService;
import Util.DateTime;
import Util.Reader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreController {
    private UserService userService;
    private ItemService itemService;
    private RentingService rentingService;
    private StorageService storageService;

    private Reader reader = Reader.getReader();

    public StoreController() {
        this.userService = new UserService();
        this.itemService = new ItemService();
        this.rentingService = new RentingService();
        this.storageService = new StorageService();
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
            int defaultQuantity = 0;

            inputTitle = reader.getNonEmptyString("Input movie title: ");
            inputCategory = reader.getNonEmptyString("Input the movie category (Default is GENERAL): ");

            Category selectCate = Category.getCateOrDefault(inputCategory);
            if (selectCate == Category.GENERAL) {
                System.out.println("Cannot found category. Defaulting to GENERAL");
            }

            defaultQuantity = reader.getNumber("Input default quantity: ");
            inputPrice = reader.getDoubleNumber("Input the price of the movie: ");
            Item addItem = itemService.addItem(inputTitle, selectCate, inputPrice);

            storageService.addQuantity(addItem.getItemId(), defaultQuantity);

            System.out.println("Movie added successfully");
            System.out.println();
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
            List<Item> listItem = itemService.getListItem();
            for (Item item: listItem) {
                int storageQuantity = storageService.getItemQuantityByItemId(item.getItemId());
                item.printItemInfo(storageQuantity);

            }
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
            System.out.println("1. Title     2. Category    3. Price    4. Cancel change");

            List<Integer> listItemChoice = new ArrayList<Integer>();
            Collections.addAll(listItemChoice, 1, 2, 3, 4);
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
                case 4 -> throw new Exception("Cancelling change");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItemQuantity() {
        try {
            if (itemService.checkEmptyList()) {
                throw new Exception("The movie list is empty");
            }

            int movieAdd = itemService.getItemIdByMovieTitle(reader.getNonEmptyString("Which movie do you want to add to: "));
            if (movieAdd < 0) {
                throw new Exception("Movie not found");
            }
            int itemQuantityAdd = reader.getNumber("How many copies do you want to add: ");
            storageService.addQuantity(movieAdd, itemQuantityAdd);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void rentMovie() {
        try {
            if (itemService.checkEmptyList()) {
                throw new Exception("The movie list is empty");
            }

            int userId = reader.getNumber("Input your user ID: ");
            if (!userService.checkUserId(userId)){
                throw new Exception("User ID not found");
            }

            String rentMovie = reader.getNonEmptyString("Enter the movie you want to rent: ");
            Item itemToRent = itemService.searchItem(rentMovie);
            if (itemToRent == null){
                throw new Exception("Movie not found");
            }

            int numberOfRent = reader.getNumber("How many copies of the movie you want to rent: ");
            if (numberOfRent > storageService.getItemQuantityByItemId(itemService.getItemIdByMovieTitle(rentMovie)) || storageService.getItemQuantityByItemId(itemService.getItemIdByMovieTitle(rentMovie)) == 0){
                throw new Exception("The storage does not have enough copies");
            }
            else if (numberOfRent == 0){
                throw new Exception("Cannot rent 0 copies");
            }
            long rentingDay = reader.getLongNumber("How many days you want to rent: ");

            rentingService.rentItem(userId, itemToRent, numberOfRent, rentingDay);
            System.out.println("Movie rented successfully");

            String dateTimeRent = DateTime.formatDateTime(rentingService.getDateAndTimeRent());
            System.out.println(dateTimeRent);
            System.out.println();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void rentingList(){
        try {
            if (rentingService.checkEmptyList()) {
                throw new Exception("The renting list is empty");
            }
            System.out.println();
            System.out.println("=== The renting list ===");
            rentingService.rentList();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void listStorage(){
        try {
            if (storageService.checkEmptyList()) {
                throw new Exception("The storage is empty");
            }

            System.out.println();
            System.out.println("=== The storage ===");
            storageService.listStorage();
            System.out.println();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void statusChange(){
        try {
            if (rentingService.checkEmptyList()){
                throw new Exception("Renting list is empty");
            }

            int rentId = reader.getNumber("Input the rent ID you want to change the status: ");
            int itemId = rentingService.getItemIdByRentId(rentId);
            int storageAmount = storageService.getItemQuantityByItemId(itemId);
            boolean status = rentingService.changeStatus(rentId, storageAmount);

            if (status){
                for (Rent rent: rentingService.getRentList()){
                    if (rentId == rent.getRentId()){
                        storageService.deductQuantity(rent.getItem().getItemId(), rent.getRentQuantity());
                    }
                }
                System.out.println("Status changed successfully");
                System.out.println();
            } else {
                throw new Exception("The storage does not have enough copies. ");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}