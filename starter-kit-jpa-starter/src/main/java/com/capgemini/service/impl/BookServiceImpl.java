package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BookDao;
import com.capgemini.domain.BookEntity;
import com.capgemini.service.BookService;

@Service
@Transactional(readOnly=true)
public class BookServiceImpl implements BookService{
	@Autowired
    private BookDao bookRepository;

    @Override
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookEntity> findBooksByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }
    
	@Override
	public List<BookEntity> findBooksByAuthor(Long authorId) {
		return bookRepository.findBooksByAuthor(authorId);
	}
    
    @Override
    public BookEntity findBookById(Long id) {
    	return bookRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public BookEntity saveBook(BookEntity book) {
    	book = bookRepository.save(book);
        return book;
    }

}
