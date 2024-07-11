package management;

import business.AuthBusiness;
import business.IAuthDesign;
import business.UserBusiness;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import util.IOFile;
import util.InputMethods;

import java.util.Optional;

public class UserManagement {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static void user(){
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "║  xin chào quý khách                        ║ " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị thông tin cá nhân              ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Sửa đổi thông tin                       ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Đăng xuất                               ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    userInfo();
                    break;
                case 2:
                    editUser();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
        }
    }}
        //        ******* hiển thị thông tin người dùng
        private   static void userInfo() {
            Optional<User> loggedInUser = UserBusiness.getLoggedInUser();
            if (loggedInUser.isPresent()) {
                User user = loggedInUser.get();
                System.out.println("-----------USER INFORMATION-----------");
                System.out.println("ID: " + user.getId());
                System.out.println("Name: " + user.getFullName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone: " + user.getPhone());
                System.out.println("Address: " + user.getAddress());
            } else {
                System.out.println("No user logged in.");
            }
        }
        //********
        private static void editUser() {
            Optional<User> loggedInUser = UserBusiness.getLoggedInUser();
            if (loggedInUser.isPresent()) {
                User user = loggedInUser.get();
                System.out.println("-----------EDIT USER INFORMATION-----------");
                System.out.println("1. Edit Name");
                System.out.println("2. Edit Email");
                System.out.println("3. Edit Phone");
                System.out.println("4. Edit Address");
                System.out.println("5. Change Active Status");
                System.out.println("6. Back to User Menu");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        String newName = InputMethods.getString("Enter new name: ");
                        user.setFullName(newName);
                        System.out.println("Name updated successfully.");
                        break;
                    case 2:
                        String newEmail = InputMethods.getString("Enter new email: ");
                        user.setEmail(newEmail);
                        System.out.println("Email updated successfully.");
                        break;
                    case 3:
                        String newPhone = InputMethods.getString("Enter new phone number: ");
                        user.setPhone(newPhone);
                        System.out.println("Phone number updated successfully.");
                        break;
                    case 4:
                        String newAddress = InputMethods.getString("Enter new address: ");
                        user.setAddress(newAddress);
                        System.out.println("Address updated successfully.");
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("No user logged in.");
            }


}
}
