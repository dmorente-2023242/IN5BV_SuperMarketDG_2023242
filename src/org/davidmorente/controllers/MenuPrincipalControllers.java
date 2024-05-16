/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.davidmorente.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.davidmorente.system.Main;

/**
 *
 * @author informatica
 */
public class MenuPrincipalControllers implements Initializable {
    private Main escenarioPrincipal;
    @FXML MenuItem btnMenuclientes;
    @FXML MenuItem btnProveedor;
    @FXML MenuItem btnProgramador;
    
    @Override
    public void initialize (URL location, ResourceBundle resources)  {

    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
        
    @FXML
    public void clicClientes (ActionEvent event)throws IOException{
        if (event.getSource() == btnMenuclientes){
            System.out.println("SI ENTRO");
            escenarioPrincipal.menuClientesView();
        }
    }
    
    @FXML
    public void clicProveedores (ActionEvent event)throws IOException{
        if (event.getSource() == btnProveedor){
            escenarioPrincipal.menuProveedoresView();
        }
    }
    
    @FXML
    public void clicProgramador (ActionEvent event)throws IOException{
        if (event.getSource() == btnProgramador){
            escenarioPrincipal.verVentanaProgramador();
        }
    }
    
    @FXML
    public void clicProveedor (ActionEvent event)throws IOException{
        if (event.getSource() == btnProveedor){
            escenarioPrincipal.verVentanaProveedor();
        }
    }
}
