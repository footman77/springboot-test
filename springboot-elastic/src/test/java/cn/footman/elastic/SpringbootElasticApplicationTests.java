package cn.footman.elastic;

import cn.footman.elastic.bean.Article;
import cn.footman.elastic.bean.Book;
import cn.footman.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void test01(){
        Book book = new Book();
        book.setId(1);
        book.setAuthor("zhangsan");
        book.setContent("dage");
        bookRepository.index(book);
    }

    @Test
    public void contextLoads() {
        //1、给ES中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setAuthor("laisf");
        article.setTitle("shijie");
        article.setContent("haohaoah");

        //构建一个索引功能
        Index build = new Index.Builder(article).index("atguigu").type("new").build();

        try {
            //执行
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //搜索测试
    @Test
    public void search(){
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"haohaoah\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索
        Search build = new Search.Builder(json).addIndex("atguigu").addType("new").build();
        try {
            SearchResult searchResult = jestClient.execute(build);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
