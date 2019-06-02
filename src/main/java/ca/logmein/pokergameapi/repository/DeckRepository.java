package ca.logmein.pokergameapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.GameDeck;


/**
 * The Interface DeckRepository.
 *
 * @author Blaise Siani
 * @Date May 31, 2019
 */
@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
	
  /**
   * Find by deck name.
   *
   * @param deckName the deck name
   * @return the list
   */
  List<Deck> findByDeckName(String deckName);
  
  /**
   * Find by game deck.
   *
   * @param gameDeck the game deck
   * @return the list
   */
  List<Deck> findByGameDeck(GameDeck gameDeck);
}
