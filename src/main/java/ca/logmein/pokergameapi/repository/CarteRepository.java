package ca.logmein.pokergameapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.logmein.pokergameapi.domain.Carte;
import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.Player;


/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Repository
public interface CarteRepository extends JpaRepository<Carte, Long> {
	
	/**
	 * Find by deck.
	 *
	 * @param deck the deck
	 * @return the list
	 */
	List<Carte> findByDeck(Deck deck);
	
	@Query(value="select c from Carte c where c.deck in (:decks)")
	List<Carte> findByDeskInList(@Param("decks") List<Deck> decks);
	
	/**
	 * Find by player.
	 *
	 * @param player the player
	 * @return the list
	 */
	List<Carte> findByPlayer(Player player);
	@Modifying
	@Query(value="update Carte c SET c.player = :player WHERE c.id = :carteId")
	int updateCate(@Param("player") Player player,@Param("carteId") Long carteId);
	
}
	