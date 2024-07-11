package business;

import entity.Category;
import entity.User;
import util.IOFile;

import java.util.List;

public class CategoryBusiness implements ICategoryDesign{
    public static List<Category> categories ;

    public CategoryBusiness() {
        categories = IOFile.readFromFile(IOFile.CATEGORY_PATH);
    }


    @Override
    public void create(User user) {

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void create(Category category) {
        categories.add(category);
        IOFile.writeToFile(IOFile.CATEGORY_PATH,categories);
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public void update(Category category) {
        categories.set(categories.indexOf(findById(category.getId())),category);
        IOFile.writeToFile(IOFile.CATEGORY_PATH,categories);
    }

    @Override
    public void deleteById(Integer id) {
        categories.remove(findById(id));
        IOFile.writeToFile(IOFile.CATEGORY_PATH,categories);
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
