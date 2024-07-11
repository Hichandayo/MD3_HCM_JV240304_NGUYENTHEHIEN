package business;

import entity.User;

public interface IAuthDesign extends IGenericDesign<User,Integer> {


    User signIn(String username, String password);
    void signUp(User user);

}
