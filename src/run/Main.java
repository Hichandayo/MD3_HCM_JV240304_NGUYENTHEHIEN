import business.AuthBusiness;
import business.IAuthDesign;
import entity.RoleName;
import entity.User;
import exception.UsernameAndPasswordException;
import util.IOFile;
import util.InputMethods;
import util.validations.UserValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
private static final IAuthDesign authDesign = new AuthBusiness();
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_CYAN = "\u001B[36m";
    public static void main(String[] args) {

        while (true) {
            System.out.println("----------------------H-Store-------------------");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng kí");
            System.out.println("3. Thoát");
            System.out.println("----- Nhập lựa chọn -------");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    // đăng nhập
                    break;
                case 2:
                    register();
                    // đăng kí
                    break;
                case 3:
                    // thoát
                    System.out.println("Tạm biệt");
                    break;
                default:
                    System.err.println("ko đúng lựa chọn");
            }
            if (choice==3){
                break;
            }
        }
    }
    private static void login(){
        System.out.println("=================Đăng nhập=================");
        System.out.println("Nhập username/ email :");
        String username = InputMethods.getString();
        System.out.println("Nhập password");
        String password = InputMethods.getString();
        try{
            User userLogin = authDesign.signIn(username,password);
            // luu thoong tin nguowif dungf vao file
            IOFile.writeUserLogin(userLogin);
            // xet quyen ngui dung
            if (userLogin.getRoleName().equals(RoleName.ADMIN)){
                menuAdmin();
            }else if (userLogin.getRoleName().equals(RoleName.USER)){
                // kiểm tra tài khoản có bị khóa ko
                if (userLogin.isBlocked()){
                    System.err.println("tai khoan cua bna da bi khoa, vui lòng liên hệ 0989376756");
                }else {
                    userMenu();
                }
            }
        }catch (UsernameAndPasswordException e){
            System.err.println(e.getMessage());
            // hỏi người ta có đăng nhập lại hay ko
        }
    }
    private static  void register(){
        System.out.println("------------------Đăng Ký----------------");
        User user = new User();
        //Nhập và kiển tra tên đăng nhập
        while (true) {
            System.out.println("Nhập Tên ngươi dùng");
            user.setFullName(InputMethods.getString());
            if (!user.getFullName().isEmpty()) {
                break;
            } else {
                System.out.println("Tên đăng nhập không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra email
        while (true) {
            System.out.println("Nhập email đăng nhập");
            user.setEmail(InputMethods.getString());
            if (UserValidation.isValidEmail(user.getEmail())) {
                break;
            } else {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra mật khẩu
        while (true) {
            System.out.println("Nhập mật khẩu: ");
            user.setPassword(InputMethods.getString());
            if (UserValidation.isValidPassword(user.getPassword())) {
                break;
            } else {
                System.out.println("Mật khẩu phải có 1 chữ viết hoa, 1 chữ viết thường, 1 số và có 8 kí tự trở lên. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra số điện thoại
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            user.setPhone(InputMethods.getString());
            if (UserValidation.isValidPhone(user.getPhone())) {
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra địa chỉ
        while (true) {
            System.out.println("Nhập địa chỉ: ");
            user.setAddress(InputMethods.getString());
            if (!user.getAddress().isEmpty()) {
                break;
            } else {
                System.out.println("Địa chỉ không hợp lệ. Vui lòng nhập lại.");
            }
        }
        System.out.println("Nhập ngày sinh (dd/MM/yyyy)");
        user.setBirthday(LocalDate.parse(InputMethods.getString(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        authDesign.signUp(user);

        System.out.println("Đăng kí thành công");
        // chuyển hướng đến đăng nhập
        login();
    }

    private static void menuAdmin(){
        System.out.println("chào mừng bạn đến trang admin");
        InputMethods.pressAnyKey();
    }
    public static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "║  xin chào quý khách                         ║ " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị thông tin cá nhân              ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Sửa đổi thông tin                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Xem tất cả mặt hàng                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xem mặt hàng theo danh mục              ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 5. Thêm mặt hàng vào giỏ                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 6. xem giỏ hàng                            ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 7 Xóa mặt hàng khỏi giỏ                    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 8. Thanh toán                              ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Đăng xuất                               ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
//                case 1 -> displayInfo(user);
//                case 2 -> updateInfo(user);
//                case 3 -> displayAllProduct();
//                case 4 -> displayProductByCategory(scanner);
//                case 5 -> addProductToCart(user);
//                case 6 -> viewCart(user);
//                case 7 -> removeProductFromCart();
//                case 8 -> pay(user);
//                case 0 -> {
//                    logout();
//                    return;
                }
//                default -> System.out.println("Lựa chọn không hợp lệ");
            }

        }

