/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudinterfaz;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
/**
 * FXML Controller class
 *
 * @author david
 */
public class InformeFechasController implements Initializable {


    @FXML
    private DatePicker dateDesde;
    @FXML
    private DatePicker dateHasta;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        
        LocalDate fechaDesde = dateDesde.getValue();
        LocalDate fechaHasta = dateHasta.getValue();
        
        java.sql.Date fechaDesdeSQL = java.sql.Date.valueOf(fechaDesde);
        java.sql.Date fechaHastaSQL = java.sql.Date.valueOf(fechaHasta);
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        java.sql.Date fechaDesdeSinhoras = new java.sql.Date(fechaDesdeSQL.getTime());
        java.sql.Date fechaHastaSinhoras = new java.sql.Date(fechaHastaSQL.getTime());
        
        String archivo = "pedidos.jrxml";
        
        try {
            
            var parameters = new HashMap();
            parameters.put("fechaDesde", fechaDesdeSinhoras);
            parameters.put("fechaHasta", fechaHastaSinhoras);
            
            JasperReport informe = JasperCompileManager.compileReport(archivo);
            JasperPrint impresion = JasperFillManager.fillReport(informe, parameters, ConexionJasper.getConexion());
        
            JRViewer visor = new JRViewer(impresion);
            javax.swing.JFrame ventanaInforme = new javax.swing.JFrame("Informe");
            ventanaInforme.getContentPane().add(visor);
            
            ventanaInforme.pack();
            ventanaInforme.setVisible(true);
            
            var exportador = new JRPdfExporter();
            exportador.setExporterInput(new SimpleExporterInput(impresion));
            exportador.setExporterOutput(new SimpleOutputStreamExporterOutput("informe_historial.pdf"));
            
            var configuracion = new SimplePdfExporterConfiguration();
            exportador.setConfiguration(configuracion);
            
            exportador.exportReport();
            
        } catch (JRException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        
        try {
            App.setRoot("pedidos");
        } catch (IOException ex) {
            Logger.getLogger(InformeFechasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
