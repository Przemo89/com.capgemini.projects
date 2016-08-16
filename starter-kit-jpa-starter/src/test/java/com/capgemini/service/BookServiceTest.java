package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.BookEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {


	@Autowired
	private BookService mSUT;
	
	@Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = mSUT.findBookById(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("Pierwsza książka", bookEntity.getTitle());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "p";
        // when
        List<BookEntity> booksEntity = mSUT.findBooksByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
    
    @Test
    public void testShouldFindBooksByAuthor() {
    	// given
    	final Long authorId = 8L;
    	// when
    	List<BookEntity> booksEntity = mSUT.findBooksByAuthor(authorId);
    	// then
    	assertNotNull(booksEntity);
    	assertEquals(2, booksEntity.size());
    }
    
    @Test
    public void testShouldSaveBook() {
    	// given
    	int countBefore = mSUT.findAllBooks().size();
    	final BookEntity book = new BookEntity(null, "Pan Tadeusz");
    	// when
    	BookEntity savedBook = mSUT.saveBook(book);
    	// then
    	assertNotNull(savedBook.getId());
    	assertEquals(countBefore+1, mSUT.findAllBooks().size());
    }

}
