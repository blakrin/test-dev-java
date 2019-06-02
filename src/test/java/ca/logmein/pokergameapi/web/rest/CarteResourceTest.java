package ca.logmein.pokergameapi.web.rest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.interfaces.CarteService;


@RunWith(SpringRunner.class)
@WebMvcTest(CarteResource.class)
public class CarteResourceTest {
		
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private CarteService carteService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCarteResource() {
		assertTrue(true);
	}

	@Test
	public void testCreateCarte() {
		final CardsDTO cardsDTO = new CardsDTO();
		cardsDTO.setColor("");
		 final CardsDTO cardsDTO1 = new CardsDTO();
			cardsDTO1.setId(12L);
		  when( carteService.save(cardsDTO)).thenReturn(cardsDTO1);
		  try {
			mvc.perform(post("/api/cartes")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(asJsonString(cardsDTO)))
				.andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
	}
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void testGetAllCartes() {
		final List<CardsDTO> cardLs = Arrays.asList(new CardsDTO());
		when(carteService.findAll()).thenReturn(cardLs);
		 try {
			mvc.perform(get("/api/cartes")
				      .contentType(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
				    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCarte() {
		final CardsDTO cardsDTO = new CardsDTO();
		final CardsDTO cardsDTO1 = new CardsDTO();
		cardsDTO.setId(12L);
		when(carteService.findOne(1L)).thenReturn(Optional.of(cardsDTO1));
		try {
			mvc.perform(get("/api/cartes/{id}",1)
				      .contentType(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateCarte() {
		final CardsDTO cardsDTO = new CardsDTO();
		cardsDTO.setColor("");
		 final CardsDTO cardsDTO1 = new CardsDTO();
			cardsDTO1.setId(12L);
		  when( carteService.save(cardsDTO)).thenReturn(cardsDTO1);
		  try {
			mvc.perform(post("/api/cartes")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(asJsonString(cardsDTO)))
				.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
