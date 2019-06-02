package ca.logmein.pokergameapi.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;

import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.service.dto.DeckDTO;


/**
 * The Interface DeckService.
 *
 * @author Blaise Siani
 * @Date May 31, 2019
 */
public interface DeckService {

    /**
     * Delete the "id" deck.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the decks.
     *
     * @return the list of entities
     */
    List<DeckDTO> findAll();


    /**
     * Find by game deck.
	
     *
     * @param gameDeck the game deck
     * @return the list
     */
    @Cacheable("deck")
    List<DeckDTO> findByGameDeck(GameDeck gameDeck);
    
    /**
     * Find deck by name.
     *
     * @param name the name
     * @return the list
     */
    List<DeckDTO> findDeckByName(String name);

    /**
     * Get the "id" deck.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DeckDTO> findOne(Long id);
    
    /**
     * Save a deck.
     *
     * @param deckDTO the entity to save
     * @return the persisted entity
     */
    DeckDTO save(DeckDTO deckDTO);
}
