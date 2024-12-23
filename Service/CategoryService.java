package Service;

import Model.Category;
import Repository.CategoryRepository;

import java.util.ArrayList;

public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService() {
        this.categoryRepository = new CategoryRepository();
    }

    public ArrayList<Category> getListCategory(){
        return categoryRepository.getListCategory();
    }

    public void addNewCategory(String category){
        categoryRepository.addCategory(category);
    }
}
