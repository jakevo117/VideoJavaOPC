package Service;

import Model.Item;
import Repository.ItemRepository;

import java.util.ArrayList;

public class ItemService {
    private ItemRepository itemRepository;
    public ItemService() {
        this.itemRepository = new ItemRepository();
    }

    public void addItem(String title, int categoryID, double price) {
        itemRepository.addNewItem(title, categoryID, price);
    }

    public ArrayList<Item> getItemList() {
        return itemRepository.getListItem();
    }

    public ArrayList<Item> getItemListDetail() {
        return itemRepository.getListItemDetail();
    }
    public void listItem() {
        for (Item item : getItemListDetail()) {
            item.printItemInfo();
        }
    }

    public Item searchItem(int itemFind) {
        Item item = itemRepository.getItemById(itemFind);
        return item;
    }

    public void deleteItem(Item itemToDelete) {
        itemRepository.removeItemById(itemToDelete);
    }

    public void editItemInfo(Item itemToEdit, String fieldCase, String valueToEditString, int newCategoryID, double valueToEditPrice) {
        itemRepository.EditItemInfoDB(itemToEdit, fieldCase, valueToEditString, newCategoryID, valueToEditPrice);
    }

    public int getItemIdByMovieTitle(String title){
        for (Item item: getItemList()){
            if (title.equalsIgnoreCase(item.getTitle())){
                return item.getItemId();
            }
        }
        return -1;
    }
//    public boolean checkExistItem(String itemNeedCheck) {
//        return this.availableItems.stream().anyMatch(u -> itemNeedCheck.equalsIgnoreCase(u.getTitle()));
//    }
    public boolean checkEmptyList(){
        return itemRepository.isEmpty();
    }
}
