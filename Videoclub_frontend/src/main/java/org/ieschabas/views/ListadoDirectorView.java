package org.ieschabas.views;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import org.ieschabas.clases.Actor;
import org.ieschabas.clases.Director;
import org.ieschabas.librerias.GestorDirector;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
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
	        gridDirector.addColumn(
	                new ComponentRenderer<>(Button::new, (button, director) ->{
	                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
	                            ButtonVariant.LUMO_ERROR,
	                            ButtonVariant.LUMO_TERTIARY);
	                    button.addClickListener(e -> {
							try {
								this.eliminarDirector(director);
							} catch (NumberFormatException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
	                    button.setIcon(new Icon(VaadinIcon.TRASH));
	                    }
	
	                
	            
	                		
	                		)).setHeader("Acciones");
	        
		 gridDirector.addColumn(
	                new ComponentRenderer<>(Button::new, (button, director) ->{
	                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
	                            ButtonVariant.LUMO_ERROR,
	                            ButtonVariant.LUMO_TERTIARY);
	                    button.addClickListener(e -> {
							try {
								this.modificarDirector(director);
							} catch (NumberFormatException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
	                    button.setIcon(new Icon(VaadinIcon.BOOK));
	                    }
	
	                
	            
	                		
	                		)).setHeader("Acciones");
	        
	        gridDirector.setItems(director);
	        
	        
	   
	        add(gridDirector);
	      
	        
	       

	 } 
	 
	 private Object modificarDirector(Director directores) throws NumberFormatException, IOException {
			director= gestorDirector.modificarDirectores(director, ficheroDirector);
			return director;
		}

		private Object eliminarDirector(Director directores) throws NumberFormatException, IOException {
			director = gestorDirector.eliminarDirectores(director, ficheroDirector);
			
			return director;
		} 
	        
	    }

