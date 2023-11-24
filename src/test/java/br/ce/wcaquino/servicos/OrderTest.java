package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest {
	
	private static int contador = 0;
	
	@Test
	public void inicia() {
		contador = 1;
	}
	
	@Test
	public void verificar() {
		Assert.assertEquals(contador, 1);
	}
	
	@Test
	public void testeGeral() {
		inicia();
		verificar();
	}

}
