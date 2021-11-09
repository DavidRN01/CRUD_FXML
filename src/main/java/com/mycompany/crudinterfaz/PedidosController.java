/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudinterfaz;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Pedido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author david
 */
public class PedidosController implements Initializable {

    @FXML
    private Button btnCarta;
    @FXML
    private Button btnCrearPedido;
    @FXML
    private Button btnListarHoy;
    @FXML
    private Button btnListarTodo;
    @FXML
    private Button btnRecogido;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<Pedido> tabla;
    @FXML
    private TableColumn<Pedido, Long> colId;
    @FXML
    private TableColumn<Pedido, String> colNombre;
    @FXML
    private TableColumn<Pedido, Double> colPrecio;
    @FXML
    private TableColumn<Pedido, Date> colFecha;
    @FXML
    private TableColumn<Pedido, String> colEstado;
    @FXML
    private Label lblNumero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        actualizar();
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    actualizar();
                });
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
        
    }    

    @FXML
    private void mostrarCarta(ActionEvent event) {
        
        try {
            App.setRoot("carta");
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void crearPedido(ActionEvent event) {
        
        try {
            App.setRoot("crearPedido");
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void listarHoy(ActionEvent event) {
        
        try {
            App.setRoot("comandasHoy");
        } catch (IOException ex) {
            Logger.getLogger(CartaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void listarTodo(ActionEvent event) {
        
        actualizar();
        
    }

    @FXML
    private void marcarRecogido(ActionEvent event) {
        
        Pedido pedidoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Pedido pedido = s.load(Pedido.class, pedidoSeleccionado.getId());
        pedido.setEstado("RECOGIDO");
        s.update(pedido);
        t.commit();
        
        actualizar();
        
    }

    @FXML
    private void borrarPedido(ActionEvent event) {
        
        Pedido pedidoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Pedido pedido = s.load(Pedido.class, pedidoSeleccionado.getId());
        s.remove(pedido);
        t.commit();
        
        actualizar();
        
    }

    @FXML
    private void seleccionElemento(MouseEvent event) {
    }
    
    private void actualizar() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        ObservableList<Pedido> contenido = FXCollections.observableArrayList();
        tabla.setItems(contenido);
        
        Query q = s.createQuery("FROM Pedido", Pedido.class);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        lblNumero.setText("Hay "+resultado.size()+" pedidos");
        
        contenido.addAll(resultado);
    }
    
}
