package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ieschabas.clases.Pelicula;
import org.ieschabas.daos.PeliculaDao;
import org.ieschabas.librerias.GestorPeliculas;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("ListadoPeliculasView")
public class ListadoPeliculasView extends FormLayout{
	
	private Span status;
	 Grid<Pelicula> gridPelicula = new Grid<>(Pelicula.class, false);
	 GestorPeliculas gestorPeliculas = new GestorPeliculas();
	 private static ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	 File ficheroPelicula= new File("peliculas.csv");
	 
	public  ListadoPeliculasView() throws IOException  {
		
		iniciarGrid();
		
		
	      
		 gridPelicula.setItems(peliculas);
		 Button botonCrear = new Button("Crear Pelicula");
		 botonCrear.addClickListener(event ->
		 botonCrear.getUI().ifPresent(ui ->
	                   ui.navigate("FormularioPeliculaView"))
	        );
		 
		 
	
		 
	        
	        add(gridPelicula,botonCrear);
	        
	}

	private void iniciarGrid() {
		PeliculaDao.obtenerPelicula();
        // tag::snippet[]
	   
	 gridPelicula.addColumn(Pelicula::getId).setHeader("Id Pelicula");
	 gridPelicula.addColumn(Pelicula::getTitulo).setHeader("Pelicula");
	 gridPelicula.addColumn(Pelicula::getCategoria).setHeader("Categoria");
	 gridPelicula.addColumn(Pelicula::getValoracion).setHeader("Valoracion");
	 gridPelicula.addColumn(Pelicula::getFormato).setHeader("Formato");
	 gridPelicula.addColumn(Pelicula::getDuracion).setHeader("Duracion");
	 gridPelicula.addColumn(Pelicula::getAnyoPublicacion).setHeader("Año de publicacion");
	 gridPelicula.addColumn(Pelicula::getDescripcion).setHeader("Descripcion");
	 gridPelicula.addColumn(
                new ComponentRenderer<>(Button::new, (button, pelicula) ->{
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
						        dialog.setHeader("Eliminar Pelicula ");
						        dialog.setText("Seguro que quieres eliminar la pelicula?");

						        dialog.setRejectable(true);
						        dialog.setRejectText("NO");
						        dialog.addRejectListener(event -> setStatus("No se ha eliminado la pelicula"));

						        dialog.setConfirmText("SI");
						        dialog.addConfirmListener(event -> setStatus("Se ha eliminado la pelicula correctamente"));

						       

						        dialog.open();

						        layout.add(button, status);
						        add(layout);
						        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
						                .set("bottom", "0").set("left", "0").set("display", "flex")
						                .set("align-items", "center").set("justify-content", "center");
							
							
							
						} catch (NumberFormatException e1) {e1.printStackTrace();}
					});
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                    }

                
            
                		
                		)).setHeader("Acciones");
        
	 gridPelicula.addColumn(
                new ComponentRenderer<>(Button::new, (button, pelicula) ->{
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
						        dialog.setHeader("Modificar Pelicula ");
						        dialog.setText("Seguro que quieres modificar la pelicula?");

						        dialog.setRejectable(true);
						        dialog.setRejectText("NO");
						        dialog.addRejectListener(event -> setStatus("No se ha modificado"));

						        dialog.setConfirmText("SI");
						        dialog.addConfirmListener(event ->
						        dialog.getUI().ifPresent(ui ->
						        
					              ui.navigate("FormularioPeliculaView"))
					        
					   );
						       

						        dialog.open();

						        layout.add(button, status);
						        add(layout);

						        // Center the button within the example
						        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
						                .set("bottom", "0").set("left", "0").set("display", "flex")
						                .set("align-items", "center").set("justify-content", "center");
							

						} catch (NumberFormatException e1) {e1.printStackTrace();}
					});
                    button.setIcon(new Icon(VaadinIcon.BOOK));
                    }

                
            
                		
                		)).setHeader("Acciones");

		
	}

	private void setStatus(String value) {
	    status.setText("Estado;" + value);
	    status.setVisible(true);
	   
	    
	}

	
}