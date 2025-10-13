
package UI;
import DAO.UserDao;
import Model.User;
import java.awt.Component;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class UserForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserForm.class.getName());
      private final UserDao userDao = new UserDao();
    
    public UserForm() {
        initComponents();
         loadUsers();
          this.setTitle("User Management");
        this.setResizable(false);
        
        
        usertable.getColumnModel().getColumn(1).setCellRenderer(new PasswordCellRenderer());
        
        usertable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });
    }
        private class PasswordCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if (value != null) {
                String password = value.toString();
                String maskedPassword = "•".repeat(password.length());
                setText(maskedPassword);
            }
            
            return c;
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        emailtxt = new javax.swing.JTextField();
        usernametxt = new javax.swing.JTextField();
        fullnametxt = new javax.swing.JTextField();
        passwordtxt = new javax.swing.JPasswordField();
        role = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        usertable = new javax.swing.JTable();
        addbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        clearbtn = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        searchtxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        setLocation(new java.awt.Point(400, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("LIBRARY MANAGEMENT SYSTEM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 420, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("SINDATHRIYA DIVISION");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 330, 40));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel1.setText("Manage Users");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 140, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Email");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 100, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 100, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 100, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("FullName");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 100, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setText("Role");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 100, -1));
        jPanel1.add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 250, -1));
        jPanel1.add(usernametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 250, -1));
        jPanel1.add(fullnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 250, -1));
        jPanel1.add(passwordtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 250, -1));

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "librarian" }));
        jPanel1.add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 250, -1));

        usertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Username", "Password", "Full Name", "Email", "Role"
            }
        ));
        jScrollPane1.setViewportView(usertable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 150, 470, 410));

        addbtn.setBackground(new java.awt.Color(0, 204, 51));
        addbtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addbtn.setForeground(new java.awt.Color(255, 255, 255));
        addbtn.setText("Add");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        jPanel1.add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 150, 50));

        updatebtn.setBackground(new java.awt.Color(204, 204, 0));
        updatebtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        updatebtn.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        jPanel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 150, 50));

        deletebtn.setBackground(new java.awt.Color(255, 0, 0));
        deletebtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        jPanel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 150, 50));

        clearbtn.setBackground(new java.awt.Color(0, 0, 255));
        clearbtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        clearbtn.setForeground(new java.awt.Color(255, 255, 255));
        clearbtn.setText("Clear");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, 150, 50));

        searchbtn.setBackground(new java.awt.Color(255, 0, 255));
        searchbtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("Search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 90, -1));
        jPanel1.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 360, -1));

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUser();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
           searchUser();
    }//GEN-LAST:event_searchbtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        addUser();
    }//GEN-LAST:event_addbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
       updateUser();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed
       clearFields();
    }//GEN-LAST:event_clearbtnActionPerformed

    
    private void addUser() {
        if (usernametxt.getText().trim().isEmpty() || 
            passwordtxt.getPassword().length == 0 ||
            fullnametxt.getText().trim().isEmpty() ||
            emailtxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = new User();
        user.setUsername(usernametxt.getText().trim());
        user.setPassword(new String(passwordtxt.getPassword()));
        user.setFullName(fullnametxt.getText().trim());
        user.setRole(role.getSelectedItem().toString());
        user.setEmail(emailtxt.getText().trim());

        if (userDao.addUser(user)) {
            JOptionPane.showMessageDialog(this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadUsers();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add user!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {
        int selectedRow = usertable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update!", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String originalUsername = usertable.getValueAt(selectedRow, 0).toString();
        User existingUser = userDao.getUserByUsername(originalUsername);
        
        if (existingUser == null) {
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User();
        user.setUserId(existingUser.getUserId());
        user.setUsername(usernametxt.getText().trim().isEmpty() ? existingUser.getUsername() : usernametxt.getText().trim());
        user.setPassword(passwordtxt.getPassword().length == 0 ? existingUser.getPassword() : new String(passwordtxt.getPassword()));
        user.setFullName(fullnametxt.getText().trim().isEmpty() ? existingUser.getFullName() : fullnametxt.getText().trim());
        user.setRole(role.getSelectedItem().toString());
        user.setEmail(emailtxt.getText().trim().isEmpty() ? existingUser.getEmail() : emailtxt.getText().trim());

        if (userDao.updateUser(user)) {
            JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadUsers();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update user!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser() {
        int selectedRow = usertable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete!", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String username = usertable.getValueAt(selectedRow, 0).toString();
        User user = userDao.getUserByUsername(username);
        
        if (user == null) {
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this user?", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            
        if (confirm == JOptionPane.YES_OPTION) {
            if (userDao.deleteUser(user.getUserId())) {
                JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchUser() {
        String username = searchtxt.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a username to search!", "Input Required", JOptionPane.WARNING_MESSAGE);
            loadUsers(); 
            return;
        }

        User user = userDao.getUserByUsername(username);
        if (user != null) {
            usernametxt.setText(user.getUsername());
            fullnametxt.setText(user.getFullName());
            emailtxt.setText(user.getEmail());
            passwordtxt.setText(user.getPassword());
            role.setSelectedItem(user.getRole());
            
            DefaultTableModel model = (DefaultTableModel) usertable.getModel();
            model.setRowCount(0);
            model.addRow(new Object[]{
                user.getUsername(),
                user.getPassword(), 
                user.getFullName(), 
                user.getEmail(), 
                user.getRole()
            });
        } else {
            JOptionPane.showMessageDialog(this, "User not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            loadUsers(); 
        }
    }

    private void clearFields() {
        usernametxt.setText("");
        passwordtxt.setText("");
        fullnametxt.setText("");
        emailtxt.setText("");
        searchtxt.setText("");
        role.setSelectedIndex(0);
        usertable.clearSelection();
    }

    private void loadUsers() {
        List<User> users = userDao.getAllUsers();
        DefaultTableModel model = (DefaultTableModel) usertable.getModel();
        model.setRowCount(0);
        for (User u : users) {
            model.addRow(new Object[]{
                u.getUsername(), 
                u.getPassword(), 
                u.getFullName(), 
                u.getEmail(), 
                u.getRole()
            });
        }
    }

    private void fillFormFromTable() {
        int row = usertable.getSelectedRow();
        if (row >= 0) {
            usernametxt.setText(usertable.getValueAt(row, 0).toString());
            DefaultTableModel model = (DefaultTableModel) usertable.getModel();
            passwordtxt.setText(model.getValueAt(row, 1).toString());
            fullnametxt.setText(usertable.getValueAt(row, 2).toString());
            emailtxt.setText(usertable.getValueAt(row, 3).toString());
            role.setSelectedItem(usertable.getValueAt(row, 4).toString());
        }
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

        java.awt.EventQueue.invokeLater(() -> new UserForm().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JTextField fullnametxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField passwordtxt;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JButton updatebtn;
    private javax.swing.JTextField usernametxt;
    private javax.swing.JTable usertable;
    // End of variables declaration//GEN-END:variables
}
