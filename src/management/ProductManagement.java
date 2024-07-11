package management;

import business.CategoryBusiness;
import business.ICategoryDesign;
import business.IProductDesign;
import business.ProductBusiness;
import entity.Product;
import util.InputMethods;
import util.validations.ProductValidation;

import java.util.List;

import static business.CategoryBusiness.categories;

public class ProductManagement {
    private static final ICategoryDesign categoryBusiness = new CategoryBusiness();
    private static final IProductDesign productBusiness = new ProductBusiness();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    static void menuProduct() {
        while (true) {
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "║               Quản lý sản phẩm             ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ Nhập lựa chọn của bạn:                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Thêm sản phẩm mới                       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Hiển thị danh sách sản phẩm             ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Chỉnh sửa sản phẩm                      ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xóa sản phẩm                            ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 5. Quay lại                                ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════════╝" + ANSI_RESET);
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1 -> addNewProduct();
                case 2 -> showProductList();
                case 3 -> editProduct();
                case 4 -> deleteProduct();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
        //********* thêm mới danh mục ************

    private static void addNewProduct() {
        if (categoryBusiness.findAll().isEmpty()) {
            System.err.println("chưa có danh mục, hãy quay lại thêm danh mục trước khi thêm sản phẩm");
            return;
        }
        System.out.println("Nhập số lượng cần thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ :" + (i + 1));
            Product newProduct = new Product(); // chứa logic tự tăng
            newProduct.inputData(); // cho nhập thông tin
            productBusiness.create(newProduct); // luu lại
        }
        // thông báo thành công
        System.out.println("Đã thêm mới thành công " + n + " sản phẩm !");
    }
    //********* hiển thị danh mục ************

    private static void showProductList() {
        // lấy ra danh sách
        List<Product> products = productBusiness.findAll();
        if (products.isEmpty()) {
            System.err.println("Danh sách trống !");
        } else {
            System.out.println("-------- Danh sách sản phẩm --------");
            for (Product pro : products) {
                pro.displayData();
            }
        }
    }
//********* sửa danh mục ************
    private static void editProduct() {
        System.out.println("Nhập mã sản phẩm cần chỉnh sửa");
        int editId = InputMethods.getInteger();
        Product product = productBusiness.findById(editId);

        // kiểm tra tồn tại
        if (productBusiness.findById(editId) == null) {
            System.err.println("sản phẩm không tồn tại.");
        }
        while (true) {
            System.out.println("Chọn thuộc tính cần chỉnh sửa");
            System.out.println("1. Tên sản phẩm");
            System.out.println("2. Giá sản phẩm");
            System.out.println("3. Số lượng sản phẩm");
            System.out.println("4. Danh mục sản phẩm");
            System.out.println("5. Mô tả sản phẩm");
            System.out.println("6. Trạng thái sản phẩm");
            System.out.println("7. Thoát");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên sản phẩm mới: ");
                    String productName = InputMethods.getString("Enter new name: ");
                    if (!ProductValidation.isValidProductName(productName)) {
                        System.out.println("Tên sản phẩm không được bỏ trống.");
                        return;
                    }
                    product.setName(productName);

                case 2:
                    System.out.println("Nhập giá sản phẩm mới: ");
                    int productPrice = InputMethods.getInteger();
                    if (!ProductValidation.isValidProductPrice(String.valueOf(productPrice))) {
                        System.out.println("Giá sản phẩm không hợp lệ. Giá sản phẩm phải lớn hơn 1000 và không được bỏ trống.");
                        return;
                    }
                    product.setPrice(productPrice);
                    break;
                case 3:
                    System.out.println("Nhập số lượng sản phẩm mới: ");
                    int productQuantity = InputMethods.getInteger();
                    if (!ProductValidation.isValidProductQuantity(String.valueOf(productQuantity))) {
                        System.out.println("Số lượng sản phẩm không hợp lệ. Số lượng sản phẩm phải lớn hơn 0 và không được bỏ trống.");
                        return;
                    }
                    product.setStock(productQuantity);
                    break;
                case 4:
                    System.out.println("Danh sách danh mục");
                    for (int i = 1 ;i<= categories.size();i++){
                        System.out.printf("|STT : %-3s | Name : %-15s |\n",i,categories.get(i-1).getName());
                    }
                    System.out.println("Lựa chọn danh mục: ");
                    int productStock = InputMethods.getInteger();
                    product.setCategoryId(Integer.parseInt(String.valueOf(categories.get(productStock-1).getId())));
                    break;
                case 5:
                    System.out.println("Nhập mô tả sản phẩm mới: ");
                    String productDescription = InputMethods.getString("Enter new name: ");
                    if (!ProductValidation.isValidProductDescription(productDescription)) {
                        System.out.println("Mô tả sản phẩm không được bỏ trống.");
                        return;
                    }
                    product.setDescriptions(productDescription);
                    break;
                case 6:
                    System.out.println("Nhập trạng thái sản phẩm mới: ");
                    System.out.println("0. Hết hàng");
                    System.out.println("1. Còn hàng");
                    int productStatus = InputMethods.getInteger();
                    if (!ProductValidation.isValidProductStatus(String.valueOf(productStatus))) {
                        System.out.println("Trạng thái sản phẩm không hợp lệ. Trạng thái sản phẩm phải là 0 hoặc 1.");
                        return;

                    }
                    product.setStatus(productStatus == 1);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
            productBusiness.update(product);
            System.out.println("Chỉnh sửa sản phẩm thành công");
        }
    }
//********* xóa danh mục ************

    private static void deleteProduct() {
        System.out.println("Nhập mã sản phẩm  cần xóa ");
        int proId = InputMethods.getInteger();
        // kiểm tra tồn tại
        if (productBusiness.findById(proId) == null) {
            System.err.println("id không tồn tại");
        } else {
            productBusiness.deleteById(proId);
            System.out.println(" đã xóa thành công");
        }
    }


}
