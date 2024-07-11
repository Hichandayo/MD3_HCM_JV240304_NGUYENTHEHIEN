package business;


import java.util.List;

public interface IGenericDesign <T,E>{
    Boolean create(T t);
    List<T> findAll();
    Boolean update(T t);
    Boolean deleteById(E id);
    T findById(E id);
}
