package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.BookEntity;

public interface BookService {
    List<BookEntity> findAllBooks();
    List<BookEntity> findBooksByTitle(String title);
    List<BookEntity> findBooksByAuthor(Long authorId);
    BookEntity findBookById(Long id);

    BookEntity saveBook(BookEntity book);
}
