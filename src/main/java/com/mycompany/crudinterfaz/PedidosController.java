/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudinterfaz;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Pedido;
import org.hibernate.Session;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Pedido> contenido = FXCollections.observableArrayList();
        tabla.setItems(contenido);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        Query q = s.createQuery("FROM Pedido", Pedido.class);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        
        contenido.addAll(resultado);
        
    }    

    @FXML
    private void mostrarCarta(ActionEvent event) {
    }

    @FXML
    private void crearPedido(ActionEvent event) {
    }

    @FXML
    private void listarHoy(ActionEvent event) {
        
        java.util.Date ahora = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(ahora.getTime());
        
        ObservableList<Pedido> contenido = FXCollections.observableArrayList();
        tabla.setItems(contenido);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Pedido p WHERE p.estado = 'SIN ENTREGAR' AND p.fecha = :fecha", Pedido.class);
        q.setParameter("fecha", fechaActual);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        
        contenido.addAll(resultado);
        
    }

    @FXML
    private void listarTodo(ActionEvent event) {
        
        ObservableList<Pedido> contenido = FXCollections.observableArrayList();
        tabla.setItems(contenido);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        Query q = s.createQuery("FROM Pedido", Pedido.class);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        
        contenido.addAll(resultado);
        
    }

    @FXML
    private void marcarRecogido(ActionEvent event) {
    }

    @FXML
    private void borrarPedido(ActionEvent event) {
    }

    @FXML
    private void seleccionElemento(MouseEvent event) {
    }
    
}
