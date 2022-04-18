/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Book;
import Services.Services;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author TGDD
 */
public class mainForm extends JFrame implements ActionListener{
    private JPanel mainPane, headerPane, filterPane, buttonPanel, displayPane, footerPane;
    private JLabel bNameLabel;
    private JTextField bNameTF;
    private JButton searchBtn, exitBtn, deleteBtn, updateBtn, createBtn;
    private JTable bookTable;
    private JScrollPane bookScrollPane;
    
    Services manager = new Services();
    
    private void createHeaderPanel() {
        headerPane = new JPanel();
        headerPane.setLayout(new BorderLayout());
        
        filterPane = new JPanel();
        Border blackline = BorderFactory.createTitledBorder("Filter");
        filterPane.setBorder(blackline);
        filterPane.setLayout(new BoxLayout(filterPane, BoxLayout.X_AXIS));
        filterPane.setPreferredSize(new Dimension(400, 100));
        
        
        bNameLabel = new JLabel();
        bNameLabel.setText("Name:");
        bNameLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        bNameTF = new JTextField();
        bNameTF.setText("");
        bNameTF.setFont(new Font("Tahoma", 1, 14));
        bNameTF.setMaximumSize(new Dimension(320, 26));
        
        filterPane.add(new JLabel("    "));
        filterPane.add(bNameLabel);
        filterPane.add(new JLabel("   "));
        filterPane.add(bNameTF);
        
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 100));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        flowLayout.setHgap(0);
        buttonPanel.setLayout(flowLayout);
        buttonPanel.setBorder(new EmptyBorder(38,0,0,0));
        searchBtn = new JButton("Search");
        searchBtn.setPreferredSize(new Dimension(75, 26));
        exitBtn = new JButton("Exit");
        exitBtn.setPreferredSize(new Dimension(75, 26));
        searchBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        buttonPanel.add(searchBtn);
        buttonPanel.add(new JLabel("  "));
        buttonPanel.add(exitBtn);
        
        headerPane.add(filterPane);
        headerPane.add(buttonPanel, BorderLayout.EAST);
        
    }
    
    private void createDisplayPanel() {
        displayPane = new JPanel();
        displayPane.setLayout(new BorderLayout());
        displayPane.setPreferredSize(new Dimension(400, 150));
        displayPane.setBorder(new EmptyBorder(10,0,10,0));
        
        
        
        bookTable = new JTable();
        handleGetAllBooks();
        bookTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        bookTable.setFont(new Font("Tahoma", Font.BOLD, 12));
        bookTable.setRowHeight(25);
        handleTable();
        bookScrollPane = new JScrollPane(bookTable);
        displayPane.add(bookScrollPane);
        

    }
    
    private void createFooterPanel() {
        footerPane = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        flowLayout.setHgap(0);
        footerPane.setLayout(flowLayout);
        footerPane.setPreferredSize(new Dimension(400, 50));
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setPreferredSize(new Dimension(75, 26));
        deleteBtn.addActionListener(this);
        deleteBtn.setEnabled(false);  
             
        updateBtn = new JButton("Update");
        updateBtn.setPreferredSize(new Dimension(75, 26));
        updateBtn.addActionListener(this);
        
        createBtn = new JButton("New +");
        createBtn.setPreferredSize(new Dimension(75, 26));
        createBtn.addActionListener(this);
        
        footerPane.add(createBtn);
        footerPane.add(new JLabel("  "));
        footerPane.add(deleteBtn);
        footerPane.add(new JLabel("  "));
        footerPane.add(updateBtn);
        
    }
    
    public mainForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(300, 90, 700, 360);
        setTitle("BOOKS MANAGERMENT");
        mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        mainPane.setBorder(new EmptyBorder(20,30,20,30));
        mainPane.setBackground(new Color(204,255,204));
        createHeaderPanel();
        createDisplayPanel();
        createFooterPanel();
        
        mainPane.add(headerPane, BorderLayout.NORTH);
        mainPane.add(displayPane, BorderLayout.CENTER);
        mainPane.add(footerPane, BorderLayout.SOUTH);
        this.add(mainPane);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object buttonClicked = e.getSource();
        if(buttonClicked == searchBtn) {
            handleSearchBtn();
        }
        if(buttonClicked == exitBtn) {
            handleExitBtn();
        }
        if(buttonClicked == deleteBtn) {
            handleDeleteBtn();
        }
        if(buttonClicked == createBtn) {
            handleCreateBtn();
        }
        if(buttonClicked == updateBtn) {
            handleUpdateBtn();
        }
    }
    
    // method handle 
    private void handleGetAllBooks() {
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
           @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("NAME");
        defaultTableModel.addColumn("PRICE");
        List<Book> Books = manager.getAllBooks();
        for (Book book: Books) {
            defaultTableModel.addRow(new Object[] {
                book.getId(),
                book.getBookName(),
                book.getPrice(),
            });
        }
        bookTable.setModel(defaultTableModel);
    }
    
    private void handleSearchBtn() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("NAME");
        defaultTableModel.addColumn("PRICE");
        
        String bookName = bNameTF.getText();
        if(bookName.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sách!");
            return;
        }
        Book book = manager.getBookByName(bookName);

        defaultTableModel.addRow(new Object[] {
                book.getId(),
                book.getBookName(),
                book.getPrice(),
            });
        bookTable.setModel(defaultTableModel);
    }
    
    private void handleExitBtn() {
        System.exit(0);
    }
    
    private  void handleTable() {
        bookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChang(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
            }

            @Override
            public void valueChanged(ListSelectionEvent e) {
                deleteBtn.setEnabled(true);
            }
        });
        
        
        
    }
   
    private void handleDeleteBtn() {
        int column = 0;
        int row = bookTable.getSelectedRow();
        if(row >= 0) {
            deleteBtn.setEnabled(true);
        }
        int id = (int) bookTable.getModel().getValueAt(row, column);
        
        int ret = JOptionPane.showConfirmDialog(this, "Xóa sách này?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            manager.deleteBook(id); 
            ((DefaultTableModel)bookTable.getModel()).removeRow(row);
        }else {
            return;
        }
        if(manager.deleteBook(id) == 0) {
            JOptionPane.showMessageDialog(this, "Đã xóa sách này!");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!");
            return;
        }
    }
    
    private void handleCreateBtn() {
        new CreateForm().setVisible(true);
        this.dispose();
    }
    
    private  void handleUpdateBtn() {
        new UpdateForm().setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        mainForm f = new mainForm();
    }

    
}
