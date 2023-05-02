package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import org.ieschabas.clases.Actor;

import org.ieschabas.librerias.GestorActor;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;



@Route("ListadoActorView")
public class ListadoActorView extends HorizontalLayout {
	
	 Grid<Actor> gridActor = new Grid<>(Actor.class, false);
	 GestorActor gestoractor = new GestorActor();
	 private static ArrayList<Actor> actores = new ArrayList<Actor>();
	 File ficheroActor = new File("actores.csv");
	 
	 public ListadoActorView() throws IOException {
		 actores= gestoractor.listarActores(actores,ficheroActor);
		 
	       
		 
	        gridActor.addColumn(Actor::getId).setHeader("Id Actores");
	        gridActor.addColumn(Actor::getNombre).setHeader("Nombre");
	        gridActor.addColumn(Actor::getApellidos).setHeader("Apellidos");
	        gridActor.addColumn(Actor::getPais).setHeader("Pais");
	        gridActor.addColumn(Actor::getAnyoNacimiento).setHeader("Año de Nacimiento");
	   	 gridActor.addColumn(
	                new ComponentRenderer<>(Button::new, (button, actor) ->{
	                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
	                            ButtonVariant.LUMO_ERROR,
	                            ButtonVariant.LUMO_TERTIARY);
	                    button.addClickListener(e -> {
							try {
								this.eliminarActor(actor);
							} catch (NumberFormatException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
	                    button.setIcon(new Icon(VaadinIcon.TRASH));
	                    }
	
	                
	            
	                		
	                		)).setHeader("Acciones");
	        
		 gridActor.addColumn(
	                new ComponentRenderer<>(Button::new, (button, actor) ->{
	                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
	                            ButtonVariant.LUMO_ERROR,
	                            ButtonVariant.LUMO_TERTIARY);
	                    button.addClickListener(e -> {
							try {
								this.modificarActor(actor);
							} catch (NumberFormatException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
	                    button.setIcon(new Icon(VaadinIcon.BOOK));
	                    }
	
	                
	            
	                		
	                		)).setHeader("Acciones");
	        
	        gridActor.setItems(actores);
	        
	        
	        
	        add(gridActor);
	      
	        
	       

	 }

	private Object modificarActor(Actor actor) throws NumberFormatException, IOException {
		actores = gestoractor.modificarActor(actores, ficheroActor);
		return null;
	}

	private Object eliminarActor(Actor actor) throws NumberFormatException, IOException {
		actores = gestoractor.eliminarActor(actores, ficheroActor);
		return null;
	} 
	        
	    }

	
	
	

