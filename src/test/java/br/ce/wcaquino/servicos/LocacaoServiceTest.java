package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.servicos.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception =  ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario
		 LocacaoService service = new LocacaoService();
		 Usuario usuario = new Usuario("Usuario 1");
		 Filme filme = new Filme("Filme 1", 2, 5.0);
	
			 //acao
			 Locacao locacao = service.alugarFilme(usuario, filme);
			 
			 //verificacao
			 error.checkThat(locacao.getValor(), is(5.0)); // consegue vê mais de um erro por vez 
			 error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
			 error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.obterDataComDiferencaDias(0)), is(true));
			
	}
	
	

	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacao_fimeSemEstoque() throws Exception {
		
		 LocacaoService service = new LocacaoService();
		 Usuario usuario = new Usuario("Usuario 1");
		 Filme filme = new Filme("Filme 1", 0, 5.0);

		 service.alugarFilme(usuario, filme);
	}
	
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException  {
		
		 LocacaoService service = new LocacaoService();		 
		 Filme filme = new Filme("Filme 1", 2, 5.0);
		 try {
			 service.alugarFilme(null, filme);
			 Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
		 System.out.println("Forma robusta"); // melhor forma, pois é a mais completa.
	}
	
	@Test
	public void testeLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException  {
		
		 LocacaoService service = new LocacaoService();		 
		 Usuario usuario = new Usuario("Usuario 1");
		
		 exception.expect(LocadoraException.class);
		 exception.expectMessage("Filme vazio");
		 
		 service.alugarFilme(usuario, null);
		 
		 System.out.println("Forma nova");
		 
	}
	
	
	
}
