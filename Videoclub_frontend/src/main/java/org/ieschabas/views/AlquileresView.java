package org.ieschabas.views;

import org.ieschabas.daos.AlquilerDao;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("alquileresview")
public class AlquileresView extends HorizontalLayout{
	private Span status;
	public AlquileresView() {
		
		String ruta = "$HOME/git/ae4_frontend/Videoclub_frontend/IMÁGENES-20230524/1.jpg";
		
		
		Image imagen = new Image(ruta,"El padrino");
		
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

		dialog.setConfirmText("SI");
		dialog.addConfirmListener(event -> setStatus("Saved"));
		dialog.addAttachListener(event -> {
			
			AlquilerDao.guardarAlquiler(null);
		});

		dialog.open();

		layout.add(status);
		add(layout);
     	
		
		
		
		
		add(imagen);
		
	}
	private Object setStatus(String string) {
		
		string = "Se ha alquilado la pelicula correctamente";
		System.out.print(string);
		
		
		return string;
	}
	
	
	
	
	
}
