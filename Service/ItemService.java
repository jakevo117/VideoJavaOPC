package Service;

import Model.Category;
import Model.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemService {
    private ArrayList<Item> availableItems;

    public ItemService(ArrayList<Item> availableItems) {
        this.availableItems = availableItems;
    }

    //    ITEM FUNCTIONS

    //    ADDING ITEM TO THE STORE (TO BE CONTINUING HANDLING BLANK INPUT)
    public void addItem(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Input movie title: ");
        String title = sc.nextLine();
        Item existingItem = searchAnItem(title);

        if (existingItem != null){
            System.out.print("Input the copies quantity of the movie you want to add: ");
            int numberOfMovie = sc.nextInt();
            existingItem.setQuantity(existingItem.getQuantity() + numberOfMovie);
            title = Item.capitalizeWord(title);
            System.out.println("Added " + numberOfMovie + " copies to " + title);
            return;
        }

        Category selectCate = null;

        while (selectCate == null) {
            System.out.print("Input the movie category (Default is GENERAL): ");
            String category = sc.nextLine().toUpperCase();
            if (!category.matches("[a-zA-z]+")){
                System.out.println("Invalid type of movie. Please type again");
                continue;
            }
            selectCate = Category.getCateOrDefault(category);
            if (selectCate == Category.GENERAL){
                System.out.println("The type cannot be found. Defaulting to GENERAL");
            }
        }
        System.out.print("Input price: ");
        double price = sc.nextDouble();
        System.out.print("Input the quantity of the movie: ");
        int quantity = sc.nextInt();

        availableItems.add(new Item(title, selectCate, true, price, quantity));
        System.out.println("New movie added successfully");

    }

    public void listItem(){
        if (this.availableItems.isEmpty()) {
            System.out.println("Movie list is empty");
            return;
        }
        for (Item item: availableItems){
            item.printItemInfo();
        }
    }

    public Item searchAnItem(String itemSearch){
        if (availableItems.isEmpty()){
            return null;
        }

        for (Item item: availableItems) {
            if (itemSearch.equalsIgnoreCase(item.getTitle())){
                if (item.getQuantity() == 0){
                    System.out.println(item.getTitle() +  " is out of stock");
                }
                System.out.println(item.getTitle() + " is available in the store");
                System.out.printf("Quantity: " + item.getQuantity());
                System.out.println("\n");
                return item;
            }
        }

        return null;
    }

    public void searchMovie(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the movie title you want to find: ");
        String searchMovie = sc.nextLine();
        Item item = searchAnItem(searchMovie);

        if (item != null) {
            item.printItemInfo();
        } else {
            System.out.println("This movie is not in the store");
        }
    }

    public void deleteItem(){
        Scanner sc = new Scanner(System.in);
        boolean isItem = false;
        Item itemRemoved = null;
        if (isItem){
            System.out.println("Movie list is empty");
            return;
        }
        System.out.println("Deleting movie");
        System.out.print("Enter the movie title you want to delete: ");
        String itemDelete = sc.nextLine();

        for (Item item: this.availableItems) {
            if (itemDelete.equalsIgnoreCase(item.getTitle())){
                isItem = true;
                itemRemoved = item;
                while (true){
                    System.out.print("Do you wish to continue? (Y/N): ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("Y")){
                        this.availableItems.remove(itemRemoved);
                        System.out.println("Movie removed successfully");
                        break;
                    }
                    else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Canceling movie deletion");
                        break;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }
                }
                break;
            }
        }
        if (!isItem){
            System.out.println("The movie is not exist");
        }
    }

    public void editItemInfo(){
        Scanner scanner = new Scanner(System.in);
        boolean isItem = false;
        while (true) {
            System.out.print("Enter the movie you want to edit: ");
            String itemInfoEdit = scanner.nextLine();
            if (itemInfoEdit.isEmpty()){
                System.out.println("The movie title cannot be empty. Please type in");
                continue;
            }

            for (Item item : this.availableItems) {
                if (itemInfoEdit.equalsIgnoreCase(item.getTitle())) {
                    isItem = true;
                    while (true) {
                        System.out.println("What would you want to change: ");
                        System.out.println("1. Movie title    2. Category    3. Price    4. Quantity    5. Cancel change");
                        System.out.print("Input your choice: ");
                        String choice = scanner.nextLine().trim();

                        if (choice.isEmpty()) {
                            System.out.println("Choice cannot be empty.");
                            continue;
                        }

                        if (!choice.matches("\\d+")) {
                            System.out.println("Choice cannot be words. Please choose again");
                            continue;
                        }
                        int option = Integer.parseInt(choice);

                        switch (option) {
                            case 1 -> {
                                if (itemInfoEdit.equalsIgnoreCase(item.getTitle())) {
                                    System.out.print("Enter new movie title: ");
                                    String newTitle = scanner.nextLine();
                                    if (newTitle.equalsIgnoreCase(item.getTitle())) {
                                        System.out.println("The movie is already exist.");
                                        continue;
                                    }
                                    item.setTitle(newTitle);
                                    System.out.println("Editing movie stats....");
                                    System.out.println("Movie title changed successfully");
                                    return;
                                }
                            }
                            case 2 -> {
                                Category newCate = null;
                                while (true) {
                                    System.out.print("Enter the category you want to change to (Default is GENERAL): ");
                                    String newCategoryString = scanner.nextLine().toUpperCase();
                                    newCate = Category.getCateOrDefault(newCategoryString);
                                    if (newCate == Category.GENERAL){
                                        System.out.println("The type cannot be found. Defaulting to GENERAL");
                                    }
                                    item.setCategory(newCate);
                                    break;
                                }
                                System.out.println("Editing movie stats....");
                                System.out.println("Category changed successfully");
                                return;
                            }
                            case 3 -> {
                                System.out.print("Enter the price you want to change: ");
                                double newPrice = scanner.nextInt();
                                item.setPrice(newPrice);
                                System.out.println("Editing movie stats....");
                                System.out.println("Price changed successfully.");
                                return;
                            }
                            case 4 -> {
                                System.out.print("Enter the quantity you want to change: ");
                                int newQuantity = scanner.nextInt();
                                item.setQuantity(newQuantity);
                                System.out.println("Editing movie stats....");
                                System.out.println("Quantity changed successfully.");
                                return;
                            }
                            case 5 -> System.out.println("Cancelling change");
                            default -> System.out.println("Invalid choice. Please choose again");
                        }
                    }
                } else {
                    System.out.println("The movie is not in store");
                }
            }
        }
    }
}
