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
import org.springframework.transaction.annotation.Transactional;

import ca.logmein.pokergameapi.domain.Carte;
import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.service.dto.PlayerTotalCard;
@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	private PlayerRepository playerRepository;
	@Test
	@Transactional
	public void whenFindAll() {
		final Player player = new Player();
		GameDeck gameDeck = new GameDeck();
		gameDeck.setGameDeckName("Game1");
		gameDeck = entityManager.persistAndFlush(gameDeck);
		player.setGameDeck(gameDeck);
		player.setPseudo("Play1");
		entityManager.persistAndFlush(player);
		assertThat(playerRepository.findAll().size(), is(1));
	}
	
	@Test
	@Transactional
	public void whenfindByGameDeck() {
		final Player player = new Player();
		GameDeck gameDeck = new GameDeck();
		gameDeck.setGameDeckName("Game1");
		gameDeck = entityManager.persistAndFlush(gameDeck);
		player.setGameDeck(gameDeck);
		player.setPseudo("Play1");
		entityManager.persistAndFlush(player);
		assertThat(playerRepository.findByGameDeck(gameDeck).size(), is(1));
	}
	
	@Test
	@Transactional
	public void whenfindByPseudo() {
		final Player player = new Player();
		GameDeck gameDeck = new GameDeck();
		gameDeck.setGameDeckName("Game1");
		gameDeck = entityManager.persistAndFlush(gameDeck);
		player.setGameDeck(gameDeck);
		player.setPseudo("Play1");
		entityManager.persistAndFlush(player);
		assertThat(playerRepository.findByPseudo(player.getPseudo()).size(), is(1));
	}
	
	
	@Test
	@Transactional
	public void whenplayerTotalCards() {
		Player player = new Player();
		GameDeck gameDeck = new GameDeck();
		gameDeck.setGameDeckName("Game1");
		gameDeck = entityManager.persistAndFlush(gameDeck);
		player.setGameDeck(gameDeck);
		player.setPseudo("Play1");
		player = entityManager.persistAndFlush(player);
		final Carte card = new Carte();
		card.setPlayer(player);
		card.setFaceValue(12);
		
		final Carte card1 = new Carte();
		card1.setPlayer(player);
		card1.setFaceValue(13);
		
		entityManager.persistAndFlush(card);
		entityManager.persistAndFlush(card1);
		
		final List<PlayerTotalCard> playerTotalCards = playerRepository.playerTotalCards(gameDeck);
		assertThat(playerTotalCards.size(), is(1));
		assertThat(playerTotalCards.get(0).getTotalAddded(), is(25L));
	}
}
