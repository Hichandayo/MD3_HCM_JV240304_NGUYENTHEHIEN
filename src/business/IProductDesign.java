package business;

import entity.Product;

import java.util.List;

public interface IProductDesign  extends  IGenericDesign<Product,Integer> {
    List<Product> findByName(String name);
    boolean existByCategoryId(Integer catId);
}
