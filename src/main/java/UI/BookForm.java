/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Model.Book;
import Dao.BookDao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class BookForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BookForm.class.getName());
  private BookDao bookDao;
    private DefaultTableModel tableModel;
   
    public BookForm() {
        initComponents();
        this.setTitle("Book Management");
        this.setResizable(false);
        bookDao = new BookDao();
        setupTable();
        loadAllBooks();
        setupEventListeners();
    }


    private void setupTable() {
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Book ID", "Title", "Author", "Category", "ISBN", "Publisher", "Year"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
        
        // Add selection listener to populate form when row is clicked
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                populateFormFromTable();
            }
        });
    }
    
    private void setupEventListeners() {
        addbtn.addActionListener(e -> addBook());
        updatebtn.addActionListener(e -> updateBook());
        clearbtn.addActionListener(e -> clearForm());
    }
    
    private void loadAllBooks() {
        tableModel.setRowCount(0);
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books) {
            tableModel.addRow(new Object[]{
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getIsbn(),
                book.getPublisher(),
                book.getPublicationYear()
            });
        }
    }
    
    private void addBook() {
        if (!validateForm()) {
            return;
        }
        
        try {
            Book book = new Book(
                titletxt.getText().trim(),
                authortxt.getText().trim(),
                categorytxt.getText().trim(),
                isbntxt.getText().trim(),
                publishertxt.getText().trim(),
                Integer.parseInt(publicationyeartxt.getText().trim())
            );
            
            if (bookDao.addBook(book)) {
                JOptionPane.showMessageDialog(this, "Book added successfully!");
                clearForm();
                loadAllBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid publication year!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateBook() {
        if (!validateForm()) {
            return;
        }
        
        try {
            Book book = new Book(
                titletxt.getText().trim(),
                authortxt.getText().trim(),
                categorytxt.getText().trim(),
                isbntxt.getText().trim(),
                publishertxt.getText().trim(),
                Integer.parseInt(publicationyeartxt.getText().trim())
            );
            
            if (bookDao.updateBook(book)) {
                JOptionPane.showMessageDialog(this, "Book updated successfully!");
                clearForm();
                loadAllBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid publication year!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteBook() {
        String isbn = isbntxt.getText().trim();
        
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ISBN or select a book to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this book?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (bookDao.deleteBook(isbn)) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                clearForm();
                loadAllBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void searchBook() {
        String keyword = jTextField2.getText().trim();
        
        if (keyword.isEmpty()) {
            loadAllBooks();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Book> books = bookDao.searchBooks(keyword);
        
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Book book : books) {
                tableModel.addRow(new Object[]{
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getIsbn(),
                    book.getPublisher(),
                    book.getPublicationYear()
                });
            }
        }
    }
    
    private void clearForm() {
        titletxt.setText("");
        authortxt.setText("");
        categorytxt.setText("");
        isbntxt.setText("");
        publishertxt.setText("");
        publicationyeartxt.setText("");
        jTextField2.setText("");
        jTable1.clearSelection();
    }
    
    private void populateFormFromTable() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            titletxt.setText(tableModel.getValueAt(selectedRow, 1).toString());
            authortxt.setText(tableModel.getValueAt(selectedRow, 2).toString());
            categorytxt.setText(tableModel.getValueAt(selectedRow, 3).toString());
            isbntxt.setText(tableModel.getValueAt(selectedRow, 4).toString());
            publishertxt.setText(tableModel.getValueAt(selectedRow, 5).toString());
            publicationyeartxt.setText(tableModel.getValueAt(selectedRow, 6).toString());
        }
    }
    
    private boolean validateForm() {
        if (titletxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter book title!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            titletxt.requestFocus();
            return false;
        }
        if (authortxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter author name!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            authortxt.requestFocus();
            return false;
        }
        if (categorytxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter category!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            categorytxt.requestFocus();
            return false;
        }
        if (isbntxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ISBN!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            isbntxt.requestFocus();
            return false;
        }
        if (publishertxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter publisher!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            publishertxt.requestFocus();
            return false;
        }
        if (publicationyeartxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter publication year!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            publicationyeartxt.requestFocus();
            return false;
        }
        
        try {
            int year = Integer.parseInt(publicationyeartxt.getText().trim());
            if (year < 1000 || year > 9999) {
                JOptionPane.showMessageDialog(this, "Please enter a valid year!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                publicationyeartxt.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Publication year must be a number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            publicationyeartxt.requestFocus();
            return false;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        authortxt = new javax.swing.JTextField();
        categorytxt = new javax.swing.JTextField();
        isbntxt = new javax.swing.JTextField();
        publishertxt = new javax.swing.JTextField();
        publicationyeartxt = new javax.swing.JTextField();
        clearbtn = new javax.swing.JButton();
        addbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        titletxt = new javax.swing.JTextField();
        deletebtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setLocation(new java.awt.Point(400, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("SINDATHRIYA DIVISION");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 330, -1));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel1.setText("Book Management");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 170, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("LIBRARY MANAGEMENT SYSTEM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 420, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Title");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 80, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Author");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 80, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Category");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 80, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("ISBN");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 80, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("Publisher");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 80, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setText("Publication Year");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 120, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 340, -1));
        jPanel1.add(authortxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 260, -1));
        jPanel1.add(categorytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 260, -1));
        jPanel1.add(isbntxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 260, -1));
        jPanel1.add(publishertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 260, -1));
        jPanel1.add(publicationyeartxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 260, -1));

        clearbtn.setBackground(new java.awt.Color(0, 0, 255));
        clearbtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        clearbtn.setForeground(new java.awt.Color(255, 255, 255));
        clearbtn.setText("Clear");
        jPanel1.add(clearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 100, 50));

        addbtn.setBackground(new java.awt.Color(0, 204, 0));
        addbtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addbtn.setForeground(new java.awt.Color(255, 255, 255));
        addbtn.setText("Add");
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 90, 50));

        updatebtn.setBackground(new java.awt.Color(204, 204, 0));
        updatebtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        updatebtn.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn.setText("Update");
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 90, 50));

        searchbtn.setBackground(new java.awt.Color(102, 0, 102));
        searchbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("Search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 90, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ISBN", "Book Name"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, -1, 330));
        jPanel1.add(titletxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 260, -1));

        deletebtn.setBackground(new java.awt.Color(255, 0, 0));
        deletebtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 90, 50));

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        searchBook();
    }//GEN-LAST:event_searchbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
            deleteBook();
    }//GEN-LAST:event_deletebtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BookForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JTextField authortxt;
    private javax.swing.JTextField categorytxt;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTextField isbntxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField publicationyeartxt;
    private javax.swing.JTextField publishertxt;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField titletxt;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
