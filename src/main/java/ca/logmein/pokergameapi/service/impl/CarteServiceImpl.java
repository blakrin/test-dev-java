package ca.logmein.pokergameapi.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.logmein.pokergameapi.domain.Carte;
import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.enums.ColorEnum;
import ca.logmein.pokergameapi.enums.FaceEnum;
import ca.logmein.pokergameapi.repository.CarteRepository;
import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.interfaces.CarteService;
import ca.logmein.pokergameapi.service.mapper.CarteMapper;


/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@Service
@Transactional
public class CarteServiceImpl implements CarteService {

	/** The carte mapper. */
	private final CarteMapper carteMapper;

	/** The carte repository. */
	private final CarteRepository carteRepository;

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CarteServiceImpl.class);

	/**
	 * Instantiates a new carte service impl.
	 *
	 * @param carteRepository the carte repository
	 * @param carteMapper     the carte mapper
	 */
	public CarteServiceImpl(final CarteRepository carteRepository, final CarteMapper carteMapper) {
		this.carteRepository = carteRepository;
		this.carteMapper = carteMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.logmein.pokergameapi.service.interfaces.CarteService#createCartesForADeck(
	 * java.lang.Long)
	 */
	@Override
	public Set<Carte> createCartesForADeck(final Long deckId) {
		final Set<Carte> result = new HashSet<>();
		Arrays.asList(ColorEnum.values()).stream()
				.forEach(color -> result.addAll(Arrays.asList(FaceEnum.values()).stream().map(face -> {
					final CardsDTO carteDTO = new CardsDTO();
					carteDTO.setColor(color.name());
					carteDTO.setFaceName(face.name());
					carteDTO.setFaceValue(face.getValue());
					carteDTO.setDeckId(deckId);
					return carteMapper.toEntity(save(carteDTO));
				}).collect(Collectors.toSet())));
		return result;
	}

	/**
	 * Delete the carte by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(final Long id) {
		log.debug("Request to delete Carte : {}", id);
		carteRepository.deleteById(id);
	}

	@Override
	public List<CardsDTO> fandCarteByPlayerId(final Long playerId) {
		final Player player = new Player();
		player.setId(playerId);
		return carteRepository.findByPlayer(player).stream().map(carteMapper::toDto).collect(Collectors.toList());
	}

	/**
	 * Get all the cartes.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CardsDTO> findAll() {
		log.debug("Request to get all Cartes");
		return carteRepository.findAll().stream().map(carteMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/* (non-Javadoc)
	 * @see ca.logmein.pokergameapi.service.interfaces.CarteService#findCarteByDeckId(java.lang.Long)
	 */
	@Override
	public List<CardsDTO> findCarteByDeckId(final Long deckId) {
		final Deck deck = new Deck();
		deck.setId(deckId);
		return carteRepository.findByDeck(deck).stream().map(carteMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<CardsDTO> findCarteByDeckList(final List<Deck> decks) {
		log.debug("Request cards for decks list : {}", decks);
		return carteRepository.findByDeskInList(decks).stream().map(carteMapper::toDto).collect(Collectors.toList());
	}

	/**
	 * Get one carte by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<CardsDTO> findOne(final Long id) {
		log.debug("Request to get Carte : {}", id);
		return carteRepository.findById(id).map(carteMapper::toDto);
	}

	/**
	 * Save a carte.
	 *
	 * @param carteDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public CardsDTO save(final CardsDTO carteDTO) {
		log.debug("Request to save Carte : {}", carteDTO);
		Carte carte = carteMapper.toEntity(carteDTO);
		carte = carteRepository.save(carte);
		return carteMapper.toDto(carte);
	}

	@Override
	public int updateCarte(final Player player, final Long cardId) {
		return carteRepository.updateCate(player, cardId);
	}
}
