
package UI;

import Dao.BorrowDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RecordsForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecordsForm.class.getName());

    private BorrowDao borrowDao;
    
    public RecordsForm() {
        initComponents();
        borrowDao = new BorrowDao();
         this.setTitle("Record Management");
        this.setResizable(false);
        
        setupEventListeners();
        
        loadRecords("All Time");
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        filtercombobox = new javax.swing.JComboBox<>();
        filtersearcgbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordtable = new javax.swing.JTable();
        downloadbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setLocation(new java.awt.Point(400, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("LIBRARY MANAGEMENT SYSTEM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 420, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("SINDATHRIYA DIVISION");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 330, -1));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel1.setText("RECORDS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 90, -1));

        filtercombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "Last Week", "Last Month", "Last Year", "All Time" }));
        filtercombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtercomboboxActionPerformed(evt);
            }
        });
        jPanel1.add(filtercombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 210, -1));

        filtersearcgbtn.setBackground(new java.awt.Color(255, 51, 255));
        filtersearcgbtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        filtersearcgbtn.setForeground(new java.awt.Color(255, 255, 255));
        filtersearcgbtn.setText("Search");
        filtersearcgbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtersearcgbtnActionPerformed(evt);
            }
        });
        jPanel1.add(filtersearcgbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, -1, -1));

        recordtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Borrow ID", "Book ID", "Book Name", "Member ID", "Member Name", "Issue Date", "Due Date", "Return Date", "Status"
            }
        ));
        jScrollPane1.setViewportView(recordtable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 790, 440));

        downloadbtn.setBackground(new java.awt.Color(0, 204, 51));
        downloadbtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        downloadbtn.setForeground(new java.awt.Color(255, 255, 255));
        downloadbtn.setText("Download");
        downloadbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadbtnActionPerformed(evt);
            }
        });
        jPanel1.add(downloadbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 610, 140, 30));

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtersearcgbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtersearcgbtnActionPerformed
        filterRecords();
    }//GEN-LAST:event_filtersearcgbtnActionPerformed

    private void downloadbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadbtnActionPerformed
       exportToExcel();
    }//GEN-LAST:event_downloadbtnActionPerformed

    private void filtercomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtercomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtercomboboxActionPerformed

    
     private void setupEventListeners() {
    
    }
     
    private void loadRecords(String filter) {
        LocalDate startDate = calculateStartDate(filter);
        List<Map<String, Object>> records;
        
        if (startDate == null) {
         
            records = borrowDao.getAllBorrowRecordsWithDetails();
        } else {
        
            records = borrowDao.getBorrowRecordsByDateRange(startDate);
        }
        
        displayRecords(records);
    }
    
   
    private LocalDate calculateStartDate(String filter) {
        LocalDate today = LocalDate.now();
        
        switch (filter) {
            case "Today":
                return today;
            case "Last Week":
                return today.minusWeeks(1);
            case "Last Month":
                return today.minusMonths(1);
            case "Last Year":
                return today.minusYears(1);
            case "All Time":
            default:
                return null; // No filter
        }
    }
    
    private void displayRecords(List<Map<String, Object>> records) {
        DefaultTableModel model = (DefaultTableModel) recordtable.getModel();
        model.setRowCount(0); // Clear existing rows
        
        for (Map<String, Object> record : records) {
            Object[] row = {
                record.get("borrow_id"),
                record.get("book_id"),
                record.get("book_title"),
                record.get("member_id"),
                record.get("member_name"),
                record.get("issue_date"),
                record.get("due_date"),
                record.get("return_date") != null ? record.get("return_date") : "Not Returned",
                record.get("status")
            };
            model.addRow(row);
        }
    }
    
    
     
    private void filterRecords() {
        String selectedFilter = (String) filtercombobox.getSelectedItem();
        loadRecords(selectedFilter);
        
        int rowCount = recordtable.getRowCount();
        JOptionPane.showMessageDialog(this, 
            "Found " + rowCount + " record(s) for: " + selectedFilter, 
            "Filter Applied", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    private void exportToExcel() {
        if (recordtable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, 
                "No records to export", 
                "Export Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");
        fileChooser.setSelectedFile(new File("BorrowRecords.xlsx"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            
            try {
                createExcelFile(filePath);
                JOptionPane.showMessageDialog(this, 
                    "Excel file exported successfully!\n" + filePath, 
                    "Export Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error exporting to Excel: " + e.getMessage(), 
                    "Export Error", 
                    JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, "Error exporting to Excel", e);
            }
        }
    }
    

    private void createExcelFile(String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Borrow Records");
        
        // Create header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        
        // Create data style
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        
       
        Row headerRow = sheet.createRow(0);
        DefaultTableModel model = (DefaultTableModel) recordtable.getModel();
        
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
            cell.setCellStyle(headerStyle);
        }
        
       
        for (int row = 0; row < model.getRowCount(); row++) {
            Row dataRow = sheet.createRow(row + 1);
            
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = dataRow.createCell(col);
                Object value = model.getValueAt(row, col);
                
                if (value != null) {
                    cell.setCellValue(value.toString());
                } else {
                    cell.setCellValue("");
                }
                
                cell.setCellStyle(dataStyle);
            }
        }
        
        
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
        }
        
       
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        
        workbook.close();
    }

     public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(() -> new RecordsForm().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downloadbtn;
    private javax.swing.JComboBox<String> filtercombobox;
    private javax.swing.JButton filtersearcgbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable recordtable;
    // End of variables declaration//GEN-END:variables
}
