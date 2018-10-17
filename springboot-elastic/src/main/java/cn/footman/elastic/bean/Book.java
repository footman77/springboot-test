package cn.footman.elastic.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author footman77
 * @create 2018-10-15 15:56
 */

@Document(indexName = "atguigu",type = "book")
public class Book {

    private Integer id;
    private String author;
    private String content;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
