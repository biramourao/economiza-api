package com.economiza.economizaapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.economiza.economizaapi.model.CartaoDeCredito;
import com.economiza.economizaapi.model.CategoriaGasto;
import com.economiza.economizaapi.model.Gasto;
import com.economiza.economizaapi.model.Usuario;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class GastoRepositoryTest {

		@Autowired
		private CartaoDeCreditoRepository cartaoDeCreditoRepository;
		@Autowired
		private CategoriaGastoRepository categoriaGastoRepository;
		@Autowired 
		private UsuarioRepository usuarioRepository;
		@Autowired
		private GastoRepository gastoRepository;

		@Test
		public void asaveTest() {
			
			Usuario usuario = new Usuario(null,"ubiratan.mourao@gmail.com","Ubiratan Leitão Mourão","123456");
			Usuario savedUsuario = usuarioRepository.save(usuario);
			CartaoDeCredito cartaoDeCredito= new CartaoDeCredito(null, "NuBank Bira", 6, 1400, 300, savedUsuario);
			
			Usuario usuario2 = new Usuario(null,"raianyramos1k@gmail.com","Raiany Ramos","123");
			Usuario savedUsuario2 = usuarioRepository.save(usuario2);
			
			CartaoDeCredito cartaoDeCredito2 = new CartaoDeCredito(null, "Nu Raiany", 6, 1400.50, 300, savedUsuario2);
			cartaoDeCreditoRepository.save(cartaoDeCredito2);
			CartaoDeCredito savedCartaoDeCredito = cartaoDeCreditoRepository.save(cartaoDeCredito);
			CategoriaGasto savedCategoriaGasto = categoriaGastoRepository.save(new CategoriaGasto(null, "Alimentacao"));
			Gasto gasto = new Gasto(null, "Compras", 600.00, new Date(), 1, null, savedUsuario, savedCategoriaGasto, savedCartaoDeCredito);
			Gasto createdGasto = gastoRepository.save(gasto);
			assertThat(createdGasto.getNome()).isEqualTo("Compras");
			
		}
		
		@Test
		public void bupdateTest() {
			Optional<Gasto> gasto = gastoRepository.findById(1L);
			
			gasto.get().setNome("Compras alterado");
			Gasto updatedGasto = gastoRepository.save(gasto.get());
			assertThat(updatedGasto.getNome()).isEqualTo("Compras alterado");	
		}
		@Test
		public void cgetByIdTest() {
			Optional<Gasto> gasto = gastoRepository.findById(1L);
			
			assertThat(gasto.get().getNome()).isEqualTo("Compras alterado");
		}
		
		@Test
		public void dfindAllTest() {
			List<Gasto> gasto = gastoRepository.findAll();
			
			assertThat(gasto.size()).isEqualTo(1);
		}
		@Ignore
		@Test
		public void edeleteById() {
			gastoRepository.deleteById(1L);
			List<Gasto> gasto = gastoRepository.findAll();
			
			assertThat(gasto.size()).isEqualTo(0);
		}
		
		@Test
		public void fefetuarPagamentoTest() {
			@SuppressWarnings("deprecation")
			int qtAlteracoes = gastoRepository.efetuarPagamento(1L, new Date("oct 15, 2019"));
			assertThat(qtAlteracoes).isEqualTo(1);
			
			Optional<Gasto> gastoPago = gastoRepository.findById(1L);
			assertThat(gastoPago.get().getDtPagamento()).isNotNull();
		}
		
		@Test
		public void gFindByUsuarioTest() {
			List<Gasto> gasto = gastoRepository.findByUsuarioCod(1L);
			System.out.println(gasto);
			assertThat(gasto.size()).isEqualTo(1);
			
		}
		
		@Test
		public void hfindByCartaoDeCreditoTest() {
			List<Gasto> gasto = gastoRepository.findByCartaoDeCreditoCod(2L);
			System.out.println(gasto);
			assertThat(gasto.size()).isEqualTo(1);
			
		}
		
		@Test
		public void ifindByCategoriaGastoTest() {
			List<Gasto> gasto = gastoRepository.findByCategoriaGastoCod(1L);
			System.out.println(gasto);
			assertThat(gasto.size()).isEqualTo(1);
			
		}
}
