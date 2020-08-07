package ly.study.cy.dao.cache.Impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ly.study.cy.bean.Book;
import ly.study.cy.dao.cache.BookRepository;

/**
 * 这个你可以写一个很复杂的数据查询操作，比如操作mysql、nosql等等。为了演示这个栗子，我只做了一下线程的延迟操作，当作是查询数据库的时间。
 * 
 * @author liuyu
 *
 */
@Component
public class SimpleBookRepository implements BookRepository {
	@Override
	@Cacheable("books")
	public Book getByIsbn(String isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	// Don't do this at home
	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
