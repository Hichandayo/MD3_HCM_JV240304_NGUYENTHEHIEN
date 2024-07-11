package business;

import entity.Category;
import util.IOFile;

import java.util.List;

public class CategoryBusiness implements ICategoryDesign{
    public static List<Category> categories ;

    public CategoryBusiness() {
        categories = IOFile.readFromFile(IOFile.CATEGORY_PATH);
    }
    @Override
    public Boolean create(Category category) {
        categories.add(category);
        IOFile.writeToFile(IOFile.CATEGORY_PATH,categories);
        return false;

    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public Boolean update(Category category) {
        categories.set(categories.indexOf(findById(category.getId())),category);
        IOFile.writeToFile(IOFile.CATEGORY_PATH,categories);
        return false;

    }

    @Override
    public Boolean deleteById(Integer id) {
        categories.remove(findById(id));
        IOFile.writeToFile(IOFile.CATEGORY_PATH,categories);
        return false;

    }

    @Override
    public Category findById(Integer id) {
        for (Category cat : categories){
            if (cat.getId() == id){
                return cat;
            }
        }
        return null;
    }
}
