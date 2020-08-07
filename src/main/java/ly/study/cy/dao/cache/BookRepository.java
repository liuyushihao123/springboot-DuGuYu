package ly.study.cy.dao.cache;

import ly.study.cy.bean.Book;

public interface BookRepository {
	Book getByIsbn(String isbn);
}
