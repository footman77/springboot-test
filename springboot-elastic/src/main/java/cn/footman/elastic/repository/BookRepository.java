package cn.footman.elastic.repository;

import cn.footman.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author footman77
 * @create 2018-10-15 15:55
 */
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
}
