/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sanju
 */
public class EventAchivemet_UGI extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    
    public EventAchivemet_UGI() {
        initComponents();
        conn = Database.DBConnection.getDbConnect().connect();
        fillSportComboBox();
        DefaultValues();
        RadiobuttonDefault();
        fillFacultyCombo();
        fillUniversityCombo();
        loadScoreTable();
    }

    
    
    //fill Sport Combo Box
    private void fillSportComboBox(){
        try {
            CallableStatement stmt = conn.prepareCall("{call getSport}");
            Boolean hasResult = stmt.execute();
            if(hasResult){
                rs = stmt.getResultSet();
                while(rs.next()){
                    sport_ComboBox.addItem(rs.getString("name"));
                }
            }
        } catch (Exception e) {
            System.out.println(e+" sport combo box");
        }
    }
    
    
    // fill Faculty & University Combobox
    private void fillFacultyCombo(){
            try {
                
                CallableStatement stmt = conn.prepareCall("{call getFaculty}");
                Boolean hasResult = stmt.execute();
                
                if(hasResult){
                    rs = stmt.getResultSet();
                    while (rs.next()) {                        
                        Faculty_ComboBox.addItem(rs.getString("name"));
                    }
                }
            
            }catch (Exception e) {
                System.out.println(e+" facluty combo box");
            }
        
    }
    
    // fill university combo Box
    private void fillUniversityCombo(){
         try {
                CallableStatement stmt = conn.prepareCall("{call getUniversity}");
                Boolean hasResult = stmt.execute();
                
                if(hasResult){
                    rs = stmt.getResultSet();
                    while (rs.next()) {                        
                       university_Comb.addItem(rs.getString("universityName"));
                    }
                }
            
            }catch (Exception e) {
                System.out.println(e+" university combo box");
            }
    }
    
    // Load score details to Table
    private void loadScoreTable(){
        try {
            CallableStatement stmt = conn.prepareCall("{call getEventArchivement}");
            Boolean hasResult = stmt.execute();
            
            if(hasResult){
                rs = stmt.getResultSet();
                while (rs.next()){
                    String year = String.valueOf(rs.getInt("year"));
                    String event =rs.getString("eventId");
                    String fac_uni = rs.getString("facUni");
                    String sport = rs.getString("sport");
                    String place =  rs.getString("place");
                    String point = String.valueOf(rs.getInt("points"));
                    String description = rs.getString("description");
                   
                    
                    //String array for store data in to score table
                    String tableData[] = {year,event,fac_uni,sport,place,point,description};
                    DefaultTableModel tableModel = (DefaultTableModel) score_table.getModel();
                    
                    tableModel.addRow(tableData);
                }
            }
        } catch (Exception e) {
            System.out.println(e+" table error");
        }
    }
    
    private void RadiobuttonDefault(){
        interfaculty_radiobtn.setSelected(true);
        // set radio button
        if(interfaculty_radiobtn.isSelected()){
            interfaculty_radiobtn.setVisible(true);
            inter_university_radiobtn.setSelected(false);
            slug_radiobtn.setSelected(false);
            university_Comb.setVisible(false);
            Faculty_ComboBox.setVisible(true);
        }else if(inter_university_radiobtn.isSelected()){
            inter_university_radiobtn.setVisible(true);
            interfaculty_radiobtn.setSelected(false);
            slug_radiobtn.setSelected(false);
            university_Comb.setVisible(true);
            Faculty_ComboBox.setVisible(false);
            
        }else if(slug_radiobtn.isSelected()){
            slug_radiobtn.setVisible(true);
            interfaculty_radiobtn.setSelected(false);
            slug_radiobtn.setSelected(true);
            university_Comb.setVisible(true);
            Faculty_ComboBox.setVisible(false);
            
        }
    }
    
    // Defaut values
    private void DefaultValues(){
        
        
        fac_uni_validate_lable.setText(null);
        sport_validate_lable.setText(null);
        place_validate_lable.setText(null);
        point_validate_lable.setText(null);
        year_validate_lable.setText(null);
        Faculty_ComboBox.setSelectedIndex(0);
        university_Comb.setSelectedIndex(0);
        place_ComboBox.setSelectedIndex(0);
        point_textfeild.setText(null);
        discription_textarea.setText(null); 
        // set year
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        Year_box.setYear(currentYear);
    }
    
    // form validation
    private void formValidation(){
        String faculty = null;
        String university = null;
        String sport = null;
        String place = null;
        int point = 0;
        int eventYear=0;
        
        // year validate
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
            if(Year_box.getYear()>currentYear){
                year_validate_lable.setText("*Please Enter Valid Year");
            }else{
               eventYear = Year_box.getYear();
            }
        
        
        
        // faculty combobox validate
        if(interfaculty_radiobtn.isSelected()){
                if(Faculty_ComboBox.getSelectedItem().toString().equalsIgnoreCase("select faculty")){
                        fac_uni_validate_lable.setText("*Please Select a Faculty");
                }else{
                        faculty = Faculty_ComboBox.getSelectedItem().toString();
                        
                }
        }else{
        // university validation
                if(university_Comb.getSelectedItem().toString().equalsIgnoreCase("select university")){
                        fac_uni_validate_lable.setText("*Please Select a University");
                }else{
                        university = university_Comb.getSelectedItem().toString();
                        
                }
        }
        
        
        // sport combobox validate
        if(sport_ComboBox.getSelectedItem().toString().equalsIgnoreCase("select sport")){
                sport_validate_lable.setText("*Please Select a Sport");
        }else{
                sport = sport_ComboBox.getSelectedItem().toString();
        }
        
        // place combobox validate
        if(place_ComboBox.getSelectedItem().toString().equalsIgnoreCase("select place")){
                place_validate_lable.setText("*Please Select a place");
        }else{
                place = place_ComboBox.getSelectedItem().toString();
        }
        
        // point validate
        if(point_textfeild.getText().equalsIgnoreCase("")){
                point_validate_lable.setText("*Please Enter point");
        }else{
                point = Integer.parseInt(point_textfeild.getText());
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        point_textfeild = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        discription_textarea = new javax.swing.JTextArea();
        Faculty_ComboBox = new javax.swing.JComboBox<>();
        sport_ComboBox = new javax.swing.JComboBox<>();
        place_ComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Year_box = new com.toedter.calendar.JYearChooser();
        interfaculty_radiobtn = new javax.swing.JRadioButton();
        inter_university_radiobtn = new javax.swing.JRadioButton();
        slug_radiobtn = new javax.swing.JRadioButton();
        university_Comb = new javax.swing.JComboBox<>();
        fac_uni_validate_lable = new javax.swing.JLabel();
        sport_validate_lable = new javax.swing.JLabel();
        place_validate_lable = new javax.swing.JLabel();
        point_validate_lable = new javax.swing.JLabel();
        year_validate_lable = new javax.swing.JLabel();
        insert_button = new javax.swing.JLabel();
        reset_button = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        score_table = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 102));
        jLabel1.setText("Year");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 102));
        jLabel2.setText("Event");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 102));
        jLabel6.setText("point");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 102));
        jLabel7.setText("discription");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        point_textfeild.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        getContentPane().add(point_textfeild, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 64, -1));

        discription_textarea.setColumns(20);
        discription_textarea.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        discription_textarea.setRows(5);
        jScrollPane1.setViewportView(discription_textarea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, 60));

        Faculty_ComboBox.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Faculty_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Faculty" }));
        Faculty_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Faculty_ComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(Faculty_ComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 140, -1));

        sport_ComboBox.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        sport_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Sport" }));
        getContentPane().add(sport_ComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 140, -1));

        place_ComboBox.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        place_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select place", "1st", "2nd", "3rd" }));
        place_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                place_ComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(place_ComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 140, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Score Panel");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_close_window_30px.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 40, 40));
        getContentPane().add(Year_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 80, 30));

        interfaculty_radiobtn.setText("Inter Faculty");
        interfaculty_radiobtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                interfaculty_radiobtnMouseClicked(evt);
            }
        });
        getContentPane().add(interfaculty_radiobtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 100, -1));

        inter_university_radiobtn.setText("Inter University");
        inter_university_radiobtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inter_university_radiobtnMouseClicked(evt);
            }
        });
        getContentPane().add(inter_university_radiobtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        slug_radiobtn.setText("SLUG");
        slug_radiobtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slug_radiobtnMouseClicked(evt);
            }
        });
        getContentPane().add(slug_radiobtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 100, -1));

        university_Comb.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        university_Comb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select University", " " }));
        university_Comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                university_CombActionPerformed(evt);
            }
        });
        getContentPane().add(university_Comb, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 140, -1));

        fac_uni_validate_lable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        fac_uni_validate_lable.setForeground(new java.awt.Color(255, 51, 51));
        fac_uni_validate_lable.setText("fac_uni_validate_lable");
        getContentPane().add(fac_uni_validate_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        sport_validate_lable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sport_validate_lable.setForeground(new java.awt.Color(255, 51, 51));
        sport_validate_lable.setText("sport_validate_lable");
        getContentPane().add(sport_validate_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        place_validate_lable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        place_validate_lable.setForeground(new java.awt.Color(255, 51, 51));
        place_validate_lable.setText("place_validate_lable");
        getContentPane().add(place_validate_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, -1, -1));

        point_validate_lable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        point_validate_lable.setForeground(new java.awt.Color(255, 51, 51));
        point_validate_lable.setText("point_validate_lable");
        getContentPane().add(point_validate_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        year_validate_lable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        year_validate_lable.setForeground(new java.awt.Color(255, 51, 51));
        year_validate_lable.setText("year_validate_lable");
        getContentPane().add(year_validate_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        insert_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/insert.png"))); // NOI18N
        insert_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insert_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(insert_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, -1, -1));

        reset_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset_button.png"))); // NOI18N
        reset_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reset_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(reset_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, -1, -1));

        score_table.setBackground(new java.awt.Color(204, 255, 255));
        score_table.setBorder(new javax.swing.border.MatteBorder(null));
        score_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Event", "Fac/Uni", "Sport", "Place", "Point", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        score_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(score_table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 640, 200));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Session bg.png"))); // NOI18N
        jLabel9.setText("  ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        setSize(new java.awt.Dimension(1100, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Faculty_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Faculty_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Faculty_ComboBoxActionPerformed

    private void interfaculty_radiobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_interfaculty_radiobtnMouseClicked
          if(interfaculty_radiobtn.isSelected()){
           inter_university_radiobtn.setSelected(false);
           slug_radiobtn.setSelected(false);
           Faculty_ComboBox.setVisible(true);
           university_Comb.setVisible(false);
       }
    }//GEN-LAST:event_interfaculty_radiobtnMouseClicked

    private void slug_radiobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slug_radiobtnMouseClicked
       if(slug_radiobtn.isSelected()){
            inter_university_radiobtn.setSelected(false);
            interfaculty_radiobtn.setSelected(false);
            Faculty_ComboBox.setVisible(false);
            university_Comb.setVisible(true);
        }
    }//GEN-LAST:event_slug_radiobtnMouseClicked

    private void inter_university_radiobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inter_university_radiobtnMouseClicked
        if(inter_university_radiobtn.isSelected()){
           interfaculty_radiobtn.setSelected(false);
           slug_radiobtn.setSelected(false);
           Faculty_ComboBox.setVisible(false);
           university_Comb.setVisible(true);
         }
    }//GEN-LAST:event_inter_university_radiobtnMouseClicked

    private void university_CombActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_university_CombActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_university_CombActionPerformed

    private void insert_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insert_buttonMouseClicked
       formValidation();
    }//GEN-LAST:event_insert_buttonMouseClicked

    private void reset_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reset_buttonMouseClicked
        DefaultValues();
    }//GEN-LAST:event_reset_buttonMouseClicked

    private void place_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_place_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_place_ComboBoxActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventAchivemet_UGI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventAchivemet_UGI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventAchivemet_UGI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventAchivemet_UGI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventAchivemet_UGI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Faculty_ComboBox;
    private com.toedter.calendar.JYearChooser Year_box;
    private javax.swing.JTextArea discription_textarea;
    private javax.swing.JLabel fac_uni_validate_lable;
    private javax.swing.JLabel insert_button;
    private javax.swing.JRadioButton inter_university_radiobtn;
    private javax.swing.JRadioButton interfaculty_radiobtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> place_ComboBox;
    private javax.swing.JLabel place_validate_lable;
    private javax.swing.JTextField point_textfeild;
    private javax.swing.JLabel point_validate_lable;
    private javax.swing.JLabel reset_button;
    private javax.swing.JTable score_table;
    private javax.swing.JRadioButton slug_radiobtn;
    private javax.swing.JComboBox<String> sport_ComboBox;
    private javax.swing.JLabel sport_validate_lable;
    private javax.swing.JComboBox<String> university_Comb;
    private javax.swing.JLabel year_validate_lable;
    // End of variables declaration//GEN-END:variables
}
