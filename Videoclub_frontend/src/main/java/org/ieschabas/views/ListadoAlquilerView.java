package org.ieschabas.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ieschabas.clases.Alquiler;
import org.ieschabas.daos.AlquilerDao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("listadoalquilerview")
public class ListadoAlquilerView extends HorizontalLayout{

	Grid<Alquiler> gridAlquiler = new Grid<>(Alquiler.class, false);
	private static List<Alquiler> alquileres = new ArrayList<>();
	public  ListadoAlquilerView() throws IOException  {

	iniciarGrid();


	}


	private void iniciarGrid() {
		alquileres = AlquilerDao.obtenerAlquiler();
        // tag::snippet[]

	 gridAlquiler.addColumn(Alquiler::getId).setHeader("Id");
	 gridAlquiler.addColumn(Alquiler::getFechaAlquiler).setHeader("Fecha Alquiler");
	 gridAlquiler.addColumn(Alquiler::getFechaRetorno).setHeader("Fecha Retorno");


      add(gridAlquiler);




	}


}
