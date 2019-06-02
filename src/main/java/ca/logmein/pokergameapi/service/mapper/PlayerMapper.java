package ca.logmein.pokergameapi.service.mapper;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.logmein.pokergameapi.domain.Player;
import ca.logmein.pokergameapi.service.dto.PlayerDTO;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Mapper(componentModel = "spring", uses = {GameDeckMapper.class})
public interface PlayerMapper extends EntityMapper<PlayerDTO, Player> {

	   default Player fromId(final Long id) {
	    if (Objects.isNull(id))
			return null;
	    final Player player = new Player();
	    player.setId(id);
	    return player;
	}

	    @Override
		@Mapping(source = "gameDeck.id", target = "gameDeckId")
		    PlayerDTO toDto(Player player);

	    @Override
		@Mapping(source = "gameDeckId", target = "gameDeck")
	    Player toEntity(PlayerDTO playerDTO);
}
