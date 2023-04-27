package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ieschabas.clases.Pelicula;
import org.ieschabas.librerias.GestorPeliculas;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("ListadoPelicula")
public class ListadoPeliculasView extends FormLayout{
	 Grid<Pelicula> gridPelicula = new Grid<>(Pelicula.class, false);
	 GestorPeliculas gestorPeliculas = new GestorPeliculas();
	 private static ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	 File ficheroPelicula= new File("peliculas.csv");
	 
	public  ListadoPeliculasView() throws IOException  {
		 peliculas = gestorPeliculas.listarPelicula(peliculas,ficheroPelicula);
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
								this.eliminarPelicula(pelicula);
							} catch (NumberFormatException | IOException e1) {

								e1.printStackTrace();
							}
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
								this.modificarPelicula(pelicula);
							} catch (NumberFormatException | IOException e1) {
								e1.printStackTrace();
							}
						});
	                    button.setIcon(new Icon(VaadinIcon.BOOK));
	                    }
	
	                
	            
	                		
	                		)).setHeader("Acciones");
		 
	      
		 gridPelicula.setItems(peliculas);
	        
	        
	        add(gridPelicula);
	        
	}

	private Object modificarPelicula(Pelicula pelicula) throws NumberFormatException, IOException {
		gestorPeliculas.modificarPelicula(peliculas, ficheroPelicula);
		return null;
	}

	private Object eliminarPelicula(Pelicula pelicula) throws NumberFormatException, IOException {
		gestorPeliculas.eliminarPelicula(peliculas,ficheroPelicula);
		return null;
	}
	
}