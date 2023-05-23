package org.ieschabas.daos;

import static org.junit.Assert.*;

import org.ieschabas.clases.Pelicula;
import org.junit.Assert;
import org.junit.Test;

public class PeliculaDaoTest {

	@Test
	public void test() {
		fail("Not yet implemented");
		
	}
	@Test
	void buscarTest() {
		Pelicula pelicula = new Pelicula();
		PeliculaDao.buscarPelicula(pelicula, 2);
		Assert.assertNotNull(pelicula);
	}
	
	
}
