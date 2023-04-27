package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ieschabas.clases.Actor;
import org.ieschabas.clases.Director;
import org.ieschabas.librerias.GestorActor;
import org.ieschabas.librerias.GestorDirector;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("FormularioActorView")
public class FormularioActorView extends HorizontalLayout  {

	GestorActor gestorActor = new GestorActor();
	FormLayout formularioActor = new FormLayout();
	private static ArrayList<Actor> listarActores = new ArrayList<Actor>();
	private static int contadorActor = 0;
	File archivoActor = new File("actores.csv");

	public FormularioActorView() throws IOException {
		
		TextField nombre = new TextField("Nombre");
		TextField apellidos = new TextField("Apellidos");
		TextField pais = new TextField("Pais");
		TextField anyoNacimiento = new TextField("Año de nacimiento");

		/**
		 * El botón de guardar de el Actor con un clicklistener para que cuando haga
		 * click en guardar escriba los datos en el fichero mediante el metodo de
		 * crearActor
		 */
		Button guardarActor = new Button("CrearActor");
		guardarActor.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

		guardarActor.addClickListener(clickEvent -> {
			try {

				contadorActor += 1;

				listarActores = gestorActor.crearActor(archivoActor, listarActores, nombre.getValue(),
						apellidos.getValue(), pais.getValue(), anyoNacimiento.getValue());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		/**
		 * Introducimos los campos de texto y los botones al formulario
		 */

		formularioActor.add(nombre, apellidos, pais, anyoNacimiento, guardarActor);
		formularioActor.setResponsiveSteps(
				// Use one column by default
				new ResponsiveStep("0", 1),
				// Use two columns, if layout's width exceeds 500px
				new ResponsiveStep("500px", 2));
		// Stretch the username field over 2 columns
		formularioActor.setColspan(nombre, 2);

		add(formularioActor);

	}
	

}
