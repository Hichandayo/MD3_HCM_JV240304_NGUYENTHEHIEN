package business;

import entity.User;

import java.util.List;

public interface IGenericDesign <T,E>{
    void create(User user);

    User findById(String id);

    void update(User user);

    void deleteById(String id);

    void create(T t);
    List<T> findAll();
    void update(T t);
    void deleteById(E id);
    T findById(E id);
}
