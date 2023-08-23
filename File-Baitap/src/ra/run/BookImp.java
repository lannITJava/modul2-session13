package ra.run;

import ra.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookImp {
    static List<Book> listBook = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("*************************MENU************************");
            System.out.println("1. Nhập thông tin các sách");
            System.out.println("2. In thông tin các sách ra file demo.txt");
            System.out.println("3. Đọc file demo.txt và in ra các sách có giá trong khoảng 10000 đến 20000");
            System.out.println("4. Thoát");
            System.out.println("Sự lựa chọn của bạn là: ");
            boolean checkChoice = true;
            int choice = 0;
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    checkChoice = false;
                }catch (NumberFormatException ex){
                    System.err.println("Sự lựa chọn phải là số nguyên, vui lòng nhập lại");
                }
            }while (checkChoice);
            switch (choice){
                case 1:
                    BookImp.inputBook();
                    break;
                case 2:
                    BookImp.writeListBookToFile(listBook);
                    break;
                case 3:
                    List<Book> listBookRead =  BookImp.readListBookFromFile();
                    if (listBookRead!= null){
                        for (Book book: listBookRead) {
                            if (book.getPrice()>=10000 && book.getPrice()<=20000){
                                System.out.println(book);
                            }
                        }
                    }

                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-4");
            }
        }while (true);
    }
    public static void inputBook(){
        System.out.println("Nhập số sách muốn nhập thông tin: ");
        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < number; i++) {
            Book bookNew = new Book();
            bookNew.inputData(scanner, listBook);
            listBook.add(bookNew);
        }
        System.out.println("Thêm thông tin sách thành công");
    }
    public static void writeListBookToFile(List<Book> listBook){
        //1. Khởi tạo đối tượng file để làm việc với file - tương đối
        File file = new File("demo.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            //2. Khởi tạo đối tượng FileOutputStream từ file - Checked Excetion
            fos = new FileOutputStream(file);
            //3. Khởi tạo đối tượng ObjectOutputStream từ fos
            oos = new ObjectOutputStream(fos);
            //4. Sử dụng writeObject để ghi object ra file
            oos.writeObject(listBook);
            //5. Đẩy dữ liệu từ Stream xuống file
            oos.flush();
        }catch (FileNotFoundException ex){
            System.err.println("File không tồn tại");
        }catch (IOException ex1){
            System.err.println("Lỗi khi ghi dữ liệu ra file");
        }catch (Exception ex2){
            System.err.println("Xảy ra lỗi trong quá trình ghi dữ liệu ra file");
        }finally {
            //6. Đóng các stream
            try {
                if (oos!= null){
                    oos.close();
                }
                if (fos!= null){
                    fos.close();
                }
            }catch (IOException ex){
                System.err.println("Xảy ra lỗi khi đóng stream");
            }catch (Exception ex1){
                System.err.println("Xảy ra lỗi trong quá trình đóng các stream");
            }
        }
    }
    public static List<Book> readListBookFromFile(){
        //1. Khởi tạo đối tượng File
        File file = new File("demo.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Book> listBookRead = null;
        try {
            //2. Khởi tạo đối tượng FileInputStream
            fis = new FileInputStream(file);
            //3. Khởi tạo đối tượng ObjectInputStream
            ois = new ObjectInputStream(fis);
            //4. Đọc dữ liệu object từ file (readObject())
            listBookRead = (List<Book>) ois.readObject();
        }catch (FileNotFoundException ex){
            System.err.println("Không tồn tại file");
        }catch (IOException ex1){
            System.err.println("Lỗi khi đọc file");
        }catch(Exception ex2){
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
        }finally {
            //6. Đóng các stream
            try {
                if (fis!= null){
                    fis.close();
                }
                if (ois!= null){
                    ois.close();
                }
            }catch (IOException ex){
                System.err.println("Có lỗi khi đóng stream");
            }catch (Exception ex1){
                System.err.println("Có lỗi trong quá trìng đóng các stream");
            }
            return listBookRead;
        }
    }
}
