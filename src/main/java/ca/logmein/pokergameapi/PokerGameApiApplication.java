package ca.logmein.pokergameapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokerGameApiApplication {
	public static void main(final String[] args) {
		SpringApplication.run(PokerGameApiApplication.class, args);
	}
	
	/*
	 * @Autowired private DeckService deckService ;
	 * 
	 * @Autowired private GameDeckService gameDeckService;
	 * 
	 * @Autowired private PlayerService playerService;
	 *  implements CommandLineRunner
	 */
	 

	
	/*
	 * @Override public void run(String... args) throws Exception { final
	 * GameDeckDTO gameDeck = new GameDeckDTO(); gameDeck.setGameDeckName("Test1");
	 * 
	 * gameDeckService.save(gameDeck); final DeckDTO deck = new DeckDTO();
	 * 
	 * deck.setDeckName("decktes"); deck.setGameDeckId(1L); deckService.save(deck);
	 * 
	 * for (int i = 0; i < 4; i++) { final PlayerDTO player = new PlayerDTO();
	 * player.setGameDeckId(1L); player.setFistName("Test");
	 * player.setLastName("Test"); player.setPseudo("Blaise"+i);
	 * playerService.save(player); }
	 * 
	 * 
	 * }
	 */
	 

}
