package business;

import entity.RoleName;
import entity.User;
import exception.UsernameAndPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import util.IOFile;

import java.util.List;

public class AuthBusiness implements IAuthDesign{
    private static List<User> users ;
    static {
        users= IOFile.readFromFile(IOFile.USER_PATH);
    }
    @Override
    public User signIn(String username, String password) {
        return users.stream().filter(u->u.getEmail().equals(username) && BCrypt.checkpw(password,u.getPassword()))
                .findFirst().orElseThrow(() -> new UsernameAndPasswordException("Tài khoản hoặc mật khẩu không đúng!!"));
    }

    @Override
    public void signUp(User user) {
        user.setRoleName(RoleName.USER);
        user.setBlocked(false);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(5)));
        users.add(user);
        IOFile.writeToFile(IOFile.USER_PATH,users);
    }
}
