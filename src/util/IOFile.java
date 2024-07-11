package util;

import entity.RoleName;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String CATEGORY_PATH = "src/data/category.txt";
    public static final String USER_PATH = "src/data/users.txt";
    public static final String PRODUCT_PATH = "src/data/product.txt";
    public static <T> List<T> readFromFile(String path){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            return (List<T>) ois.readObject();
        }catch (FileNotFoundException e){
            System.err.println("File không tồn tại");
        }catch (EOFException e){
            System.err.println("Chưa Có Tài Khoản User Nào, Hãy Thêm Vào!!");
            User admin = new User(1,"admin","admin@gmail.com", BCrypt.hashpw("admin123",BCrypt.gensalt(5)),null,null,null, RoleName.ADMIN,false);
            List<User> users = new ArrayList<>();
            users.add(admin);
            writeToFile(USER_PATH, users);
            return (List<T>) users;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }  finally {
            try{
                if (ois!=null){
                    ois.close();
                }
                if (fis!=null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public static <T> void writeToFile(String path, List<T> data){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(new File(path));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
        }catch (IOException e){
            e.printStackTrace();
        }  finally {
            try{
                if (oos!=null){
                    oos.close();
                }
                if (fos!=null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static User readUserLogin(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("src/data/userLogin.txt");
            ois = new ObjectInputStream(fis);
            return (User) ois.readObject();
        }catch (FileNotFoundException e){
            System.err.println("file ko ton tai");
        }catch (EOFException e){
            System.err.println("file trống");
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }  finally {
            try{
                if (ois!=null){
                    ois.close();
                }
                if (fis!=null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeUserLogin(User userLogin){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(new File("src/data/userLogin.txt"));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(userLogin);
        }catch (IOException e){
            e.printStackTrace();
        }  finally {
            try{
                if (oos!=null){
                    oos.close();
                }
                if (fos!=null){
                    fos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
