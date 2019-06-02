package ca.logmein.pokergameapi.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.logmein.pokergameapi.service.dto.CardRest;
import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.dto.GameDeckDTO;
import ca.logmein.pokergameapi.service.interfaces.GameDeckService;
import ca.logmein.pokergameapi.web.error.BadRequestAlertException;
import ca.logmein.pokergameapi.web.helper.HeaderUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class GameDeckResource.
 */
@RestController
@RequestMapping("/api")
public class GameDeckResource {

    /** The Constant ENTITY_NAME. */
    private static final String ENTITY_NAME = "gameDeck";

    /**
     * Require non null.
     *
     * @param objects the objects
     */
    public static void requireNonNull(Object... objects) {
        for (final Object object : objects)
			Objects.requireNonNull(object);
    }

    /** The game deck service. */
    private final GameDeckService gameDeckService;

    /** The log. */
    private final Logger log = LoggerFactory.getLogger(GameDeckResource.class);

    /**
     * Instantiates a new game deck resource.
     *
     * @param gameDeckService the game deck service
     */
    public GameDeckResource(final GameDeckService gameDeckService) {
        this.gameDeckService = gameDeckService;
    }

    /**
     * Creates the game deck.
     *
     * @param gameDeckDTO the game deck DTO
     * @return the response entity
     * @throws URISyntaxException the URI syntax exception
     */
    @PostMapping("/game-decks")
    public ResponseEntity<GameDeckDTO> createGameDeck(@RequestBody final GameDeckDTO gameDeckDTO) throws URISyntaxException {
        log.debug("REST request to save GameDeck : {}", gameDeckDTO);
        if (Objects.nonNull(gameDeckDTO.getId()))
			throw new BadRequestAlertException("A new gameDeck cannot already have an ID", ENTITY_NAME, "idexists");
        final GameDeckDTO result = gameDeckService.save(gameDeckDTO);
        return ResponseEntity.created(new URI("/api/game-decks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    
    /**
     * Deal cards.
     *
     * @param gameId the game id
     * @param request the request
     * @return the response entity
     * @throws URISyntaxException the URI syntax exception
     */
    @GetMapping("/game-decks/{gameId}/dealcards")
    public ResponseEntity<String> dealCards(@PathVariable(required= true) final Long gameId, final HttpServletRequest request) throws URISyntaxException {
        log.debug("Deal one card to all player on game Deck ", gameId);
        requireNonNull(gameId,request, "Please provide the game Id");
		gameDeckService.dealCards(gameId, request.getSession());
		return ResponseEntity.ok("Each player received one card");
    }
  
       /**
        * Delete game deck.
        *
        * @param gameId the game id
        * @return the response entity
        */
    @DeleteMapping("/game-decks/{gameId}")
    public ResponseEntity<Void> deleteGameDeck(@PathVariable final Long gameId) {
        log.debug("REST request to delete GameDeck : {}", gameId);
        gameDeckService.delete(gameId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, gameId.toString())).build();
    }

    /**
     * Gets the game deck.
     *
     * @param gameId the game id
     * @return the game deck
     */
    @GetMapping("/game-decks/{gameId}")
    public ResponseEntity<GameDeckDTO> getGameDeck(@PathVariable final Long gameId) {
        log.debug("REST request to get GameDeck : {}", gameId);
        requireNonNull(gameId, "Please provide the game Id");
        final Optional<GameDeckDTO> gameDeckDTO = gameDeckService.findOne(gameId);
        return ResponseEntity.ok(gameDeckDTO.orElse(new GameDeckDTO()));
    }
    
    /**
     * Gets the undealt cards.
     *
     * @param gameId the game id
     * @param request the request
     * @return the undealt cards
     */
    @GetMapping("game-deck/{gameId}/undealt")
    public ResponseEntity<List<CardRest>> getUndealtCards(@PathVariable final Long gameId, HttpServletRequest request) {
    	requireNonNull(gameId,request, "Please provide the game Id");
    	log.debug("Get undealtCards {} , ", gameId);
       return ResponseEntity.ok(gameDeckService.cardRest(gameId, request.getSession()));
    }
    
    /**
     * Remaining cards.
     *
     * @param gameId the game id
     * @param request the request
     * @return the response entity
     */
    @GetMapping("game-deck/{gameId}/remaining")
    public ResponseEntity<List<CardsDTO>> remainingCards(@PathVariable final Long gameId, HttpServletRequest request) {
    	requireNonNull(gameId,request, "Please provide the game Id");
    	log.debug("Get undealtCards {} , ", gameId);
       return ResponseEntity.ok(gameDeckService.remainingCards(gameId, request.getSession()));
    }
    
    /**
     * Shuffle.
     *
     * @param gameId the game id
     * @param request the request
     * @return the response entity
     * @throws URISyntaxException the URI syntax exception
     */
    @GetMapping("/game-decks/{gameId}/shuffle")
    public ResponseEntity<String> shuffle(@PathVariable(required= true) final Long gameId, final HttpServletRequest request) throws URISyntaxException {
        log.debug("Shuffle cards {} , ", gameId);
        requireNonNull(gameId,request, "Please provide the game Id");
		gameDeckService.shuffle(gameId, request.getSession());
		return ResponseEntity.ok("Cards are shuffle ");
    }
    
    /**
     * Update game deck.
     *
     * @param gameDeckDTO the game deck DTO
     * @return the response entity
     * @throws URISyntaxException the URI syntax exception
     */
    @PutMapping("/game-decks")
    public ResponseEntity<GameDeckDTO> updateGameDeck(@RequestBody final GameDeckDTO gameDeckDTO) throws URISyntaxException {
        log.debug("REST request to update GameDeck : {}", gameDeckDTO);
        if (Objects.isNull(gameDeckDTO.getId()))
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        final GameDeckDTO result = gameDeckService.save(gameDeckDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gameDeckDTO.getId().toString()))
            .body(result);
    }
}
