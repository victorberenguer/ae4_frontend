package org.ieschabas.views;

import java.io.IOException;

import org.ieschabas.clases.Alquiler;
import org.ieschabas.daos.PeliculaDao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("ListadoAlquilerView")
public class ListadoAlquilerView extends HorizontalLayout{

	Grid<Alquiler> gridAlquiler = new Grid<>(Alquiler.class, false);
	
	public  ListadoAlquilerView() throws IOException  {
	
	iniciarGrid();
	
	
	}


	private void iniciarGrid() {
		PeliculaDao.obtenerPelicula();
        // tag::snippet[]
	   
	 gridAlquiler.addColumn(Alquiler::getId).setHeader("Id");
	 gridAlquiler.addColumn(Alquiler::getFechaAlquiler).setHeader("Fecha Alquiler");
	 gridAlquiler.addColumn(Alquiler::getFechaRetorno).setHeader("Fecha Retorno");

        
      add(gridAlquiler);
	 
	 
	 
		
	}


}
