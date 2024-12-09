package Service;

import Model.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StorageService {
    private ArrayList<Storage> storageItemList;

    public StorageService() {
        this.storageItemList = new ArrayList<>();
    }

    public void addQuantity(int itemId, int amount) throws Exception {
       storageItemList.add(new Storage(itemId, amount));
    }

    public int getItemQuantityByItemId(int itemId) throws Exception {
        int count = 0;
        for (Storage storage: storageItemList ) {
            if (storage.getItemId() == itemId) {
                count = count + storage.getQuantity();
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
}
