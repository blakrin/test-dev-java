package ca.logmein.pokergameapi.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.repository.GameDeckRepository;
import ca.logmein.pokergameapi.service.dto.CardRest;
import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.dto.DeckDTO;
import ca.logmein.pokergameapi.service.dto.GameDeckDTO;
import ca.logmein.pokergameapi.service.dto.PlayerDTO;
import ca.logmein.pokergameapi.service.interfaces.CarteService;
import ca.logmein.pokergameapi.service.interfaces.DeckService;
import ca.logmein.pokergameapi.service.interfaces.GameDeckService;
import ca.logmein.pokergameapi.service.interfaces.PlayerService;
import ca.logmein.pokergameapi.service.mapper.DeckMapper;
import ca.logmein.pokergameapi.service.mapper.GameDeckMapper;
import ca.logmein.pokergameapi.service.mapper.PlayerMapper;

/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@Service
@Transactional
public class GameDeckServiceImpl implements GameDeckService {
	
	/** The Constant CARD_TOTAL. */
	
	/** The carte service. */
	private  CarteService carteService;

	/** The deck mapper. */
	private  DeckMapper deckMapper;

	/** The deck service. */
	private  DeckService deckService;

	/** The game deck mapper. */
	private  GameDeckMapper gameDeckMapper;

	/** The game deck repository. */
	private  GameDeckRepository gameDeckRepository;

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(GameDeckServiceImpl.class);

	/** The player mapper. */
	private  PlayerMapper playerMapper;

	/** The player service. */
	private  PlayerService playerService;

	/**
	 * Instantiates a new game deck service impl.
	 *
	 * @param gameDeckRepository the game deck repository
	 * @param gameDeckMapper the game deck mapper
	 * @param carteService the carte service
	 * @param playerService the player service
	 * @param deckService the deck service
	 * @param mapper the mapper
	 * @param playerMapper the player mapper
	 */
	public GameDeckServiceImpl( GameDeckRepository gameDeckRepository,  GameDeckMapper gameDeckMapper,
			 CarteService carteService,  PlayerService playerService,  DeckService deckService,
			 DeckMapper mapper,  PlayerMapper playerMapper) {
		this.gameDeckRepository = gameDeckRepository;
		this.gameDeckMapper = gameDeckMapper;
		this.carteService = carteService;
		this.deckService = deckService;
		this.playerService = playerService;
		deckMapper = mapper;
		this.playerMapper = playerMapper;
	}

	/* (non-Javadoc)
	 * @see ca.logmein.pokergameapi.service.interfaces.GameDeckService#cardRest(java.lang.Long, javax.servlet.http.HttpSession)
	 */
	@Override
	public List<CardRest> cardRest(Long gameDeckId, HttpSession session) {
		log.debug("dealCards for game deck {}", gameDeckId);
		if (Objects.isNull(session.getAttribute("GAME_DECK" + gameDeckId.toString()))) {
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			// get list of deck on game deck
			final List<DeckDTO> deckList = deckService.findByGameDeck(gameDeck);
			// get cards for all decks
			final List<CardsDTO> cardList = carteService.findCarteByDeckList(deckMapper.toEntity(deckList));
			return cardList.stream().collect(Collectors.groupingBy(CardsDTO::getColor)).values().stream().map(card -> {
				final CardRest cartRest = new CardRest();
				cartRest.setSuit(card.get(0).getColor().toLowerCase());
				cartRest.setQuatity(card.size());
				return cartRest;
			}).collect(Collectors.toList());
		} else {
			@SuppressWarnings("unchecked")
			final List<CardsDTO> cardList = (List<CardsDTO>) session.getAttribute("GAME_DECK" + gameDeckId.toString());
			return cardList.stream().collect(Collectors.groupingBy(CardsDTO::getColor)).values().stream().map(card -> {
				final CardRest cartRest = new CardRest();
				cartRest.setSuit(card.get(0).getColor());
				cartRest.setQuatity(card.size());
				return cartRest;
			}).collect(Collectors.toList());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.logmein.pokergameapi.service.interfaces.GameDeckService#dealCards(java.
	 * lang.Long, javax.servlet.http.HttpSession)
	 */
	@Override
	public void dealCards(final Long gamedeckId, final HttpSession session) {
		log.debug("dealCards for game deck {}", gamedeckId);
		final GameDeck gameDeck = new GameDeck();
		if (Objects.isNull(session.getAttribute("GAME_DECK" + gamedeckId.toString()))) {
			gameDeck.setId(gamedeckId);
			// get list of deck on game deck
			final List<DeckDTO> deckList = deckService.findByGameDeck(gameDeck);
			// get cards for all decks
			final List<CardsDTO> cardList = carteService.findCarteByDeckList(deckMapper.toEntity(deckList));
			// get all players of a game deck
			final List<PlayerDTO> playerList = playerService.getAllPlayersByGameDeck(gamedeckId);
			playerList.stream().forEach(player -> {
				final CardsDTO randomCard = cardList.get(0);
				carteService.updateCarte(playerMapper.toEntity(player), randomCard.getId());
				cardList.remove(0);
			});
			session.setAttribute("GAME_DECK" + gamedeckId.toString(), cardList);
		} else {
			@SuppressWarnings("unchecked")
			final List<CardsDTO> cardList = (List<CardsDTO>) session.getAttribute("GAME_DECK" + gamedeckId.toString());
			if (!cardList.isEmpty()) {
				final List<PlayerDTO> playerList = playerService.getAllPlayersByGameDeck(gamedeckId);
				playerList.stream().forEach(player -> {
					final CardsDTO randomCard = cardList.get(0);
					carteService.updateCarte(playerMapper.toEntity(player), randomCard.getId());
					cardList.remove(0);
				});
			}
			session.setAttribute("GAME_DECK" + gamedeckId.toString(), cardList);
		}

	}

	/**
	 * Delete the gameDeck by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(final Long id) {
		log.debug("Request to delete GameDeck : {}", id);
		gameDeckRepository.deleteById(id);
	}

	/**
	 * Get all the gameDecks.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<GameDeckDTO> findAll() {
		log.debug("Request to get all GameDecks");
		return gameDeckRepository.findAll().stream().map(gameDeckMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one gameDeck by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<GameDeckDTO> findOne(final Long id) {
		log.debug("Request to get GameDeck : {}", id);
		return gameDeckRepository.findById(id).map(gameDeckMapper::toDto);
	}

	/* (non-Javadoc)
	 * @see ca.logmein.pokergameapi.service.interfaces.GameDeckService#remainingCards(java.lang.Long, javax.servlet.http.HttpSession)
	 */
	@Override
	public List<CardsDTO> remainingCards(Long gameDeckId, HttpSession session) {
		log.debug("dealCards for game deck {}", gameDeckId);
		final List<String> orderSuit = Arrays.asList("hearts", "spades", "clubs", "diamonds");
		if (Objects.isNull(session.getAttribute("GAME_DECK" + gameDeckId.toString()))) {
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			// get list of deck on game deck
			final List<DeckDTO> deckList = deckService.findByGameDeck(gameDeck);
			// get cards for all decks
			final List<CardsDTO> cardList = carteService.findCarteByDeckList(deckMapper.toEntity(deckList));
			return cardList.stream().sorted(new Comparator<CardsDTO>() {
				@Override
				public int compare(CardsDTO o1, CardsDTO o2) {
					if (orderSuit.indexOf(o1.getColor().toLowerCase()) > orderSuit.indexOf(o2.getColor().toLowerCase()))
						return 1;
					else
						return -1;
				}
			}).collect(Collectors.toList()).stream().sorted(new Comparator<CardsDTO>() {

				@Override
				public int compare(CardsDTO card1, CardsDTO card2) {
					if (card1.getColor().toLowerCase().equals(card2.getColor().toLowerCase()))
						return -card1.getFaceValue().compareTo(card2.getFaceValue());
					return 0;
				}
			}).collect(Collectors.toList());
		} else {
			@SuppressWarnings("unchecked")
			final List<CardsDTO> cardList = (List<CardsDTO>) session.getAttribute("GAME_DECK" + gameDeckId.toString());
			return cardList.stream().sorted(new Comparator<CardsDTO>() {
				@Override
				public int compare(CardsDTO o1, CardsDTO o2) {
					if (orderSuit.indexOf(o1.getColor().toLowerCase()) > orderSuit.indexOf(o2.getColor().toLowerCase()))
						return 1;
					else
						return -1;
				}
			}).collect(Collectors.toList()).stream().sorted(new Comparator<CardsDTO>() {

				@Override
				public int compare(CardsDTO card1, CardsDTO card2) {
					if (card1.getColor().toLowerCase().equals(card2.getColor().toLowerCase()))
						return -card1.getFaceValue().compareTo(card2.getFaceValue());
					return 0;
				}
			}).collect(Collectors.toList());
		}
	}

	/**
	 * Save a gameDeck.
	 *
	 * @param gameDeckDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public GameDeckDTO save(final GameDeckDTO gameDeckDTO) {
		log.debug("Request to save GameDeck : {}", gameDeckDTO);
		gameDeckDTO.setCreateDate(LocalDate.now());
		GameDeck gameDeck = gameDeckMapper.toEntity(gameDeckDTO);
		gameDeck = gameDeckRepository.save(gameDeck);
		return gameDeckMapper.toDto(gameDeck);
	}

	/* (non-Javadoc)
	 * @see ca.logmein.pokergameapi.service.interfaces.GameDeckService#shuffle(java.lang.Long, javax.servlet.http.HttpSession)
	 */
	@Override
	public void shuffle(Long gameDeckId, HttpSession session) {
		
		if (Objects.isNull(session.getAttribute("GAME_DECK" + gameDeckId.toString()))) {
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			// get list of deck on game deck
			final List<DeckDTO> deckList = deckService.findByGameDeck(gameDeck);
			// get cards for all decks
			final List<CardsDTO> cardList = carteService.findCarteByDeckList(deckMapper.toEntity(deckList));
			session.setAttribute("GAME_DECK" + gameDeckId.toString(), shuffleCard(cardList));
		}else {
			@SuppressWarnings("unchecked")
			final List<CardsDTO> cardList = (List<CardsDTO>) session.getAttribute("GAME_DECK" + gameDeckId.toString());
			session.setAttribute("GAME_DECK" + gameDeckId.toString(), shuffleCard(cardList));
		}
	}
	
	/**
	 * Shuffle card.
	 *
	 * @param cardsList the cards list
	 * @return the list
	 */
	private List<CardsDTO> shuffleCard(List<CardsDTO> cardsList){
		final List<CardsDTO> shuffleCard = new ArrayList<>();
		final int size = cardsList.size();
		for (int i = 0; i < size; i++) {
			final int randomPosition = new Random().nextInt(cardsList.size());
			shuffleCard.add(cardsList.get(randomPosition));
			cardsList.remove(randomPosition);
		}
		return shuffleCard;
	}
}
