package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ieschabas.clases.Actor;
import org.ieschabas.clases.Equipo;
import org.ieschabas.daos.EquipoDao;
import org.ieschabas.librerias.GestorActor;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;



@Route("ListadoEquipoView")
public class ListadoEquipoView extends HorizontalLayout implements HasUrlParameter<String> {
	private Span status;
	
	 Grid<Equipo> grid = new Grid<>(Equipo.class);
	 GestorActor gestoractor = new GestorActor();
	 private static List<Equipo> personal = new ArrayList<>();
	 File ficheroActor = new File("actores.csv");
	 
	 private static String rol;
	 
	 public ListadoEquipoView() throws IOException {
	        
	       iniciarGrid();
	
	        Button botonCrear = new Button("Crear Actor");
			 botonCrear.addClickListener(event ->
			 botonCrear.getUI().ifPresent(ui ->
		                   ui.navigate("FormularioActorView"))
		        );
		        
		       
			 getElement().getStyle().set("height", "100%");
		        grid.setHeight("100%");
		        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		
	        
	        
	        add(grid,botonCrear);
	      
	        
	       

	 }

	private void iniciarGrid() {
		
		grid.addColumn(Equipo::getId).setHeader("Id Actores");
        grid.addColumn(Equipo::getNombre).setHeader("Nombre");
        grid.addColumn(Equipo::getApellidos).setHeader("Apellidos");
        grid.addColumn(Equipo::getPais).setHeader("Pais");
        grid.addColumn(Equipo::getAnyoNacimiento).setHeader("Año de Nacimiento");
        grid.addColumn(
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
						        dialog.addAttachListener(event -> {
						           dialog.setText("Introduce el ID del actor");
						           TextField id = new TextField();
						           Button confirmar = new Button("confirmar");
						           confirmar.addClickListener(clickEvent -> {
						        	   try {
										personal = gestoractor.eliminarActor(actores, ficheroActor, id.getValue());
										
										
										
									} catch (NumberFormatException | IOException e1) {
										e1.printStackTrace();
									}
						           });
						        	});
				
						       

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
        grid.addColumn(
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
						        dialog.addRejectListener(event -parameter> setStatus("Discarded"));

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
						    

							this.eliminarActor(actor);
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
                    button.setIcon(new Icon(VaadinIcon.BOOK)); 
                    }

                		)).setHeader("Acciones");
		
       
              
	        
		
	}

	private Object modificarActor(Actor actor) throws NumberFormatException, IOException {
		//personal = gestoractor.modificarActor(personal, ficheroActor);
		return null;
	}

	private Object eliminarActor(Actor actor) throws NumberFormatException, IOException {
		//personal = gestoractor.eliminarActor(personal, ficheroActor);
		return null;
	} 		

	        
	    

	
	private void setStatus(String value) {
    status.setText("Status: " + value);
    status.setVisible(true);
}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		this.rol = parameter;
		 personal= EquipoDao.listarEquipo(rol);
		
		 iniciarGrid();

		
	}


}
	

