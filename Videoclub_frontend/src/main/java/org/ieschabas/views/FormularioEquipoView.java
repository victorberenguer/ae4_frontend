package org.ieschabas.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.ieschabas.clases.Actor;
import org.ieschabas.clases.Director;
import org.ieschabas.clases.Equipo;
import org.ieschabas.daos.EquipoDao;
import org.ieschabas.librerias.GestorActor;
import org.ieschabas.librerias.GestorDirector;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("FormularioEquipoView")
public class FormularioEquipoView extends HorizontalLayout  {

	GestorActor gestorActor = new GestorActor();
	FormLayout formularioActor = new FormLayout();
	private static ArrayList<Equipo> equipo = new ArrayList<Equipo>();
	private static int contadorActor = 0;
	File archivoActor = new File("actores.csv");

	public FormularioEquipoView() throws IOException {
		
		
		iniciarFormulario();
		
		
		
	

	}

	private void iniciarFormulario() {
		TextField nombre = new TextField("Nombre");
		TextField apellidos = new TextField("Apellidos");
		TextField pais = new TextField("Pais");
		TextField anyoNacimiento = new TextField("Año de nacimiento");

		/**
		 * El botón de guardar de el Actor con un clicklistener para que cuando haga
		 * click en guardar escriba los datos en el fichero mediante el metodo de
		 * crearActor
		 */
		Button guardarActor = new Button("Crear");
		guardarActor.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

		guardarActor.addClickListener(clickEvent -> {
			Equipo equipo = new Equipo();
			equipo.setNombre(nombre.getValue());
			equipo.setApellidos(apellidos.getValue());
			equipo.setPais(pais.getValue());
			//equipo.setAnyoNacimiento(anyoNacimiento);
			
			EquipoDao.guardarEquipo(equipo);
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
