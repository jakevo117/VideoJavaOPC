package Controller;

import Model.*;
import Service.*;
import Util.Reader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreController {
    private UserService userService;
    private ItemService itemService;
    private RentingService rentingService;
    private StorageService storageService;
    private CategoryService categoryService;

    private Reader reader = Reader.getReader();

    public StoreController() {
        this.userService = new UserService();
        this.itemService = new ItemService();
        this.rentingService = new RentingService();
        this.storageService = new StorageService();
        this.categoryService = new CategoryService();
    }

    public void addUser() {
        String userName = "";
        String email = "";
        String password = "";
        Date birthday;

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
            int findUser = reader.getNumber("Enter the ID of user you want to find: ");
            User foundUser = userService.searchUser(findUser);
            if (foundUser == null) {
                throw new Exception("User not found");
            }
            System.out.println("Searching user....");
            System.out.println();

            System.out.println("===== User information =====");
            foundUser.printUserInfo();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkExist() {
        try {
            if (userService.checkEmpty()) {
                throw new Exception("The user list is empty");
            }

            int searchUser = reader.getNumber("Enter the username you want to find: ");
            System.out.println("Searching user....");

            boolean isExist = userService.checkUserId(searchUser);
            if (!isExist) {
                throw new Exception("User Id not found");
            }
            System.out.println("User Id found");
        }  catch (Exception e) {
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
                int deleteUser = reader.getNumber("Enter the ID of user you want to delete: ");
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

            int userToEdit = reader.getNumber("Enter the ID of user you want to edit: ");
            User foundUser = userService.searchUser(userToEdit);
            if (foundUser == null) {
                throw new Exception("User not found");
            }

            System.out.println("What would you want to change: ");
            System.out.println("1. Email    2. Date of birth    3. Cancel change");

            ArrayList<Integer> listChoices = new ArrayList<Integer>();
            Collections.addAll(listChoices, 1, 2, 3);

            int choice = reader.getNumberChoice("Input your choice: ", listChoices);

            switch (choice) {
                case 1 -> {
                    String userEmail = reader.getNonEmptyString("Enter new email: ");
                    if (!userService.checkExist(userEmail, "email")) {
                        userService.editUserInfo(foundUser, "email", userEmail);
                    }
                }
                case 2 -> {
                    String birthdayStr = reader.getNonEmptyString("Enter the new date of birth (yyyy-mm-dd): ");
                    userService.editUserInfo(foundUser, "birthDate", birthdayStr);
                }
                case 3 -> throw new Exception("Cancelling change");
            }

            System.out.println("User stat changed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItem() {
        try {
            String inputTitle = "";
            double inputPrice = 0.0;
            int defaultQuantity = 0;
            int selectCateIndex = 0;

            inputTitle = reader.getNonEmptyString("Input movie title: ");

            List<Category> listCategory = categoryService.getListCategory();
            for (int i = 0; i< listCategory.size(); i++) {
                System.out.print(i+1 + ". " + listCategory.get(i).getCategoryName()+ "     ");
            }
            System.out.println();
            selectCateIndex = reader.getNumber("Input the movie category index: ");
            int categoryID = listCategory.get(selectCateIndex - 1).getCategoryID();

            defaultQuantity = reader.getNumber("Input default quantity: ");
            inputPrice = reader.getDoubleNumber("Input the price of the movie: ");
            itemService.addItem(inputTitle, categoryID, inputPrice);

            int storageCode = reader.getNumber("Enter code: ");
            System.out.println(itemService.getItemIdByMovieTitle(inputTitle));
            storageService.addQuantityWithCode(itemService.getItemIdByMovieTitle(inputTitle), storageCode,defaultQuantity);

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

            int searchItem = reader.getNumber("Enter the movie ID you want to find: ");
            System.out.println();

            Item item = itemService.searchItem(searchItem);
            if (item == null) {
                throw new Exception("The movie is not exist");
            }
            System.out.println("===== Movie information =====");
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
                int deleteItem = reader.getNumber("Enter the ID of movie you want to delete: ");
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

            int itemToEdit = reader.getNumber("Enter the ID of movie you want to edit: ");
            Item foundItem = itemService.searchItem(itemToEdit);
            if (foundItem == null) {
                throw new Exception("Item not found");
            }

            System.out.println("What would you want to change: ");
            System.out.println("1. Title     2. Category    3. Price    4. Cancel change");

            ArrayList<Integer> listItemChoice = new ArrayList<Integer>();
            Collections.addAll(listItemChoice, 1, 2, 3, 4);
            System.out.println();
            int choice = reader.getNumberChoice("Input your choice: ", listItemChoice);

            switch (choice) {
                case 1 -> {
                    String valueToEditString = reader.getNonEmptyString("Input the new title to the movie: ");
                    itemService.editItemInfo(foundItem, "title", valueToEditString, 0, 0.0);
                    System.out.println("Movie title changed successfully");
                }
                case 2 -> {
                    List<Category> listCategory = categoryService.getListCategory();
                    for (int i = 0; i< listCategory.size(); i++) {
                        System.out.print(i+1 + ". " + listCategory.get(i).getCategoryName()+ "     ");
                    }
                    System.out.println();

                    int newCategoryID = reader.getNumber("Enter the category ID you want to change to: ");
                    itemService.editItemInfo(foundItem, "categoryID", null, newCategoryID, 0.0);
                    System.out.println("Category changed successfully");
                }
                case 3 -> {
                    double newPrice = reader.getDoubleNumber("Enter the price you want to change: ");
                    itemService.editItemInfo(foundItem, "itemPrice", null, 0, newPrice);
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

            int movieAdd = reader.getNumber("Which movie do you want to add to: ");

            if (itemService.searchItem(movieAdd) == null) {
                throw new Exception("Movie not found");
            }

            int itemQuantityAdd = reader.getNumber("How many copies do you want to add: ");
            int code = reader.getNumber("Enter code: ");
            int typeID = 3;

            storageService.addQuantity(movieAdd, code, typeID, itemQuantityAdd);
            System.out.println("Added successfully");

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

            int rentMovieID = reader.getNumber("Enter the movie ID you want to rent: ");
            Item itemToRent = itemService.searchItem(rentMovieID);
            if (itemToRent == null){
                throw new Exception("Movie not found");
            }

            int numberOfRent = reader.getNumber("How many copies of the movie you want to rent: ");
            if (numberOfRent > storageService.getItemQuantityByItemId(itemToRent.getItemId()) || storageService.getItemQuantityByItemId(itemToRent.getItemId()) == 0){
                throw new Exception("The storage does not have enough copies");
            }
            else if (numberOfRent == 0){
                throw new Exception("Cannot rent 0 copies");
            }
            long rentingDay = reader.getLongNumber("How many days you want to rent: ");

            rentingService.rentItem(itemToRent.getItemId(), userId, numberOfRent, rentingDay);
            System.out.println("Movie renting request sent successfully");
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

            System.out.println("What status you want to change to ?");
            System.out.println("1. Rent Item      2. Return Item");
            int choice = reader.getNumber("Input your choice: ");

            switch (choice){
                case 1 -> {
                    int rentId = reader.getNumber("Input the rent ID you want to change the status: ");
                    int itemId = rentingService.getItemIdByRentId(rentId);

                    int storageAmount = storageService.getItemQuantityByItemId(itemId);
                    boolean status = rentingService.changeStatus(rentId, storageAmount);

                    if (status){
                        for (Rent rent: rentingService.getRentList()){
                            if (rentId == rent.getRentId()){
                                int typeID = 1;
                                int code = reader.getNumber("Enter code: ");
                                storageService.deductQuantity(rent.getItemID(), code, typeID, rent.getRentQuantity());
                            }
                        }
                        System.out.println("Status changed successfully");
                        System.out.println();
                    } else {
                        throw new Exception("The storage does not have enough copies. ");
                    }
                }
                case 2 -> {
                    int rentId = reader.getNumber("Input the rent ID you want to change the status: ");

                    for (Rent rent: rentingService.getRentList()){
                        if (rentId == rent.getRentId()){
                            int code = reader.getNumber("Enter code: ");
                            int typeID = 2;
                            rentingService.changeCompleted(rentId);
                            storageService.addQuantity(rent.getItemID(), code, typeID, rent.getRentQuantity());
                        }
                    }
                    System.out.println("Status changed successfully");
                    System.out.println();
                }
            }


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addCategory(){
        String newCate = reader.getNonEmptyString("Add new category: ");
        categoryService.addNewCategory(newCate);
    }
}