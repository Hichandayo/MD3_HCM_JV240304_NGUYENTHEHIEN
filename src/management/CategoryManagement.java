package management;

import business.CategoryBusiness;
import business.ICategoryDesign;
import entity.Category;
import util.InputMethods;

import java.util.List;

public class CategoryManagement {
    private static final ICategoryDesign categoryBusiness = new CategoryBusiness();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";


        static void menuCategory() {
        while (true) {
                System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "║               Menu Danh Mục                ║" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ 1. Thêm mới                                ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ 2. Hiển thị                                ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ 3. Sửa tên                                 ║" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "║ 4. Xóa                                     ║" + ANSI_RESET);
                System.out.println(ANSI_RED + "║ 5. Thoát                                   ║" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Nhập lựa chọn ⭣ ⭣ ⭣     " + ANSI_RESET);
                System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                        addNewCategory();
                        break;
                    case 2:
                        showCategoryList();
                        break;
                    case 3:
                        editCategory();
                        break;
                    case 4:
                        deleteCategory();
                        break;
                    case 5:
                        break;
                    default:
                        System.err.println("Lựa chọn ko chính xác , vui lòng nhập lại");
                }
                if (choice == 5){
                    break;
                }

            }
        }
        //********* sửa  Danh Mục ************
        private static void editCategory() {
            System.out.println("Nhập mã danh mục  cần sửa ");
            int catId = InputMethods.getInteger();
            Category catEdit = categoryBusiness.findById(catId);
            if (catEdit == null) {
                System.err.println("id không tồn tại");
            } else {
                catEdit.inputData();
                categoryBusiness.update(catEdit);
                System.out.println("Cập nhật thành công");
            }
        }
//********* thêm mới Danh Mục ************

        private static void addNewCategory() {
            System.out.println("Nhập số lượng danh mục cần thêm mới");
            byte n = InputMethods.getByte();
            for (int i = 0; i < n; i++) {
                System.out.println("Nhập thông tin cho danh mục thứ :" + (i + 1));
                Category newCategory = new Category(); // chứa logic tự tăng
                newCategory.inputData(); // cho nhập thông tin
                categoryBusiness.create(newCategory); // luu lại
            }
            // thông báo thành công
            System.out.println("Đã thêm mới thành công " + n + " danh mục !");
        }

//********* hiển thị Danh Mục ************

        private static void showCategoryList() {
            // lấy ra danh sách
            List<Category> categories = categoryBusiness.findAll();
            if (categories.isEmpty()) {
                System.err.println("Danh sách trống !");
            } else {
                System.out.println("-------- Danh sách danh mục --------");
                for (Category cat : categories) {
                    cat.displayData();
                }
            }
        }
        //********* Xóa Danh Mục ************
        private static void deleteCategory() {
            System.out.println("Nhập mã danh mục cần xóa ");
            int catId = InputMethods.getInteger();
            if (categoryBusiness.findById(catId) == null) {
                System.err.println("sản phẩm không tồn tại");
            } else {
                categoryBusiness.deleteById(catId);
                System.out.println("đã xóa sản phẩm thành công!!!");
            }
        }


}
