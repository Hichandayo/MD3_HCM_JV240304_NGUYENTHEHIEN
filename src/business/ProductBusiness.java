package business;
import entity.Product;
import util.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductBusiness implements IProductDesign{
    public static List<Product> products;

    public ProductBusiness() {
        products = IOFile.readFromFile(IOFile.PRODUCT_PATH);
    }

    @Override
    public List<Product> findByName(String name) {
//        return products.stream().filter(pro-> pro.getName().toLowerCase().contains(name.toLowerCase())).toList();
        List<Product> searchList = new ArrayList<>();
        for (Product pro : products){
            if (pro.getName().toLowerCase().contains(name.toLowerCase())){
                searchList.add(pro);
            }
        }
        return searchList;
    }

    @Override
    public boolean create(Product product) {
        products.add(product);
        IOFile.writeToFile(IOFile.PRODUCT_PATH,products);
        return true;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public boolean update(Product product) {
        products.set(products.indexOf(findById(product.getId())),product);
        IOFile.writeToFile(IOFile.PRODUCT_PATH,products);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        products.remove(findById(id));
        IOFile.writeToFile(IOFile.PRODUCT_PATH,products);
        return true;
    }

    @Override
    public Product findById(Integer id) {
        for (Product pro : products){
            if (pro.getId() == id){
                return pro;
            }
        }
        return null;
    }

    @Override
    public boolean existByCategoryId(Integer catId) {
        for (Product pro : products){
            if (pro.getCategoryId() == catId){
                return true;
            }
        }
        return false;
    }
}
