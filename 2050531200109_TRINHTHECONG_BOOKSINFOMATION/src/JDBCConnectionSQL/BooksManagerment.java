/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBCConnectionSQL;

import Models.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TGDD
 */
public class BooksManagerment extends JDBCConnection {
    // method hiển thị toàn bộ sách
    public List<Book> getAllBooks() {
        List<Book> books =  new ArrayList<Book>();
        
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "select * from Books";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getDouble("price"));
                
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    // method hiển thị sách theo tên
    public Book getBookByName(String bookName) {
        Book book = new Book();
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "select * from Books where bookName = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            stm.setString(1 , bookName);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getDouble("price"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    
    public Book getBookById(String id) {
        Book book = new Book();
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "select * from Books where id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            stm.setString(1 , id);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getDouble("price"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    
    //method thêm sách theo id 
    public void addBook(Book book) {
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "insert into Books(bookName, price) values(?, ?)";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            stm.setString(1, book.getBookName());
            stm.setDouble(2, book.getPrice());
            
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method xóa sách theo id 
    int result;
    public int deleteBook(int id) {
        
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "delete from Books where id = ?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            stm.setInt(1, id);
            result = stm.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // method sửa sách
    public void updateBook(Book book) {
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "update Books set bookName = ?, price = ? where id = ?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            stm.setString(1, book.getBookName());
            stm.setDouble(2, book.getPrice());
            stm.setInt(3, book.getId());
            stm.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method lấy id sách 
    public List<String> getIds() {
        List<String> ids =  new ArrayList<>();
        
        Connection conn = JDBCConnection.getJDBCConnection();
        String sqlQuerry = "select id from Books";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sqlQuerry);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                ids.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

}
