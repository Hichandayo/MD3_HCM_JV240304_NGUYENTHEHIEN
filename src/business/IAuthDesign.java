package business;

import entity.User;

public interface IAuthDesign {
    User signIn(String username, String password);
    void signUp(User user);
}
