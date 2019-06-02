package ca.logmein.pokergameapi.service.interfaces;

import java.util.List;
import java.util.Optional;

import ca.logmein.pokergameapi.service.dto.PlayerDTO;
import ca.logmein.pokergameapi.service.dto.PlayerTotalCard;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
public interface PlayerService {

    /**
     * Delete the "id" player.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Delete.
     *
     * @param speudo the speudo
     */
    void delete(String speudo);

    /**
     * Get all the players.
     *
     * @return the list of entities
     */
    List<PlayerDTO> findAll();
    
    /**
     * Find by speudo.
     *
     * @return the list
     */
    List<PlayerDTO> findByPseudo(String pseudo);


    /**
     * Get the "id" player.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlayerDTO> findOne(Long id);

    /**
     * Gets the all players by game deck.
     *
     * @param gameDeckId the game deck id
     * @return the all players by game deck
     */
    List<PlayerDTO> getAllPlayersByGameDeck(Long gameDeckId);
    
    /**
     * Save a player.
     *
     * @param playerDTO the entity to save
     * @return the persisted entity
     */
    PlayerDTO save(PlayerDTO playerDTO);
    
    List<PlayerTotalCard> TotalAddPerPlayer(Long gameId);
}
