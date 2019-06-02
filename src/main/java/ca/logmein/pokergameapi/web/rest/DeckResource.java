package ca.logmein.pokergameapi.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.logmein.pokergameapi.service.dto.DeckDTO;
import ca.logmein.pokergameapi.service.interfaces.DeckService;
import ca.logmein.pokergameapi.service.interfaces.GameDeckService;
import ca.logmein.pokergameapi.web.error.BadRequestAlertException;


/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@RestController
@RequestMapping("/api")
public class DeckResource {

    private static final String ENTITY_NAME = "deck";

    private final DeckService deckService;
    
    private final GameDeckService gameDeckService;

    private final Logger log = LoggerFactory.getLogger(DeckResource.class);

    public DeckResource(final DeckService deckService, final GameDeckService gameDeckService) {
        this.deckService = deckService;
        this.gameDeckService = gameDeckService;
    }

    /**
     * POST  /decks : Create a new deck.
     *
     * @param deckDTO the deckDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deckDTO, or with status 400 (Bad Request) if the deck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/decks")
    public ResponseEntity<DeckDTO> createDeck(@RequestBody final DeckDTO deckDTO) throws URISyntaxException {
        log.debug("REST request to save Deck : {}", deckDTO);
		if (!deckService.findDeckByName(deckDTO.getDeckName()).isEmpty())
			throw new BadRequestAlertException("A deck exist with this name", ENTITY_NAME, " Deck exist ");
		else if(!gameDeckService.findOne(deckDTO.getGameDeckId()).isPresent())
			throw new BadRequestAlertException("The is no Game Deck with this id", deckDTO.getGameDeckId().toString(), "id not exist");
		final DeckDTO result = deckService.save(deckDTO);
        return ResponseEntity.created(new URI("/api/decks/" + result.getId()))
            .body(result);
    }

 
    /**
     * GET  /decks : get all the decks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of decks in body
     */
    @GetMapping("/decks")
    public ResponseEntity<List<DeckDTO>> getAllDecks() {
        log.debug("REST request to get all Decks");
        return ResponseEntity.ok().body(deckService.findAll());
    }

    /**
     * GET  /decks/:id : get the "id" deck.
     *
     * @param id the id of the deckDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deckDTO, or with status 404 (Not Found)
     */
    @GetMapping("/decks/{id}")
    public ResponseEntity<DeckDTO> getDeck(@PathVariable final Long id) {
        log.debug("REST request to get Deck : {}", id);
        final Optional<DeckDTO> deckDTO = deckService.findOne(id);
        return ResponseEntity.ok(deckDTO.orElse(new DeckDTO()));
    }

    /**
     * PUT  /decks : Updates an existing deck.
     *
     * @param deckDTO the deckDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deckDTO,
     * or with status 400 (Bad Request) if the deckDTO is not valid,
     * or with status 500 (Internal Server Error) if the deckDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/decks")
    public ResponseEntity<DeckDTO> updateDeck(@RequestBody final DeckDTO deckDTO) throws URISyntaxException {
        log.debug("REST request to update Deck : {}", deckDTO);
        if (Objects.isNull(deckDTO.getId()))
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id null");
        final DeckDTO result = deckService.save(deckDTO);
        return ResponseEntity.ok(result);
    }
}
