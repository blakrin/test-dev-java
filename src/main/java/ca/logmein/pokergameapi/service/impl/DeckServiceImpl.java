package ca.logmein.pokergameapi.service.impl;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.repository.DeckRepository;
import ca.logmein.pokergameapi.service.dto.DeckDTO;
import ca.logmein.pokergameapi.service.interfaces.CarteService;
import ca.logmein.pokergameapi.service.interfaces.DeckService;
import ca.logmein.pokergameapi.service.mapper.DeckMapper;

/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@Service
@Transactional
public class DeckServiceImpl implements DeckService {

	private final CarteService carteService;

	private final DeckMapper deckMapper;
	
	private final DeckRepository deckRepository;

	private final Logger log = LoggerFactory.getLogger(DeckServiceImpl.class);

	public DeckServiceImpl(final DeckRepository deckRepository, final DeckMapper deckMapper,final CarteService carteService) {
		this.deckRepository = deckRepository;
		this.deckMapper = deckMapper;
		this.carteService = carteService;
	}

	/**
	 * Delete the deck by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(final Long id) {
		log.debug("Request to delete Deck : {}", id);
		deckRepository.deleteById(id);
	}

	/**
	 * Get all the decks.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DeckDTO> findAll() {
		log.debug("Request to get all Decks");
		return deckRepository.findAll().stream().map(deckMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<DeckDTO> findByGameDeck(final GameDeck gameDeck) {
		return  deckRepository.findByGameDeck(gameDeck).stream().map(deckMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<DeckDTO> findDeckByName(final String name) {
		return  deckRepository.findByDeckName(name).stream().map(deckMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one deck by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<DeckDTO> findOne(final Long id) {
		log.debug("Request to get Deck : {}", id);
		return deckRepository.findById(id).map(deckMapper::toDto);
	}

	/**
	 * Save a deck.
	 *
	 * @param deckDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public DeckDTO save(final DeckDTO deckDTO) {
		log.debug("Request to save Deck : {}", deckDTO);
		deckDTO.setCreateDate(LocalDate.now());
		Deck deck = deckMapper.toEntity(deckDTO);
		deck = deckRepository.save(deck);
		deck.setCartes(carteService.createCartesForADeck(deck.getId()));
		return deckMapper.toDto(deck);
	}
}
