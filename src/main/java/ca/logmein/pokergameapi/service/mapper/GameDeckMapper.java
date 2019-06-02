package ca.logmein.pokergameapi.service.mapper;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.logmein.pokergameapi.domain.GameDeck;
import ca.logmein.pokergameapi.service.dto.GameDeckDTO;

/**
 * 
 * @author Blaise Siani
 * @Date May 31, 2019
 *
 */
@Mapper(componentModel = "spring", uses = {})
public interface GameDeckMapper extends EntityMapper<GameDeckDTO, GameDeck> {

	default GameDeck fromId(final Long id) {
		if (Objects.isNull(id))
			return null;
		final GameDeck gameDeck = new GameDeck();
		gameDeck.setId(id);
		return gameDeck;
	}

	@Override
	@Mapping(target = "decks", ignore = true)
	@Mapping(target = "players", ignore = true)
	GameDeck toEntity(GameDeckDTO gameDeckDTO);
}
