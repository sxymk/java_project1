import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> list =new ArrayList<>();
    public static void main(String[] args) {
        readDate();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("=========图书管理系统========");
            System.out.println("1.插入信息");
            System.out.println("2.修改信息");
            System.out.println("3.查询图书列表");
            System.out.println("4.删除图书");
            System.out.println("（按其他任意键退出系统）");
            String str = scanner.nextLine();
            switch (str){
                case "1":
                    insertBook(scanner);
                    break;
                case "2":
                    modifyBook(scanner);
                    break;
                case "3":
                    showBooks();
                    break;
                case "4":
                    deleteBook(scanner);
                    break;
                default:
                    saveDate();
                    scanner.close();
                    return;
            }

        }

    }
    @SuppressWarnings("unchecked")
    private static void readDate(){
        File file = new File("data.txt");
        if(file.exists()){
            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.txt"))){
                list = (List<Book>) inputStream.readObject();
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }
    private static void saveDate(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.txt"))){
            outputStream.writeObject(list);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void modifyBook(Scanner scanner){
        int i =0;
        for(Book book: list){
            System.out.println(i+"."+book);
            i++;
        }
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index>=list.size()){
            System.out.println("错误的序号");
        }

        else {
            list
                    .get(index)
                    .name(scanner.nextLine())
                    .author(scanner.next())
                    .price(scanner.nextDouble());
            scanner.nextLine();

        }
    }
    private static void deleteBook(Scanner scanner){
        int i =0;
        for(Book book: list){
            System.out.println(i+"."+book);
            i++;
        }
        int index = scanner.nextInt();
        if (index>=list.size()) System.out.println("错误的序号");
        else list.remove(index);
        scanner.nextLine();
    }
    private static void  showBooks(){
        list.forEach(System.out::println);
    }
    private static  void insertBook(Scanner scanner){

        list.add(new Book()
                .name(scanner.nextLine())
                .author(scanner.next())
                .price(scanner.nextDouble()));
        scanner.nextLine();

    }
    private static class Book implements Serializable{
        String name;
        String author;
        double price;
        public Book name(String name) {
            this.name = name;
            return this;
        }
        public Book author(String author) {
            this.author = author;
            return this;
        }
        public Book price(double price) {
            this.price = price;
            return this;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
