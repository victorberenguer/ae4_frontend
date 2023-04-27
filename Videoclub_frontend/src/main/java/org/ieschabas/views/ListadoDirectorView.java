package org.ieschabas.views;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;


import org.ieschabas.clases.Director;
import org.ieschabas.librerias.GestorDirector;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;



@Route("ListadoDirectorView")
public class ListadoDirectorView extends HorizontalLayout {
	
	 Grid<Director> gridDirector = new Grid<>(Director.class, false);
	 GestorDirector gestorDirector = new GestorDirector();
	 private static ArrayList<Director> director = new ArrayList<Director>();
	 File ficheroDirector = new File("directores.csv");
	 
	 public ListadoDirectorView() throws IOException {
		 director= gestorDirector.listarDirectores(director,ficheroDirector);
		 
	       
		 
	        gridDirector.addColumn(Director::getId).setHeader("Id Directores");
	        gridDirector.addColumn(Director::getNombre).setHeader("Nombre");
	        gridDirector.addColumn(Director::getApellidos).setHeader("Apellidos");
	        gridDirector.addColumn(Director::getPais).setHeader("Pais");
	        gridDirector.addColumn(Director::getAnyoNacimiento).setHeader("Año de Nacimiento");
	        
	        
	        gridDirector.setItems(director);
	        
	        
	        
	        Button borrar = new Button(new Icon(VaadinIcon.TRASH));
	        Button modificar = new Button(new Icon(VaadinIcon.PENCIL));
	        add(gridDirector,borrar,modificar);
	      
	        
	       

	 } 
	        
	    }

