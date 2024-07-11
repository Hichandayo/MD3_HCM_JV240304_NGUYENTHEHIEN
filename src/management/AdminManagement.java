package management;

import util.InputMethods;

public class AdminManagement {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_RED = "\u001B[31m";


    public static void admin(){

            while (true) {
                System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "║              Admin-Management              ║" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ 1. Quản lí danh mục                        ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ 2. Quản lí sản phẩm                        ║" + ANSI_RESET);
                System.out.println(ANSI_RED + "║ 3. Đăng Xuất                               ║" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Nhập lựa chọn ⭣ ⭣ ⭣     " + ANSI_RESET);
                System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                        // menu danh mục
                        CategoryManagement.menuCategory();
                        break;
                    case 2:
                        //menu sản phẩm
                        ProductManagement.menuProduct();
                        break;
                    case 3:
                        System.out.println("Tạm biệt");
                        break;
                    default:
                        System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
                }
                if (choice == 3) {
                    break;
                }
            }
        }
    }

