package ca.logmein.pokergameapi.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;

import ca.logmein.pokergameapi.domain.Carte;
import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.service.dto.CardsDTO;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
public interface CarteService {

    /**
     * Creates the cartes for A deck.
     *
     * @param deckId the deck id
     * @return the sets the
     */
    public Set<Carte> createCartesForADeck(final Long deckId);

    /**
     * Delete the "id" carte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Fand carte by player id.
     *
     * @param playerId the player id
     * @return the list
     */
    List<CardsDTO> fandCarteByPlayerId(Long playerId);
    
    /**
     * Get all the cartes.
     *
     * @return the list of entities
     */
    List<CardsDTO> findAll();


    /**
     * Find carte by deck id.
     *
     * @param deckId the deck id
     * @return the list
     */
    List<CardsDTO> findCarteByDeckId(Long deckId);

    
    /**
     * Find carte by deck list.
     *
     * @param decks the decks
     * @return the list
     */
    @Cacheable("player")
    List<CardsDTO> findCarteByDeckList(List<Deck> decks);
    /**
     * Get the "id" carte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CardsDTO> findOne(Long id);
    
    /**
     * Save a carte.
     *
     * @param carteDTO the entity to save
     * @return the persisted entity
     */
    CardsDTO save(CardsDTO carteDTO);
    
    /**
     * Update carte.
     *
     * @param player the player
     * @param cardId the card id
     * @return the int
     */
    int updateCarte(Player player, Long cardId);
}
