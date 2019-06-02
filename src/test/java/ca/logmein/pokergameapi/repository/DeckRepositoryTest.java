package ca.logmein.pokergameapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.GameDeck;
@RunWith(SpringRunner.class)
@DataJpaTest
public class DeckRepositoryTest {
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	/**
	 * When find by alll.
	 */
	@Test
	public void when_findAlll() {
		final Deck deck = new Deck();
		deck.setDeckName("Test1");
		entityManager.persistAndFlush(deck);
		final List<Deck> listDeck = deckRepository.findAll();
		assertThat(listDeck.size(), is(1));
		assertThat(listDeck.get(0).getDeckName().equals("Test1"));
	}
	
	@Test
	public void when_findByDeckName() {
		final Deck deck = new Deck();
		deck.setDeckName("Test1");
		entityManager.persistAndFlush(deck);
		final List<Deck> listDeck = deckRepository.findByDeckName(deck.getDeckName());
		assertThat(listDeck.size(), is(1));
		assertThat(listDeck.get(0).getDeckName().equals("Test1"));
	}
	
	
	@Test
	public void when_findByGameDeck() {
		final Deck deck = new Deck();
		deck.setDeckName("Test1");
		GameDeck game = new GameDeck();
		game.setGameDeckName("Test1");
		game = entityManager.persistAndFlush(game);
		deck.setGameDeck(game);
		entityManager.persistAndFlush(deck);
		final List<Deck> listDeck = deckRepository.findByGameDeck(game);
		assertThat(listDeck.size(), is(1));
		assertThat(listDeck.get(0).getDeckName().equals("Test1"));
	}
}
