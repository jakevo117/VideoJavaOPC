package Service;

import Model.Category;
import Model.Item;
import Model.User;
import Util.Reader;

import java.util.ArrayList;

public class ItemService {
    private ArrayList<Item> availableItems;
    private Reader reader = Reader.getReader();

    public ItemService() {
        this.availableItems = new ArrayList<>();
    }

    //    ITEM FUNCTIONS

    public void addItem(String title, Category category, double price, int quantity) throws Exception {
        availableItems.add(new Item(title, category, price, quantity, true));
        System.out.println("New movie added successfully");
    }

    public void addQuantity(int newQuantity, String title) {
        for (Item item : availableItems) {
            if (title.equalsIgnoreCase(item.getTitle())) {
                item.setQuantity(item.getQuantity() + newQuantity);
                return;
            }
        }
    }

    public void listItem() {
        for (Item item : availableItems) {
            item.printItemInfo();
        }
    }

    public Item searchItem(String itemFind) throws Exception {
        Item item = null;
        for (Item itemSearch : availableItems) {
            if (itemFind.equalsIgnoreCase(itemSearch.getTitle())) {
                item = itemSearch;
            }
        }
        return item;
    }

    public void deleteItem(Item itemToDelete) {
        availableItems.remove(itemToDelete);
    }

    public void editItemInfo(Item itemToEdit) throws Exception {
        int index = availableItems.indexOf(itemToEdit);
        availableItems.set(index, itemToEdit);
    }

    public boolean checkExistItem(String itemNeedCheck) {
        return this.availableItems.stream().anyMatch(u -> itemNeedCheck.equalsIgnoreCase(u.getTitle()));
    }
    public boolean checkEmptyList(){
        return availableItems.isEmpty();
    }
}
