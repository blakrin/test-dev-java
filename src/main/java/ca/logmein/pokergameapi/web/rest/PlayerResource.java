package ca.logmein.pokergameapi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.dto.PlayerDTO;
import ca.logmein.pokergameapi.service.dto.PlayerTotalCard;
import ca.logmein.pokergameapi.service.interfaces.CarteService;
import ca.logmein.pokergameapi.service.interfaces.GameDeckService;
import ca.logmein.pokergameapi.service.interfaces.PlayerService;
import ca.logmein.pokergameapi.web.error.BadRequestAlertException;
import ca.logmein.pokergameapi.web.helper.HeaderUtil;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@RestController
@RequestMapping("/api")
public class PlayerResource {

	/** The Constant ENTITY_NAME. */
	private static final String ENTITY_NAME = "player";

	private final CarteService carteService;
	
	/** The game deck service. */
	private final GameDeckService gameDeckService;

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(PlayerResource.class);
	
	/** The player service. */
	private final PlayerService playerService;

	/**
	 * Instantiates a new player resource.
	 *
	 * @param playerService the player service
	 * @param gameDeckService the game deck service
	 */
	public PlayerResource(final PlayerService playerService, final GameDeckService gameDeckService,final CarteService carteService) {
		this.playerService = playerService;
		this.gameDeckService = gameDeckService;
		this.carteService = carteService;
	}

	/**
	 * POST /players : Create a new player.
	 *
	 * @param playerDTO the playerDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         playerDTO, or with status 400 (Bad Request) if the player has already
	 *         an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/players")
	public ResponseEntity<PlayerDTO> createPlayer(@RequestBody final PlayerDTO playerDTO) throws URISyntaxException {
		log.debug("REST request to save Player : {}", playerDTO);
		if (!playerService.findByPseudo(playerDTO.getPseudo()).isEmpty())
			throw new BadRequestAlertException("A player exist on this game deck with this pseudo ", ENTITY_NAME,
					"player exists");
		else if (!gameDeckService.findOne(playerDTO.getGameDeckId()).isPresent())
			throw new BadRequestAlertException("The is no Game Deck with", playerDTO.getGameDeckId().toString(),
					"id not exist");

		final PlayerDTO result = playerService.save(playerDTO);
		return ResponseEntity.created(new URI("/api/players/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);

	}

	/**
	 * DELETE /players/:id : delete the "id" player.
	 *
	 * @param id the id of the playerDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/players/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable final Long id) {
		log.debug("REST request to delete Player : {}", id);
		playerService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
	/**
	 * Delete player.
	 *
	 * @param pseudo the pseudo
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/players/{pseudo}")
	public ResponseEntity<Void> deletePlayer(@PathVariable final String pseudo) {
		log.debug("REST request to delete Player : {}", pseudo);
		playerService.delete(pseudo);
		return ResponseEntity.ok().build();
	}
	/**
	 * GET /players : get all the players.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of players in
	 *         body
	 */
	@GetMapping("/players")
	public List<PlayerDTO> getAllPlayers() {
		log.debug("REST request to get all Players");
		return playerService.findAll();
	}

	
	@GetMapping("player/{playerId}/cartes")
	public List<CardsDTO> getCartesPlayer(@PathVariable final Long playerId) {
		log.debug("REST request to get all Players");
		return carteService.fandCarteByPlayerId(playerId);
	}
	
	@GetMapping("gamedeck/{gameDeckId}/players")
	public List<PlayerDTO> getGameDeckPlayers(@PathVariable final Long gameDeckId) {
		log.debug("REST request to get all Players");
		if (!gameDeckService.findOne(gameDeckId).isPresent())
			throw new BadRequestAlertException("The is no Game Deck with", gameDeckId.toString(),
					"id not exist");
		return playerService.getAllPlayersByGameDeck(gameDeckId);
	}
	/**
	 * GET /players/:id : get the "id" player.
	 *
	 * @param id the id of the playerDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the playerDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/players/{id}")
	public ResponseEntity<PlayerDTO> getPlayer(@PathVariable final Long id) {
		log.debug("REST request to get Player : {}", id);
		final Optional<PlayerDTO> playerDTO = playerService.findOne(id);
		return ResponseEntity.ok(playerDTO.orElse(new PlayerDTO()));
	}
	
	@GetMapping("/game-deck/{gameDeckId}/players-totals")
	public ResponseEntity<List<PlayerTotalCard>> getPlayerTotalCard(@PathVariable final Long gameDeckId) {
		log.debug("REST request to get Player total game deck : {}", gameDeckId);
		return ResponseEntity.ok(playerService.TotalAddPerPlayer(gameDeckId));
	}

	/**
	 * PUT /players : Updates an existing player.
	 *
	 * @param playerDTO the playerDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         playerDTO, or with status 400 (Bad Request) if the playerDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the playerDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/players")
	public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody final PlayerDTO playerDTO) throws URISyntaxException {
		log.debug("REST request to update Player : {}", playerDTO);
		if (Objects.isNull(playerDTO.getId()))
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
		final PlayerDTO result = playerService.save(playerDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, playerDTO.getId().toString())).body(result);
	}
}
