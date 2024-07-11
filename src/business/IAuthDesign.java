package business;

import entity.User;

public interface IAuthDesign extends IGenericDesign<User,Integer> {
    User findById(String id);

    void deleteById(String id);

    User signIn(String username, String password);
    void signUp(User user);

}
