package run;
import business.AuthBusiness;
import business.IAuthDesign;
import entity.RoleName;
import entity.User;
import exception.UsernameAndPasswordException;
//import management.AdminManagement;
import management.AdminManagement;
import management.UserManagement;
import util.IOFile;
import util.InputMethods;
import util.validations.UserValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main{
private static final IAuthDesign authDesign = new AuthBusiness();
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {

        while (true) {
            System.out.println(ANSI_PURPLE + "╔════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_PURPLE +"║ Welcome to Hi-chan Shop    ║"+ ANSI_RESET);
            System.out.println(ANSI_PURPLE + "╠════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "║" + ANSI_RESET + ANSI_CYAN + "    1. Đăng nhập " + ANSI_RESET + ANSI_PURPLE + "           ║" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "║" + ANSI_RESET + ANSI_CYAN + "    2. Đăng kí" + ANSI_RESET + ANSI_PURPLE + "              ║" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "║" + ANSI_RESET + ANSI_CYAN + "    3. Thoát" + ANSI_RESET + ANSI_PURPLE + "                ║" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "╚ " + ANSI_RESET + ANSI_CYAN + "  Nhập lựa chọn ⭣ ⭣ ⭣" + ANSI_RESET + ANSI_PURPLE + "     ═╝" + ANSI_RESET);
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
    //******* Đăng Nhập *********
    private static void login(){
        System.out.println("=================Đăng nhập=================");
        System.out.println("Nhập username/ email :");
        String username = InputMethods.getString();
        System.out.println("Nhập password");
        String password = InputMethods.getString();
        try{
            User userLogin = authDesign.signIn(username,password);
            // lưu thông tin người dùng vào file
            IOFile.writeUserLogin(userLogin);
            // xét quyền người dùng
            if (userLogin.getRoleName().equals(RoleName.ADMIN)){
                AdminManagement.admin();
            }else if (userLogin.getRoleName().equals(RoleName.USER)){
                // kiểm tra tài khoản có bị khóa ko
                if (userLogin.isBlocked()){
                    System.err.println("tài khoản của bạn đã bị khóa, vui lòng liên hệ Admin để được hỗ trợ.");
                }else {
                    UserManagement.user();
                }
            }
        }catch (UsernameAndPasswordException e){
            System.err.println(e.getMessage());
            // hỏi người ta có đăng nhập lại hay ko
        }
    }
    //******* Đăng Ký *********

    private static  void register(){
        System.out.println("************Đăng Ký************");
        User user = new User();
        //Nhập và kiển tra tên đăng nhập
        while (true) {
            System.out.println(ANSI_PURPLE +"Nhập Tên ngươi dùng"+ ANSI_RESET);
            user.setFullName(InputMethods.getString());
            if (!user.getFullName().isEmpty()) {
                break;
            } else {
                System.out.println("Tên đăng nhập không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra email
        while (true) {
            System.out.println(ANSI_PURPLE +"Nhập email đăng nhập"+ ANSI_RESET);
            user.setEmail(InputMethods.getString());
            if (UserValidation.isValidEmail(user.getEmail())) {
                break;
            } else {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra mật khẩu
        while (true) {
            System.out.println(ANSI_PURPLE +"Nhập mật khẩu"+ ANSI_RESET);
            user.setPassword(InputMethods.getString());
            if (UserValidation.isValidPassword(user.getPassword())) {
                break;
            } else {
                System.out.println("Mật khẩu phải có 1 chữ viết hoa, 1 chữ viết thường, 1 số và có 8 kí tự trở lên. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra số điện thoại
        while (true) {
            System.out.println(ANSI_PURPLE +"Nhập số điện thoại"+ ANSI_RESET);
            user.setPhone(InputMethods.getString());
            if (UserValidation.isValidPhone(user.getPhone())) {
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        }
        //Nhập và kiểm tra địa chỉ
        while (true) {
            System.out.println(ANSI_PURPLE +"Nhập Địa Chỉ"+ ANSI_RESET);
            user.setAddress(InputMethods.getString());
            if (!user.getAddress().isEmpty()) {
                break;
            } else {
                System.err.println("Địa chỉ không hợp lệ. Vui lòng nhập lại.");
            }
        }
        System.out.println(ANSI_PURPLE +"Nhập ngày sinh (dd/MM/yyyy)"+ ANSI_RESET);
        user.setBirthday(LocalDate.parse(InputMethods.getString(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        authDesign.signUp(user);
        System.out.println("Đăng kí thành công");
        // chuyển hướng đến đăng nhập
        login();
    }}







