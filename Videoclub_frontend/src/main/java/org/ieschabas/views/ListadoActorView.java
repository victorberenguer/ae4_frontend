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
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;



@Route("ListadoActorView")
public class ListadoActorView extends HorizontalLayout {
	private Span status;
	
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
					
							        HorizontalLayout layout = new HorizontalLayout();
							        layout.setAlignItems(FlexComponent.Alignment.CENTER);
							        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

							        status = new Span();
							        status.setVisible(false);

							        ConfirmDialog dialog = new ConfirmDialog();
							        dialog.setHeader("Borrrar Actor ");
							        dialog.setText("Seguro que quieres borrar el actor?");

							        dialog.setRejectable(true);
							        dialog.setRejectText("NO");
							        dialog.addRejectListener(event -> setStatus("Discarded"));

							        dialog.setConfirmText("SI");
							        dialog.addConfirmListener(event -> setStatus("Saved"));
							        
					
							       

							        dialog.open();

							        layout.add(button, status);
							        add(layout);

							        // Center the button within the example
							        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
							                .set("bottom", "0").set("left", "0").set("display", "flex")
							                .set("align-items", "center").set("justify-content", "center");
							    

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
								
								  HorizontalLayout layout = new HorizontalLayout();
							        layout.setAlignItems(FlexComponent.Alignment.CENTER);
							        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

							        status = new Span();
							        status.setVisible(false);

							        ConfirmDialog dialog = new ConfirmDialog();
							        dialog.setHeader("Modificar Actor ");
							        dialog.setText("Seguro que quieres modificar el actor?");

							        dialog.setRejectable(true);
							        dialog.setRejectText("NO");
							        dialog.addRejectListener(event -> setStatus("Discarded"));

							        dialog.setConfirmText("SI");
							        dialog.addConfirmListener(event ->
							        dialog.getUI().ifPresent(ui ->
							        
						              ui.navigate("FormularioActorView"))
						        
						   );
							        

							        dialog.open();

							        layout.add(button, status);
							        add(layout);

							        // Center the button within the example
							        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
							                .set("bottom", "0").set("left", "0").set("display", "flex")
							                .set("align-items", "center").set("justify-content", "center");
								
								this.modificarActor(actor);
							} catch (NumberFormatException | IOException e1) {

								e1.printStackTrace();
							}
						});
	                    button.setIcon(new Icon(VaadinIcon.BOOK));
	                    }
	
	                
	            
	                		
	                		)).setHeader("Acciones");
	        
	        gridActor.setItems(actores);
	        
	        
	        
	        Button botonCrear = new Button("Crear Actor");
			 botonCrear.addClickListener(event ->
			 botonCrear.getUI().ifPresent(ui ->
		                   ui.navigate("FormularioActorView"))
		        );
		        
		       
		        
		
	        
	        
	        add(gridActor,botonCrear);
	      
	        
	       

	 }

	private Object modificarActor(Actor actor) throws NumberFormatException, IOException {
		actores = gestoractor.modificarActor(actores, ficheroActor);
		return null;
	}

	private Object eliminarActor(Actor actor) throws NumberFormatException, IOException {
		actores = gestoractor.eliminarActor(actores, ficheroActor);
		return null;
	} 
	        
	    

	
	private void setStatus(String value) {
    status.setText("Status: " + value);
    status.setVisible(true);
}


}
	

