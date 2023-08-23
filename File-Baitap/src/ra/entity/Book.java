package ra.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Book implements Serializable {
    private String bookId;
    private String bookName;
    private float price;

    public Book() {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void inputData(Scanner scanner, List<Book> listBook){
        System.out.println("Nhập mã sách: ");
        this.bookId= scanner.nextLine();
        System.out.println("Nhập tên sách: ");
        this.bookName= scanner.nextLine();
        System.out.println("Nhập giá sách");
        boolean checkPrice = true;
        do {
            try {
                this.price= Float.parseFloat(scanner.nextLine());
                checkPrice = false;
            }catch (NumberFormatException ex){
                System.err.println("Giá sách phài là số thực, vui lòng nhập lại");
            }
        }while (checkPrice);
    }
    public String toString (){
        return "Mã sách: "+this.bookId+" - Tên sách: "+this.bookName+" - Giá sách: "+price;
    }
}
