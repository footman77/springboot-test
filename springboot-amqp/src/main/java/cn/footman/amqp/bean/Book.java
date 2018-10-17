package cn.footman.amqp.bean;

/**
 * @author footman77
 * @create 2018-10-14 22:05
 */
public class Book {

    private String bookName;
    private String auth;

    public Book() {
    }

    public Book(String bookName, String auth) {
        this.bookName = bookName;
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", auth='" + auth + '\'' +
                '}';
    }
}
