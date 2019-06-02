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

import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.repository.PlayerRepository;
import ca.logmein.pokergameapi.service.dto.PlayerDTO;
import ca.logmein.pokergameapi.service.dto.PlayerTotalCard;
import ca.logmein.pokergameapi.service.interfaces.PlayerService;
import ca.logmein.pokergameapi.service.mapper.PlayerMapper;

/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

	private final PlayerMapper playerMapper;

	private final PlayerRepository playerRepository;

	public PlayerServiceImpl(final PlayerRepository playerRepository, final PlayerMapper playerMapper) {
		this.playerRepository = playerRepository;
		this.playerMapper = playerMapper;
	}

	/**
	 * Delete the player by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(final Long id) {
		log.debug("Request to delete Player : {}", id);
		playerRepository.deleteById(id);
	}

	@Override
	public void delete(final String pseudo) {
		log.debug("Request to delete Player : {}", pseudo);
		playerRepository.deleteByPseudo(pseudo);
	}

	/**
	 * Get all the players.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PlayerDTO> findAll() {
		log.debug("Request to get all Players");
		return playerRepository.findAll().stream().map(playerMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<PlayerDTO> findByPseudo(final String pseudo) {
		log.debug("Request to get Players by pseudo");
		return playerRepository.findByPseudo(pseudo).stream().map(playerMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one player by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PlayerDTO> findOne(final Long id) {
		log.debug("Request to get Player : {}", id);
		return playerRepository.findById(id).map(playerMapper::toDto);
	}

	@Override
	public List<PlayerDTO> getAllPlayersByGameDeck(final Long gameDeckId) {
		final GameDeck gameDeck = new GameDeck();
		gameDeck.setId(gameDeckId);
		log.debug("Request all Players on a game deck : {}", gameDeckId);
		return playerRepository.findByGameDeck(gameDeck).stream().map(playerMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Save a player.
	 *
	 * @param playerDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public PlayerDTO save(final PlayerDTO playerDTO) {
		log.debug("Request to save Player : {}", playerDTO);
		playerDTO.setCreateDate(LocalDate.now());
		Player player = playerMapper.toEntity(playerDTO);
		player = playerRepository.save(player);
		return playerMapper.toDto(player);
	}

	@Override
	public List<PlayerTotalCard>  TotalAddPerPlayer(final Long gameDeckId) {
		final GameDeck gameDeck = new GameDeck();
		gameDeck.setId(gameDeckId);
		return playerRepository.playerTotalCards(gameDeck);
	}
	
	

}
