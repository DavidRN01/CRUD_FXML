/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudinterfaz;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Carta;
import models.Pedido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author DavidRamosNavas
 */
public class CrearPedidoController implements Initializable {

    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TableView<Carta> tabla;
    @FXML
    private TableColumn<Carta, Long> colId;
    @FXML
    private TableColumn<Carta, String> colNombre;
    @FXML
    private TableColumn<Carta, Double> colPrecio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Carta> contenido = FXCollections.observableArrayList();
        tabla.setItems(contenido);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        Session s = HibernateUtil.getSessionFactory().openSession();

        Query q = s.createQuery("FROM Carta", Carta.class);
        ArrayList<Carta> resultado = (ArrayList<Carta>) q.list();

        contenido.addAll(resultado);

    }

    @FXML
    private void añadir(ActionEvent event) {

        Carta producto = tabla.getSelectionModel().getSelectedItem();
        Pedido pedido = new Pedido();

        pedido.setId(0L);
        pedido.setEstado("SIN ENTREGAR");
        pedido.setNombre(txtNombre.getText());
        pedido.setPrecio(producto.getPrecio());
        pedido.setProducto_id(producto.getId());

        java.util.Date ahora = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(ahora.getTime());
        pedido.setFecha(fechaActual);

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = s.beginTransaction();
        s.save(pedido);
        tr.commit();

        try {
            App.setRoot("pedidos");
        } catch (IOException ex) {
            Logger.getLogger(CrearPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancelar(ActionEvent event) {

        try {
            App.setRoot("pedidos");
        } catch (IOException ex) {
            Logger.getLogger(CrearPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
