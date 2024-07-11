package business;

import entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserBusiness implements IGenericDesign {
    private Map<String, User> users = new HashMap<>();

    public static Optional<User> getLoggedInUser() {
        return null;
    }

    @Override
    public void create(User user) {
        users.put(String.valueOf(user.getId()),user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public void update(User user) {
        users.put(String.valueOf(user.getId()), user);
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public void create(Object o) {

    }

    @Override
    public List<User> findAll() {
        return users.values().stream().collect(Collectors.toList());
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void deleteById(Object id) {

    }

    @Override
    public Object findById(Object id) {
        return null;
    }

}