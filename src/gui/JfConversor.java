/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dto.Moneda;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author fatal-Emiliano
 */
public class JfConversor extends javax.swing.JFrame {

    /**
     * Creates new form JfConversor
     */
    private String men="";
    private Moneda monedaResultado = new Moneda();
    public JfConversor() {
        initComponents();
        iniciarConversor();
    }
    public void iniciarConversor(){
        cargarDatos();
        setRadioButton();
        obtenerSeleccion();
        configurarControles();
        digitarMoneda();
    }
     public void cargarDatos(){
         //Seleccionar la opcion de soles a otras monedas como defecto
        jrbSoles.setSelected(true);
        // Obtener el modelo del combo box
        DefaultComboBoxModel<Moneda> modelo = (DefaultComboBoxModel<Moneda>) jComboBox1.getModel();
       
        // Crear una lista de objetos "Pais" para agregar al modelo
        List<Moneda> listaMoneda = generarMoneda();

        // Agregar los nuevos objetos al modelo
        for(Moneda moneda : listaMoneda){
            modelo.addElement(moneda);
        }
    

    }
    public List<Moneda> generarMoneda(){
        List<Moneda> listaMoneda = new ArrayList<>();
        listaMoneda.add(new Moneda("Dolar","USD", 3.74));
        listaMoneda.add(new Moneda("Euro","EUR", 4.11));
        listaMoneda.add(new Moneda("Libra esterlinas","GBP", 4.67));
        listaMoneda.add(new Moneda("Yen","JPY",0.028));
        listaMoneda.add(new Moneda("Won","KWR",0.0028));
        return listaMoneda;
    }
    public Moneda obtenerSeleccion(){
        
        jComboBox1.addActionListener(
                new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
             monedaResultado=(Moneda) jComboBox1.getSelectedItem();
            
            if(jrbSoles.isSelected()){
                configurarControles(monedaResultado,lblMonedaTotal);
            }else{
                configurarControles(monedaResultado,lblMoneda);
            }
              
            if(!txtMonedaTotal.getText().isEmpty()){
                  System.out.println("Obteniendo");     
                  calcularCambioTotal();
            }
          }
        }
        );
        return monedaResultado;
       
    }
    public Moneda obtenerComboBox(){
        Moneda resultado=(Moneda) jComboBox1.getSelectedItem();
        return resultado;
    }
    public void configurarControles(){
        lblMoneda.setText("PEN");
        lblMoneda.setFont(new Font("Tahoma",Font.BOLD, 13));
        lblMonedaTotal.setText("PEN");
        lblMonedaTotal.setFont(new Font("Tahoma",Font.BOLD,13));
        txtMonedaTotal.setEditable(false);
    }
    public void configurarControles(Moneda moneda){
        lblMoneda.setText("PEN");
        lblMoneda.setFont(new Font("Tahoma",Font.BOLD, 13));
        lblMonedaTotal.setText(moneda.getMoneda_codigo());
        lblMonedaTotal.setFont(new Font("Tahoma",Font.BOLD,13));
        txtMonedaTotal.setEditable(false);
    }
    public void configurarControles(Moneda moneda,JLabel etiqueta){
        etiqueta.setText(moneda.getMoneda_codigo());
        etiqueta.setFont(new Font("Tahoma",Font.BOLD,13));
        txtMonedaTotal.setEditable(false);
    }
    public void digitarMoneda(){
         txtMoneda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.')) {
                    e.consume();
                }
            }
        });
    }
    public void calcularCambioTotal(){

        if(!txtMoneda.getText().isEmpty()){
            Moneda moneda = obtenerComboBox();
            double dinero = Double.parseDouble(txtMoneda.getText());
             double total;
            if(jrbSoles.isSelected()){
                total = dinero/moneda.getMoneda_valor();
            }else{
                total = dinero*moneda.getMoneda_valor();
            }
           
            double totalRedondeado=Math.round(total*1000.0)/1000.0;
            txtMonedaTotal.setText(String.valueOf(totalRedondeado));
            System.out.println(totalRedondeado);
        }else{
            System.out.println("Caja vacia");
            JOptionPane.showMessageDialog(rootPane, "Ingrese un valor en la caja de monedas");
            txtMoneda.requestFocus();
        }
    }
    public void setRadioButton(){
        btgMoneda.add(jrbSoles);
        btgMoneda.add(jrbOtras);
        getRadioButton();

    }
    public void getRadioButton(){
            ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jrbSoles.isSelected()) {
                    System.out.println("Opción 1 seleccionada");
                    setCodigoMoneda(lblMoneda);
                    //aqui tiene que ir el auto selection
                    Moneda resultado = obtenerComboBox();
                    configurarControles(resultado,lblMonedaTotal);
                } else if (jrbOtras.isSelected()) {
                    System.out.println("Opción 2 seleccionada");
                    setCodigoMoneda(lblMonedaTotal);
                    //aqui tiene que ir el auto selection
                    Moneda resultado = obtenerComboBox();
                    configurarControles(resultado,lblMoneda);
                } 
                calcularCambioTotal();
            }
        };
        
        jrbSoles.addActionListener(actionListener);
        jrbOtras.addActionListener(actionListener);
   
    }
    public void setCodigoMoneda(JLabel etiqueta){
        System.out.println(etiqueta.getText());
        etiqueta.setText("PEN");
    }
    void print(){
        System.out.println("Es");
    }
    public void convertirTemperatura(){
        double celsius = Double.parseDouble(txtCelsius.getText());
        double converTemp=(celsius * 1.8)+32;
        txtFaren.setText(String.valueOf(converTemp));
    }
    public void limpiarCajas(){
        txtMoneda.setText("");
        txtMonedaTotal.setText("");
        txtCelsius.setText("");
        txtFaren.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgMoneda = new javax.swing.ButtonGroup();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtMoneda = new javax.swing.JTextField();
        btnCalcularCambio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtMonedaTotal = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblMoneda = new javax.swing.JLabel();
        lblMonedaTotal = new javax.swing.JLabel();
        jrbSoles = new javax.swing.JRadioButton();
        jrbOtras = new javax.swing.JRadioButton();
        btnConvTemp = new javax.swing.JButton();
        txtFaren = new javax.swing.JTextField();
        txtCelsius = new javax.swing.JTextField();
        lblFahren = new javax.swing.JLabel();
        lblCelsius = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Moneda");

        txtMoneda.setText("0");

        btnCalcularCambio.setText("Calcular");
        btnCalcularCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularCambioActionPerformed(evt);
            }
        });

        jLabel4.setText("Total");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lblMoneda.setText("    ");

        lblMonedaTotal.setText("  ");

        jrbSoles.setText("De soles a otras monedas");

        jrbOtras.setText("De otras monedas a soles");

        btnConvTemp.setText("Convertir");
        btnConvTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvTempActionPerformed(evt);
            }
        });

        txtCelsius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelsiusActionPerformed(evt);
            }
        });

        lblFahren.setText("°F");

        lblCelsius.setText("°C");

        jLabel5.setText("Conversor de Temperatura");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCelsius, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCelsius, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnConvTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFahren, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jrbSoles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(jrbOtras)
                        .addGap(107, 107, 107))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMoneda)
                                    .addComponent(jComboBox1, 0, 122, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnCalcularCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(87, 87, 87))
                                    .addComponent(txtMonedaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMonedaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtFaren))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrbSoles)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(lblFahren, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCelsius, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCelsius, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnConvTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFaren, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrbOtras)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMonedaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMonedaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(328, 328, 328))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(btnCalcularCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalcularCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularCambioActionPerformed
        // TODO add your handling code here:
       calcularCambioTotal();
        
    }//GEN-LAST:event_btnCalcularCambioActionPerformed

    private void txtCelsiusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelsiusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelsiusActionPerformed

    private void btnConvTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvTempActionPerformed
        // TODO add your handling code here:
        convertirTemperatura();
    }//GEN-LAST:event_btnConvTempActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCajas();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JfConversor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfConversor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfConversor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfConversor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfConversor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgMoneda;
    private javax.swing.JButton btnCalcularCambio;
    private javax.swing.JButton btnConvTemp;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<Moneda> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jrbOtras;
    private javax.swing.JRadioButton jrbSoles;
    private javax.swing.JLabel lblCelsius;
    private javax.swing.JLabel lblFahren;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblMonedaTotal;
    private javax.swing.JTextField txtCelsius;
    private javax.swing.JTextField txtFaren;
    private javax.swing.JTextField txtMoneda;
    private javax.swing.JTextField txtMonedaTotal;
    // End of variables declaration//GEN-END:variables


}
