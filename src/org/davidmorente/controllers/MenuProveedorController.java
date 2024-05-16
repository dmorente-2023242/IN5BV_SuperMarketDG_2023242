/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.davidmorente.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.davidmorente.bean.Clientes;
import org.davidmorente.bean.Proveedores;
import org.davidmorente.db.Conexion;
import org.davidmorente.system.Main;

/**
 * FXML Controller class
 *
 * @author david
 */
public class MenuProveedorController implements Initializable {
    private Main escenarioProveedor;
    private ObservableList<Proveedores> listaProveedores;
    private enum operaciones {AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO}
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    
    
    @FXML private TextField txtDireccionP;
    @FXML private TextField txtPaginaWeb;
    @FXML private TextField txtCodigoP;
    @FXML private TextField txtNitP;
    @FXML private TextField txtNombreP;
    @FXML private TextField txtApellidoP;
    @FXML private TextField txtContactoP;
    @FXML private TextField txtRazonSocial;
    @FXML private Button btnAgregar;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReportes;
    @FXML private Button btnRegresar;
    @FXML private TableView tblProveedor;
    @FXML private TableColumn colCodigoP;
    @FXML private TableColumn colNombreP;
    @FXML private TableColumn colApellidoP;
    @FXML private TableColumn colNit;
    @FXML private TableColumn colDireccionP;
    @FXML private TableColumn colContactoP;
    @FXML private TableColumn colPaginaWeb;
    @FXML private TableColumn colRazonSocial;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public Main getEscenarioProveedor() {
        return escenarioProveedor;
    }

    public void setEscenarioProveedor(Main escenarioProveedor) {
        this.escenarioProveedor = escenarioProveedor;
    }
    
    public void cargarDatos(){
        tblProveedor.setItems(getProveedores());
        colCodigoP.setCellValueFactory(new PropertyValueFactory<Proveedores, Integer>("codigoProveedor"));
        colNit.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("NITProveedor"));
        colNombreP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nombreProveedor"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("apellidoProveedor"));
        colDireccionP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("direccionProveedor"));
        colContactoP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("contactoProveedor"));
        colPaginaWeb.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("paginaWebProveedor"));
        colRazonSocial.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("razonSocial")); 
    }
    
    public void SeleccionarElemento(){
        txtCodigoP.setText(String.valueOf(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
        txtNombreP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getNombreProveedor());
        txtApellidoP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getApellidoProveedor());
        txtNitP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getNITProveedor());
        txtContactoP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getContactoPrincipal());
        txtDireccionP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getDireccionProveedor());
        txtPaginaWeb.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getDireccionProveedor());
        txtRazonSocial.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getDireccionProveedor());        
    
    }
    
    public ObservableList<Proveedores> getProveedores() {
    ArrayList <Proveedores> lista = new ArrayList(); 
    
    try {
        PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarProveedores()}");
        ResultSet resultado = procedimiento.executeQuery();
        while (resultado.next()) {
            lista.add(new Proveedores(resultado.getInt("codigoProveedor"),
                                       resultado.getString("NITProveedor"),
                                       resultado.getString("nombreProveedor"),
                                       resultado.getString("apellidoProveedor"),
                                       resultado.getString("direccionProveedor"),
                                       resultado.getString("ContactoPrincipal"),
                                       resultado.getString("paginaWebProveedor"),
                                       resultado.getString("razonSocial")
            ));
        }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    
    
    
    return listaProveedores = FXCollections.observableArrayList(lista);
    }
    
    
    
    @FXML
    public void agregar() {
        
    }
    
    @FXML
    public void editar() {
        
    }
    
    @FXML
    public void eliminar() {
        
    }
    
    @FXML
    public void report() {
        
    }
    
    @FXML
    public void clickRegresar(ActionEvent event){
        if (event.getSource() == btnRegresar){
            escenarioProveedor.menuPrincipalView();
        }
    }
    
}
