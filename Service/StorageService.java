package Service;

import Model.Storage;
import Model.Type;

import java.util.ArrayList;
import java.util.List;

public class StorageService {
    private ArrayList<Storage> storageItemList;

    public StorageService() {
        this.storageItemList = new ArrayList<>();
    }

    public void addQuantity(int itemId, int amount) {
       storageItemList.add(new Storage(itemId, amount, true));
    }

    public void addQuantityWithCode(int itemId, int rentId, Type type, int amount) throws Exception {
        storageItemList.add(new Storage(itemId, rentId, type, amount, true));
    }

    public void deductQuantity(int itemId, int rentId, Type type, int amount){
        storageItemList.add(new Storage(itemId, rentId, type, -amount, false));
    }

    public int getItemQuantityByItemId(int itemId) throws Exception {
        int count = 0;
        for (Storage storage: storageItemList ) {
            if (storage.getItemId() == itemId) {
                count += storage.getQuantity();
            }
        }
        return count;
    }

    public List<Storage> getListStorage() {
        return this.storageItemList;
    }
    public void listStorage(){
        for (Storage storage: storageItemList){
            storage.printStorageInfo();
        }
    }
    public boolean checkEmptyList(){
        return storageItemList.isEmpty();
    }
}
