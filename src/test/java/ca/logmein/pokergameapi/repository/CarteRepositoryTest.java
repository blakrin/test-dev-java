package ca.logmein.pokergameapi.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ca.logmein.pokergameapi.domain.Carte;
import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.Player;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarteRepositoryTest {
	@Autowired
	private CarteRepository carteRepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void whenfindByDeck() {
		 Deck deck = new Deck();
		deck.setDeckName("Test1");
		deck = entityManager.persistAndFlush(deck);
		final Carte card = new Carte();
		card.setFaceValue(12);
		card.setDeck(deck);
		
		final Carte card1 = new Carte();
		card1.setFaceValue(13);
		card1.setDeck(deck);
		
		entityManager.persistAndFlush(card);
		entityManager.persistAndFlush(card1);
		final List<Carte> cardList = carteRepository.findByDeck(deck);
		assertThat(cardList.size(), is(2));
	}
	
	@Test
	public void whenfindByPlayer() {
		 Deck deck = new Deck();
		deck.setDeckName("Test1");
		deck = entityManager.persistAndFlush(deck);
		
		Player player = new Player();
		player.setPseudo("Play1");
		player = entityManager.persistAndFlush(player);
		 Carte card = new Carte();
		card.setFaceValue(12);
		card.setDeck(deck);
		
		 Carte card1 = new Carte();
		card1.setFaceValue(13);
		card1.setDeck(deck);
		
		card = entityManager.persistAndFlush(card);
		card1 = entityManager.persistAndFlush(card1);
		carteRepository.updateCate(player, card.getId());
		carteRepository.updateCate(player, card1.getId());
		final List<Carte> cardList = carteRepository.findByPlayer(player);
		assertThat(cardList.size(), is(2));
	}
}
