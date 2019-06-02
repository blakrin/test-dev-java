package ca.logmein.pokergameapi.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.repository.GameDeckRepository;
import ca.logmein.pokergameapi.service.dto.CardRest;
import ca.logmein.pokergameapi.service.dto.CardsDTO;
import ca.logmein.pokergameapi.service.dto.DeckDTO;
import ca.logmein.pokergameapi.service.dto.PlayerDTO;
import ca.logmein.pokergameapi.service.interfaces.CarteService;
import ca.logmein.pokergameapi.service.interfaces.DeckService;
import ca.logmein.pokergameapi.service.interfaces.GameDeckService;
import ca.logmein.pokergameapi.service.interfaces.PlayerService;
import ca.logmein.pokergameapi.service.mapper.DeckMapper;
import ca.logmein.pokergameapi.service.mapper.GameDeckMapper;
import ca.logmein.pokergameapi.service.mapper.PlayerMapper;

@RunWith(SpringRunner.class)
@JsonTest
public class GameDeckServiceImplTest {

	@Autowired
	JacksonTester<List<CardRest>> cardList;
	
	@Value("classpath:data/cards-with-out-players.json")
	private org.springframework.core.io.Resource cards;
	
	@Autowired
	JacksonTester<List<CardsDTO>> cardsList;
	
	@Value("classpath:data/un-deal-cards.json")
	private org.springframework.core.io.Resource cardsRest;
	
	
	private  CarteService carteService  = mock(CarteService.class);
	
	@Value("classpath:data/decks.json")
	private org.springframework.core.io.Resource decks;
	
	
	private  DeckService deckService  = mock(DeckService.class);
	
	
	@Autowired
	JacksonTester<List<DeckDTO>> declsList;
	

	private  GameDeckMapper gameDeckMapper = mock(GameDeckMapper.class);
	

	private  GameDeckRepository gameDeckRepository = mock(GameDeckRepository.class);

	private  DeckMapper mapper = mock(DeckMapper.class);
	private  PlayerMapper playerMapper = mock(PlayerMapper.class) ;
	
	private  PlayerService playerService = mock(PlayerService.class);

	@InjectMocks
	private GameDeckService gameDeckService = new GameDeckServiceImpl(gameDeckRepository, gameDeckMapper, carteService, playerService, deckService, mapper, playerMapper);
	
	
	@Autowired
	JacksonTester<List<PlayerDTO>> playerList;
	
	@Value("classpath:data/player.json")
	private org.springframework.core.io.Resource players;
	
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void whenCardRestWithNotDataInSession() {
		
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			final List<CardRest> restCard = cardList.readObject(cardsRest.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			
			final List<CardRest>  result = gameDeckService.cardRest(1L, new MockHttpSession());
			assertThat(restCard.size(), is(result.size()));
			//verify(deckService, atLeastOnce()).findByGameDeck(gameDeck);
			//verify(carteService, atLeastOnce()).findCarteByDeckList(mapper.toEntity(deckList));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void whenCardRestWithDataInSession() {
		
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			final List<CardRest> restCard = cardList.readObject(cardsRest.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			final HttpSession httpSession = new MockHttpSession();
			httpSession.setAttribute("GAME_DECK" + gameDeckId.toString(), cardLs);
			final List<CardRest>  result = gameDeckService.cardRest(1L,httpSession );
			assertThat(restCard.size(), is(result.size()));
			//verify(deckService, atLeastOnce()).findByGameDeck(gameDeck);
			//verify(carteService, atLeastOnce()).findCarteByDeckList(mapper.toEntity(deckList));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void whenDealCardsWithDataInSession() {
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			cardList.readObject(cardsRest.getInputStream());
			final List<PlayerDTO> playersList = playerList.readObject(players.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			when(playerService.getAllPlayersByGameDeck(gameDeckId)).thenReturn(playersList);
			when(carteService.updateCarte(playerMapper.toEntity(playersList.get(0)),cardLs.get(0).getDeckId())).thenReturn(1);
			final HttpSession httpSession = new MockHttpSession();
			httpSession.setAttribute("GAME_DECK" + gameDeckId.toString(), cardLs);
			gameDeckService.dealCards(1L,httpSession );
		
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenDealCardsWithNoDataInSession() {
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			cardList.readObject(cardsRest.getInputStream());
			final List<PlayerDTO> playersList = playerList.readObject(players.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			when(playerService.getAllPlayersByGameDeck(gameDeckId)).thenReturn(playersList);
			when(carteService.updateCarte(playerMapper.toEntity(playersList.get(0)),cardLs.get(0).getDeckId())).thenReturn(1);
			final HttpSession httpSession = new MockHttpSession();
			httpSession.setAttribute("GAME_DECK" + gameDeckId.toString(), cardLs);
		
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void whenDelete() {
		
	}
	@Test
	public void whenFindAll() {
		
	}
	@Test
	public void whenFindOne() {
		
	}
	@Test
	public void whenRemainingCardsWithOutDateInSession() {
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			cardList.readObject(cardsRest.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			
			final List<CardsDTO>  result = gameDeckService.remainingCards(1L, new MockHttpSession());
			assertThat(cardLs.size(), is(result.size()));
			//verify(deckService, atLeastOnce()).findByGameDeck(gameDeck);
			//verify(carteService, atLeastOnce()).findCarteByDeckList(mapper.toEntity(deckList));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void whenRemainingCardsWithDateInSession() {
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			cardList.readObject(cardsRest.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			final HttpSession httpSession = new MockHttpSession();
			httpSession.setAttribute("GAME_DECK" + gameDeckId.toString(), cardLs);
			final List<CardsDTO>  result = gameDeckService.remainingCards(1L,httpSession );
			assertThat(cardLs.size(), is(result.size()));
			//verify(deckService, atLeastOnce()).findByGameDeck(gameDeck);
			//verify(carteService, atLeastOnce()).findCarteByDeckList(mapper.toEntity(deckList));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void whenSave() {
		
	}
	@Test
	public void whenShuffle() {
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			cardList.readObject(cardsRest.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			gameDeckService.shuffle(1L, new MockHttpSession());
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenShuffleWithDataInSession() {
		try {
			final Long gameDeckId = 1L;
			final GameDeck gameDeck = new GameDeck();
			gameDeck.setId(gameDeckId);
			final List<DeckDTO> deckList = declsList.readObject(decks.getInputStream());
			final List<CardsDTO> cardLs = cardsList.readObject(cards.getInputStream());
			cardList.readObject(cardsRest.getInputStream());
			when(deckService.findByGameDeck(gameDeck)).thenReturn(deckList);
			when(carteService.findCarteByDeckList(mapper.toEntity(deckList))).thenReturn(cardLs);
			final HttpSession httpSession = new MockHttpSession();
			httpSession.setAttribute("GAME_DECK" + gameDeckId.toString(), cardLs);
			gameDeckService.shuffle(1L, httpSession);
			//verify(deckService, atLeastOnce()).findByGameDeck(gameDeck);
			//verify(carteService, atLeastOnce()).findCarteByDeckList(mapper.toEntity(deckList));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
