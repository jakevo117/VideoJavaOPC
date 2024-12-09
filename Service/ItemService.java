package Service;

import Model.Category;
import Model.Item;
import Model.Storage;
import Util.Reader;

import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private ArrayList<Item> availableItems;
    private int ItemIdCounter;

    public ItemService() {
        this.availableItems = new ArrayList<>();
        this.ItemIdCounter = 1;
    }

    //    ITEM FUNCTIONS

    public Item addItem(String title, Category category, double price) throws Exception {
        Item item = new Item(this.ItemIdCounter, title,category, price);
        availableItems.add(item);
        this.ItemIdCounter++;
        return item;
    }

    public List<Item> getListItem () {
        return this.availableItems;
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


    public int getItemIdByMovieTitle(String title){
        int userId = 0;
        for (Item item: availableItems){
            if (title == item.getTitle()){
                userId = item.getItemId();
            }
        }
        return userId;
    }

    public boolean checkExistItem(String itemNeedCheck) {
        return this.availableItems.stream().anyMatch(u -> itemNeedCheck.equalsIgnoreCase(u.getTitle()));
    }
    public boolean checkEmptyList(){
        return availableItems.isEmpty();
    }
}
