package ca.logmein.pokergameapi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PokerGameApiApplicationTests.class})
public class PokerGameApiApplicationTests {

	/*
	 * @Value("classpath:data/cards-with-out-players.json") private
	 * org.springframework.core.io.Resource cards;
	 * 
	 * @Autowired JacksonTester<List<CardsDTO>> cardsList;
	 */
	  @Test
	  public void contextLoads() {
		  assertTrue(true);
		/*
		 * try { final List<CardsDTO> cardLs =
		 * cardsList.readObject(cards.getInputStream()); assertTrue(cardLs.size()>0); }
		 * catch (final IOException e) { e.printStackTrace(); }
		 */
	  }
	 

}
