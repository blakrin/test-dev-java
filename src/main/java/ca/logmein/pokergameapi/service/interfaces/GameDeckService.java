package ca.logmein.pokergameapi.service.interfaces;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import ca.logmein.pokergameapi.service.dto.CardRest;
import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.dto.GameDeckDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface GameDeckService.
 *
 * @author Blaise Siani
 * @Date May 31, 2019
 */
public interface GameDeckService {
	
    /**
     * Card rest.
     *
     * @param gameDeckId the game deck id
     * @param session the session
     * @return the list
     */
    List<CardRest> cardRest(Long gameDeckId, HttpSession session);
    
    /**
     * Deal cards.
     *
     * @param gamedeckId the gamedeck id
     * @param session the session
     */
    void dealCards(Long gamedeckId, HttpSession session);
    /**
     * Delete the "id" gameDeck.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    /**
     * Get all the gameDecks.
     *
     * @return the list of entities
     */
    List<GameDeckDTO> findAll();
  
    /**
     * Get the "id" gameDeck.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GameDeckDTO> findOne(Long id);

    /**
     * Remaining cards.
     *
     * @param gameDeckId the game deck id
     * @param session the session
     * @return the list
     */
    List<CardsDTO> remainingCards(Long gameDeckId, HttpSession session);
    
    /**
     * Save a gameDeck.
     *
     * @param gameDeckDTO the entity to save
     * @return the persisted entity
     */
    GameDeckDTO save(GameDeckDTO gameDeckDTO);
    
    void shuffle(Long gameDeckId, HttpSession session);
}
