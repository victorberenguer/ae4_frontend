package org.ieschabas.views;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ieschabas.clases.Equipo;
import org.ieschabas.daos.EquipoDao;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;


@Route("formularioequipoview")
public class FormularioEquipoView extends HorizontalLayout implements HasUrlParameter<String> {

	private static String rol;
	FormLayout formularioActor = new FormLayout();
	private static List<Equipo> equipo = new ArrayList<>();
	//private static int contadorActor = 0;
	//File archivoActor = new File("actores.csv");

	public FormularioEquipoView() throws IOException {

		
		
		





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

			SimpleDateFormat fecha1 = new SimpleDateFormat("yyyy");

			try {
			Date fecha = fecha1.parse(anyoNacimiento.getValue());
			equipo.setAnyoNacimiento(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
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

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		FormularioEquipoView.rol = parameter;

		equipo = EquipoDao.listarEquipo(rol);
		if(rol == "Actor") {

			iniciarFormulario();
			
		}else if(rol == "Director") {
			
			iniciarFormulario();
		
			
		}
		
			
	}


}
