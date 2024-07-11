package management;

import util.InputMethods;


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
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
        }
    }}

}
