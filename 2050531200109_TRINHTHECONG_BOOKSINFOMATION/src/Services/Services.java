/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import JDBCConnectionSQL.BooksManagerment;
import Models.Book;
import java.util.List;

/**
 *
 * @author TGDD
 */
public class Services {
    public BooksManagerment manager;
    
    public Services() {
        manager = new BooksManagerment();
    }
    
    public List<Book> getAllBooks() {
        return manager.getAllBooks();
    }
    
    public Book getBookByName(String bookName) {
        return manager.getBookByName(bookName);
    }
    
    public Book getBookById(String id) {
        return manager.getBookById(id);
    }
    
    public void addBook(Book book) {
        manager.addBook(book);
    }
    
    public int deleteBook(int id) {
        return manager.deleteBook(id);
    }
    
    public void updateBook(Book book) {
        manager.updateBook(book);
    }
    
    public List<String> getIds() {
        return manager.getIds();
    }
}
