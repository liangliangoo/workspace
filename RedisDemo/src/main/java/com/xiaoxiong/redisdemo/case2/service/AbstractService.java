package com.xiaoxiong.redisdemo.case2.service;

import com.xiaoxiong.redisdemo.entity.Book;
import com.xiaoxiong.redisdemo.entity.BookQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/20 15:59
 */
@Service
@Slf4j
public class AbstractService {

    protected static Map<String, Book> repositoryBook = new HashMap<>();

    public AbstractService() {
        super();
    }

    //模拟数据库
    @PostConstruct
    public void init() {
        // 1
        Book book1 = new Book("1", "name_1", 11, new Date());
        repositoryBook.put(book1.getId(), book1);
        // 2
        Book book2 = new Book("2", "name_2", 11, new Date());
        repositoryBook.put(book2.getId(), book2);
        // 3
        Book book3 = new Book("3", "name_3", 11, new Date());
        repositoryBook.put(book3.getId(), book3);
        // 4
        Book book4 = new Book("4", "name_4", 11, new Date());
        repositoryBook.put(book4.getId(), book4);
    }

    /**
     * @param id
     * @return
     * @Cacheable 如果缓存没有值，从则执行方法并缓存数据，如果缓存有值，则从缓存中获取值
     * <p>
     * cacheNames 设置缓存的值
     * key：指定缓存的key，这是指参数id值。 key可以使用spEl表达式
     */
    @Cacheable(cacheNames = "book1", key = "#id")
    public Book queryBookCacheable(String id) {
        log.info("queryBookCacheable,id={}", id);
        return repositoryBook.get(id);
    }

    /**
     * 这里使用另一个缓存存储缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "book2", key = "#id")
    public Book queryBookCacheable_2(String id) {
        log.info("queryBookCacheable_2,id={}", id);
        return repositoryBook.get(id);
    }

    /**
     * 缓存的key也可以指定对象的成员变量
     *
     * @param qry
     * @return
     */
    @Cacheable(cacheNames = "book1", key = "#qry.id")
    public Book queryBookCacheableByBookQry(BookQuery qry) {
        log.info("queryBookCacheableByBookQry,qry={}", qry);
        String id = qry.getId();
        Assert.notNull(id, "id can't be null!");
        String name = qry.getName();
        Book book = null;
        if (id != null) {
            book = repositoryBook.get(id);
            if (book != null && !(name != null && book.getName().equals(name))) {
                book = null;
            }
        }
        return book;
    }

    /**
     * 以上我们使用默认的keyGenerator，对应spring的SimpleKeyGenerator
     * 如果你的使用很复杂，我们也可以自定义myKeyGenerator的生成key
     * <p>
     * key和keyGenerator是互斥，如果同时制定会出异常
     * The key and keyGenerator parameters are mutually exclusive and an operation specifying both will result in an exception.
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "book3", keyGenerator = "myKeyGenerator")
    public Book queryBookCacheableUseMyKeyGenerator(String id) {
        log.info("queryBookCacheableUseMyKeyGenerator,id={}", id);
        return repositoryBook.get(id);
    }


    /**
     * @CacheEvict 删除缓存
     * allEntries = true: 清空book1里的所有缓存
     */
    @CacheEvict(cacheNames = "book1", allEntries = true)
    public void clearBook1All() {
        log.info("clearAll");
    }

    /**
     * 对符合key条件的记录从缓存中book1移除
     */
    @CacheEvict(cacheNames = "book1", key = "#id")
    public void updateBook(String id, String name) {
        log.info("updateBook");
        Book book = repositoryBook.get(id);
        if (book != null) {
            book.setName(name);
            book.setUpdate(new Date());
        }
    }

    /**
     * @param id
     * @return
     * @CachePut 每次执行都会执行方法，无论缓存里是否有值，同时使用新的返回值的替换缓存中的值
     */
    @CachePut(cacheNames = "book1", key = "#id")
    public Book queryBookCachePut(String id) {
        log.info("queryBookCachePut,id={}", id);
        return repositoryBook.get(id);
    }

    /**
     *
     * @CacheConfig: 类级别的注解：如果我们在此注解中定义cacheNames，
     * 则此类中的所有方法上 @Cacheable的cacheNames默认都是此值。
     * 当然@Cacheable也可以重定义cacheNames的值
     */

}
