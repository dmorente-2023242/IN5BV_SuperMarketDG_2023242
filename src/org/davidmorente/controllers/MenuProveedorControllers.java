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
import java.util.HashSet;
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
import org.davidmorente.bean.Proveedores;
import org.davidmorente.db.Conexion;
import org.davidmorente.system.Main;

/**
 *
 * @author informatica
 */
public class MenuProveedorControllers implements Initializable {

    private ObservableList<Proveedores> listaProveedores;
    private Main escenarioProveedores;
    private enum operaciones {AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO}
    private operaciones  tipoDeOperaciones = operaciones.NINGUNO;
    
    @FXML Button btnRegresar3;
    @FXML private TextField txtDireccionP;
    @FXML private TextField txtWebP;
    @FXML private TextField txtCodigoP;
    @FXML private TextField txtNITP;
    @FXML private TextField txtNombreP;
    @FXML private TextField txtApellidoP;
    @FXML private TextField txtContactoP;
    @FXML private TextField txtRazonSocial;    
    @FXML private TableView tblProveedor;
    @FXML private TableColumn colCodigoP;
    @FXML private TableColumn colNombreP;
    @FXML private TableColumn colApellidoP;
    @FXML private TableColumn colNit;
    @FXML private TableColumn colDireccionP;
    @FXML private TableColumn colContactoP;
    @FXML private TableColumn colWebP;
    @FXML private TableColumn colRazonSocial;    
    @FXML private Button btnAgregarProveedor;
    @FXML private Button btnEliminarProveedor;
    @FXML private Button btnEditarProveedor;
    @FXML private Button btnReportesProveedor;
    @FXML private ImageView imgAgregar;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    @FXML private ImageView imgReporte;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }
    
    public void cargarDatos(){
        tblProveedor.setItems(getProveedores());
        colCodigoP.setCellValueFactory(new PropertyValueFactory<Proveedores, Integer>("codigoProveedor"));
        colNit.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("NITProveedor"));
        colNombreP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nombreProveedor"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("apellidoProveedor"));
        colDireccionP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("direccionProveedor"));
        colContactoP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("contactoProveedor"));
        colWebP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("paginaWebProveedor"));
        colRazonSocial.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("razonSocial"));

        
    }
    public void SeleccionarElemento(){
        txtCodigoP.setText(String.valueOf(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
        txtNombreP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getNombreProveedor());
        txtApellidoP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getApellidoProveedor());
        txtNITP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getNITProveedor());
        txtContactoP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getContactoPrincipal());
        txtDireccionP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getDireccionProveedor());
        txtWebP.setText(((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getDireccionProveedor());
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
    
    public void agregar() {
    switch (tipoDeOperaciones) {
        case NINGUNO:
            ActivarText();
            btnAgregarProveedor.setText("Guardar");
            btnEliminarProveedor.setText("Eliminar");
            btnEditarProveedor.setDisable(true);
            btnReportesProveedor.setDisable(true);
            tipoDeOperaciones = operaciones.ACTUALIZAR;
            break;
        default:
            case ACTUALIZAR:
                guardar();
                desactivarText();
                LimpiarText();
                btnAgregarProveedor.setText("Agregar");
                btnEliminarProveedor.setText("Eliminar");
                btnEditarProveedor.setDisable(false);
                btnReportesProveedor.setDisable(false);
                tipoDeOperaciones = operaciones.NINGUNO;
        }
    }
    public void guardar(){
    Proveedores registro = new Proveedores();
    registro.setCodigoProveedor(Integer.parseInt(txtCodigoP.getText()));
    registro.setNITProveedor((txtNITP.getText()));
    registro.setNombreProveedor((txtNombreP.getText()));
    registro.setApellidoProveedor((txtApellidoP.getText()));
    registro.setDireccionProveedor((txtDireccionP.getText()));
    registro.setRazonSocial((txtRazonSocial.getText()));
    registro.setContactoPrincipal((txtContactoP.getText()));
    registro.setPaginaWebProveedor((txtWebP.getText()));
    
    
    try{
        PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_AgregarProveedores(?, ?, ?, ?, ?, ?, ?, ?)}");
        procedimiento.setInt(1, registro.getCodigoProveedor());
        procedimiento.setString(2, registro.getNITProveedor());
        procedimiento.setString(3, registro.getNombreProveedor());
        procedimiento.setString(4, registro.getApellidoProveedor());
        procedimiento.setString(5, registro.getDireccionProveedor());
        procedimiento.setString(6, registro.getRazonSocial());
        procedimiento.setString(7, registro.getContactoPrincipal());
        procedimiento.setString(8, registro.getPaginaWebProveedor());
        listaProveedores.add(registro);
        
        procedimiento.execute();
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    }
    
    
    public void Eliminar(){
        switch(tipoDeOperaciones){
            case ACTUALIZAR:
                desactivarText();
                LimpiarText();
                btnAgregarProveedor.setText("Agregar");
                btnEliminarProveedor.setText("Eliminar");
                btnEditarProveedor.setDisable(false);
                btnReportesProveedor.setDisable(false);
                imgAgregar.setImage(new Image("/org/davidmorente/image/Agregar.png"));
                imgEliminar.setImage(new Image("/org/davidmorente/image/Eliminar.png"));
                tipoDeOperaciones = operaciones.NINGUNO;
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
            default:
                if(tblProveedor.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar si eliminar el registro", "Eliminar Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respuesta == JOptionPane.YES_NO_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_EliminarProveedores(?)} ");
                            procedimiento.setInt(1, ((Proveedores)tblProveedor.getSelectionModel().getSelectedItem()).getCodigoProveedor());
                            listaProveedores.remove(tblProveedor.getSelectionModel().getSelectedItem());
                        
                        
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un elemento");
        
        }
    }
    
    public void editar(){
        switch (tipoDeOperaciones){
            case NINGUNO:
                if(tblProveedor.getSelectionModel().getSelectedItem() != null){
                    ActivarText();
                    btnEditarProveedor.setText("Actualizar");
                    btnReportesProveedor.setText("Cancelar");
                    btnAgregarProveedor.setDisable(true);
                    btnEliminarProveedor.setDisable(true);
                    imgEditar.setImage(new Image("/org/davidmorente/image/Editar.png"));
                    imgReporte.setImage(new Image("/org/davidmorente/image/cancelar.png"));
                    txtCodigoP.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                }else
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar algun elemento");
            break;
            case ACTUALIZAR:
                actualizar();
                btnEditarProveedor.setText("Editar");
                btnReportesProveedor.setText("Reportes");
                btnAgregarProveedor.setDisable(false);
                btnEliminarProveedor.setDisable(false);
                imgEditar.setImage(new Image("/org/davidmorente/image/Agregar.png"));
                imgReporte.setImage(new Image("/org/davidmorente/image/reporte.png"));
                desactivarText();
                LimpiarText();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();

            break;
        }

    }
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_EditarProveedores(?, ?, ?, ?, ?, ?, ?, ?)}");
            Proveedores registro = (Proveedores)tblProveedor.getSelectionModel().getSelectedItem();
            registro.setNITProveedor(txtNITP.getText());
            registro.setNombreProveedor(txtNombreP.getText());
            registro.setApellidoProveedor(txtApellidoP.getText());
            registro.setDireccionProveedor(txtDireccionP.getText());
            registro.setRazonSocial(txtRazonSocial.getText());
            registro.setContactoPrincipal(txtContactoP.getText());
            registro.setPaginaWebProveedor(txtWebP.getText());
           
            procedimiento.setInt(1, registro.getCodigoProveedor());
            procedimiento.setString(2, registro.getNITProveedor());
            procedimiento.setString(3, registro.getNombreProveedor());
            procedimiento.setString(4, registro.getApellidoProveedor());
            procedimiento.setString(5, registro.getDireccionProveedor());
            procedimiento.setString(6, registro.getRazonSocial());
            procedimiento.setString(7, registro.getContactoPrincipal());
            procedimiento.setString(8, registro.getPaginaWebProveedor());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public void desactivarText(){
        txtCodigoP.setEditable(false);
        txtNombreP.setEditable(false);
        txtApellidoP.setEditable(false);
        txtDireccionP.setEditable(false);
        txtWebP.setEditable(false);
        txtNITP.setEditable(false);
        txtContactoP.setEditable(false);
        txtRazonSocial.setEditable(false);

        
    }
    
     public void ActivarText(){
        txtCodigoP.setEditable(true);
        txtNombreP.setEditable(true);
        txtApellidoP.setEditable(true);
        txtDireccionP.setEditable(true);
        txtWebP.setEditable(true);
        txtNITP.setEditable(true);
        txtContactoP.setEditable(true);
        txtRazonSocial.setEditable(true);
        
     }
     
     public void LimpiarText(){
        txtCodigoP.clear();
        txtNombreP.clear();
        txtApellidoP.clear();
        txtDireccionP.clear();
        txtWebP.clear();
        txtNITP.clear();
        txtContactoP.clear();
        
     }

    public Main getEscenarioPrincipalProveedores() {
        return escenarioProveedores;
    }

    public void setEscenarioPrincipalProveedores(Main escenarioPrincipalProveedores) {
        this.escenarioProveedores = escenarioPrincipalProveedores;
    }
    
    @FXML
    public void clickRegresar(ActionEvent event){
         if (event.getSource() == btnRegresar3){
        escenarioProveedores.menuPrincipalView();
        }
    } 
}