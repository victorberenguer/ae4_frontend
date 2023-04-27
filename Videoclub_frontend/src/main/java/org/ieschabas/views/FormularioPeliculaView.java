package org.ieschabas.views;




import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ieschabas.clases.Actor;
import org.ieschabas.clases.Pelicula;
import org.ieschabas.enums.Categoria;
import org.ieschabas.enums.Formato;
import org.ieschabas.enums.Valoracion;
import org.ieschabas.librerias.GestorPeliculas;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;




@Route("FormularioPeliculaView")
public class FormularioPeliculaView extends FormLayout {
	GestorPeliculas gestorPeliculas = new GestorPeliculas();
	ArrayList<Pelicula> listarPelicula = new ArrayList<Pelicula>();
	FormLayout formularioPelicula = new FormLayout();
	
	File ficheroPelicula = new File("peliculas.csv");
	
    public FormularioPeliculaView() throws IOException {
    	
        
           
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
                
                	try {
						listarPelicula = gestorPeliculas.crearPelicula(ficheroPelicula,listarPelicula,titulo.getValue(),descripcion.getValue(),duracion.getValue(),(Categoria)categoria.getValue(),(Formato)formato.getValue(),(Valoracion)valoracion.getValue(),anyoPublicacion.getValue());
					} catch (IOException e) {
						e.printStackTrace();
						
					}
                
              
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
	
	
	

