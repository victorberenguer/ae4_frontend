package org.ieschabas.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ieschabas.clases.Equipo;
import org.ieschabas.daos.EquipoDao;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value="listadoequipoview", layout = MainView.class)
public class ListadoEquipoView extends VerticalLayout implements HasUrlParameter<String> {

	private Span status;
	private static Grid<Equipo> grid = new Grid<>(Equipo.class, false);
	private static List<Equipo> personal = new ArrayList<>();

	private static String rol;
	private static VerticalLayout layout = new VerticalLayout();
	private static Button botonCrear = new Button("Crear");
	
	public ListadoEquipoView() throws IOException {


		
		
		

	}

	private void crearBoton() {

		botonCrear.addClickListener(event -> botonCrear.getUI().ifPresent(ui -> ui.navigate("formularioequipoview"))

		);
		
	}

	private void iniciarGrid() {

		

		grid.addColumn(Equipo::getId).setHeader("Id Actores");
		grid.addColumn(Equipo::getNombre).setHeader("Nombre");
		grid.addColumn(Equipo::getApellidos).setHeader("Apellidos");
		grid.addColumn(Equipo::getPais).setHeader("Pais");
		grid.addColumn(Equipo::getAnyoNacimiento).setHeader("Año de Nacimiento");
		grid.addColumn(Equipo::getRol).setHeader("Rol");
		grid.addColumn(new ComponentRenderer<>(Button::new, (button, equipo) -> {
			button.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
			button.addClickListener(e -> {
		

				
					ConfirmDialog dialog = new ConfirmDialog();
					dialog.setHeader("Borrar ");
					dialog.setText("Seguro que quieres borrar el actor?");

					dialog.setRejectable(true);
					dialog.setRejectText("NO");
					dialog.addRejectListener(event -> setStatus("Discarded"));

					dialog.setConfirmText("SI");
				
					dialog.open();

				

					// Center the button within the example
					getStyle().set("position", "fixed").set("top", "0").set("right", "0").set("bottom", "0")
							.set("left", "0").set("display", "flex").set("align-items", "center")
							.set("justify-content", "center");

			});
			button.setIcon(new Icon(VaadinIcon.TRASH));
		}

		)).setHeader("Eliminar");

		grid.addColumn(new ComponentRenderer<>(Button::new, (button, equipo) -> {
			button.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
			button.addClickListener(e -> {			

					ConfirmDialog dialog = new ConfirmDialog();
					dialog.setHeader("Modificar Actor ");
					dialog.setText("Seguro que quieres modificar el actor?");

					dialog.setRejectable(true);
					dialog.setRejectText("NO");
					dialog.addRejectListener(event -> setStatus("Discarded"));

					dialog.setConfirmText("SI");
					dialog.addConfirmListener(event -> dialog.getUI().ifPresent(ui ->

					ui.navigate("formularioequipoview"))

					);

					dialog.open();

					

					// Center the button within the example
					getStyle().set("position", "fixed").set("top", "0").set("right", "0").set("bottom", "0")
							.set("left", "0").set("display", "flex").set("align-items", "center")
							.set("justify-content", "center");

			
			});
			button.setIcon(new Icon(VaadinIcon.BOOK));
		}

		)).setHeader("Acciones");

		

		getElement().getStyle().set("height", "100%");
		grid.setHeight("100%");
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		grid.setItems(personal);
		
	}

	private void setStatus(String value) {
		status.setText("Status: " + value);
		status.setVisible(true);
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		ListadoEquipoView.rol = parameter;

		personal = EquipoDao.listarEquipo(rol);
		
		iniciarGrid();
		crearBoton();
		layout.add(grid,botonCrear);
		add(layout);
		
		
		

	}

}
