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
import ca.logmein.pokergameapi.service.interfaces.CarteService;
import ca.logmein.pokergameapi.web.error.BadRequestAlertException;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@RestController
@RequestMapping("/api")
public class CarteResource {

    private static final String ENTITY_NAME = "carte";

    private final CarteService carteService;

    private final Logger log = LoggerFactory.getLogger(CarteResource.class);

    public CarteResource(final CarteService carteService) {
        this.carteService = carteService;
    }

    /**
     * POST  /cartes : Create a new carte.
     *
     * @param carteDTO the carteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carteDTO, or with status 400 (Bad Request) if the carte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cartes")
    public ResponseEntity<CardsDTO> createCarte(@RequestBody final CardsDTO carteDTO) throws URISyntaxException {
        log.debug("REST request to save Carte : {}", carteDTO);
        if (Objects.nonNull(carteDTO.getId()))
			throw new BadRequestAlertException("A new deck cannot already have an ID", ENTITY_NAME, "idexists");
        final CardsDTO result = carteService.save(carteDTO);
        return ResponseEntity.created(new URI("/api/cartes/" + result.getId()))
            .body(result);
    }

    /**
     * DELETE  /cartes/:id : delete the "id" carte.
     *
     * @param id the id of the carteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cartes/{id}")
    public ResponseEntity<Void> deleteCarte(@PathVariable final Long id) {
        log.debug("REST request to delete Carte : {}", id);
        carteService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /cartes : get all the cartes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cartes in body
     */
    @GetMapping("/cartes")
    public List<CardsDTO> getAllCartes() {
        log.debug("REST request to get all Cartes");
        return carteService.findAll();
    }

    /**
     * GET  /cartes/:id : get the "id" carte.
     *
     * @param id the id of the carteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cartes/{id}")
    public ResponseEntity<CardsDTO> getCarte(@PathVariable final Long id) {
        log.debug("REST request to get Carte : {}", id);
        final Optional<CardsDTO> carteDTO = carteService.findOne(id);
        return ResponseEntity.ok(carteDTO.orElse(new CardsDTO()));
    }

    /**
     * PUT  /cartes : Updates an existing carte.
     *
     * @param carteDTO the carteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carteDTO,
     * or with status 400 (Bad Request) if the carteDTO is not valid,
     * or with status 500 (Internal Server Error) if the carteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cartes")
    public ResponseEntity<CardsDTO> updateCarte(@RequestBody final CardsDTO carteDTO) throws URISyntaxException {
        log.debug("REST request to update Carte : {}", carteDTO);
        if (Objects.isNull(carteDTO.getId()))
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        final CardsDTO result = carteService.save(carteDTO);
        return ResponseEntity.ok(result);
    }
}
