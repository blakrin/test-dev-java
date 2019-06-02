package ca.logmein.pokergameapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.service.dto.PlayerTotalCard;



/**
 * The Interface PlayerRepository.
 *
 * @author Blaise Siani
 * @Date May 31, 2019
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	/**
	 * Delete by pseudo.
	 *
	 * @param pseudo the pseudo
	 */
	void deleteByPseudo(String pseudo);
	
	/**
	 * Find by game deck.
	 *
	 * @param gameDeck the game deck
	 * @return the list
	 */
	List<Player> findByGameDeck(GameDeck gameDeck);
	
	
	/**
	 * Find by pseudo.
	 *
	 * @param pseudo the pseudo
	 * @return the list
	 */
	List<Player> findByPseudo(String pseudo);
	
	@Query("select new ca.logmein.pokergameapi.service.dto.PlayerTotalCard(p, sum(c.faceValue)) from Player p, Carte c where p.gameDeck = :gameDeck and c.player = p"
			+ " group by p.id order by sum(c.faceValue) desc")
	List<PlayerTotalCard> playerTotalCards(@Param("gameDeck")GameDeck gameDeck);
	
}
