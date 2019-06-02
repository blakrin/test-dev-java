package ca.logmein.pokergameapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ca.logmein.pokergameapi.domain.GameDeck;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameDeckRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private GameDeckRepository gameDeckRepository;
	
	@Test
	public void whenFindAll() {
		GameDeck gameDeck = new GameDeck();
		gameDeck.setGameDeckName("Test1");
		gameDeck = entityManager.persistAndFlush(gameDeck);
		assertThat(gameDeckRepository.findAll().size(), is(1));
	}
	
	@Test
	public void whenFindById() {
		GameDeck gameDeck = new GameDeck();
		gameDeck.setGameDeckName("Test1");
		gameDeck = entityManager.persistAndFlush(gameDeck);
		assertThat(gameDeckRepository.findById(gameDeck.getId()).isPresent());
	}
	
	
}
