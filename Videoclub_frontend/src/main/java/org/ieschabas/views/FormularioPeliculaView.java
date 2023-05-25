package org.ieschabas.views;




import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.ieschabas.clases.Pelicula;
import org.ieschabas.daos.PeliculaDao;
import org.ieschabas.enums.Categoria;
import org.ieschabas.enums.Formato;
import org.ieschabas.enums.Valoracion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;




@Route("formulariopeliculaview")
public class FormularioPeliculaView extends FormLayout {
	//GestorPeliculas gestorPeliculas = new GestorPeliculas();
	ArrayList<Pelicula> listarPelicula = new ArrayList<>();
	FormLayout formularioPelicula = new FormLayout();

	//File ficheroPelicula = new File("peliculas.csv");

    public FormularioPeliculaView() throws IOException {

        iniciarFromulario();







    }

	private void iniciarFromulario() {
		  TextField titulo = new TextField("Título");
	        TextField descripcion = new TextField("Descripcion");
	        TextField duracion = new TextField("Duracion");
	        TextField anyoPublicacion = new TextField("Año de publicación");
	        ComboBox<Categoria> categoria = new ComboBox<>("Categoria");
	        categoria.setItems(Categoria.values());
	        ComboBox<Formato> formato = new ComboBox<>("Formato");
	        formato.setItems(Formato.values());
	        ComboBox<Valoracion> valoracion = new ComboBox<>("Valoracion");
	        valoracion.setItems(Valoracion.values());
	        /**
			 * El botón de guardar de el Director con un clicklistener para que cuando haga
			 * click en guardar escriba los datos en el fichero mediante el metodo de
			 * crearPelicula
			 */

	            Button guardar = new Button("Guardar");
	            guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
	                    ButtonVariant.LUMO_SUCCESS);
	            guardar.addClickListener(clickEvent -> {

	                	Pelicula pelicula = new Pelicula();
	                	pelicula.setTitulo(titulo.getValue());
	            		pelicula.setDescripcion(descripcion.getValue());
	            		pelicula.setDuracion(duracion.getValue());
	            		pelicula.setCategoria(categoria.getValue());
	            		pelicula.setFormato(formato.getValue());
	            		pelicula.setValoracion(valoracion.getValue());
	            		SimpleDateFormat fecha1 = new SimpleDateFormat("yyyy");

						try {
						Date fecha = fecha1.parse(anyoPublicacion.getValue());
						pelicula.setAnyoPublicacion(fecha);
						} catch (ParseException e) {
							e.printStackTrace();
						}


	                	PeliculaDao.guardarPelicula(pelicula);
						//listarPelicula = gestorPeliculas.crearPelicula(ficheroPelicula,listarPelicula,titulo.getValue(),descripcion.getValue(),duracion.getValue(),(Categoria)categoria.getValue(),(Formato)formato.getValue(),(Valoracion)valoracion.getValue(),anyoPublicacion.getValue());


	            });
	        	/**
	    		 * Introducimos los campos de texto y los botones al formulario
	    		 */

	        formularioPelicula.add(titulo, descripcion, duracion,
	        		anyoPublicacion,categoria,formato,valoracion,guardar);
	        formularioPelicula.setResponsiveSteps(
	                // Use one column by default
	                new ResponsiveStep("0", 1),
	                // Use two columns, if layout's width exceeds 500px
	                new ResponsiveStep("500px", 2));
	        // Stretch the username field over 2 columns
	        formularioPelicula.setColspan(titulo, 2);
	        // end::snippet[]


	        add(formularioPelicula);

	}





}




