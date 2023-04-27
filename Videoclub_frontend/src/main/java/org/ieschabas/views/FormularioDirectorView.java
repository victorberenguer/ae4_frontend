package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ieschabas.clases.Director;
import org.ieschabas.librerias.GestorDirector;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("FormularioDirectorView")

public class FormularioDirectorView extends HorizontalLayout {

	GestorDirector gestorDirector = new GestorDirector();
	private static ArrayList<Director> listarDirectores = new ArrayList<Director>();
	private static int contadorDirector = 0;
	File ficheroDirector = new File("directores.csv");
	FormLayout formularioDirector = new FormLayout();

	public FormularioDirectorView() throws IOException {

		TextField nombre = new TextField("Nombre");
		TextField apellidos = new TextField("Apellidos");
		TextField pais = new TextField("Pais");
		TextField anyoNacimiento = new TextField("Año de nacimiento");

		/**
		 * El botón de guardar de el Director con un clicklistener para que cuando haga
		 * click en guardar escriba los datos en el fichero mediante el metodo de
		 * crearDirector
		 */

		Button guardarDirector = new Button("CrearDirector");
		guardarDirector.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

		guardarDirector.addClickListener(clickEvent -> {
			try {

				contadorDirector += 1;

				listarDirectores = gestorDirector.crearDirector(ficheroDirector, listarDirectores, nombre.getValue(),
						apellidos.getValue(), pais.getValue(), anyoNacimiento.getValue());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		/**
		 * Introducimos los campos de texto y los botones al formulario
		 */

		formularioDirector.add(nombre, apellidos, pais, anyoNacimiento, guardarDirector);
		formularioDirector.setResponsiveSteps(
				// Use one column by default
				new ResponsiveStep("0", 1),
				// Use two columns, if layout's width exceeds 500px
				new ResponsiveStep("500px", 2));
		// Stretch the username field over 2 columns
		formularioDirector.setColspan(nombre, 2);

		add(formularioDirector);

	}
}