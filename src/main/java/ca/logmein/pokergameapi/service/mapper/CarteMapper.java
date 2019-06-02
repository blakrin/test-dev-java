package ca.logmein.pokergameapi.service.mapper;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.logmein.pokergameapi.domain.Carte;
import ca.logmein.pokergameapi.service.dto.CardsDTO;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Mapper(componentModel = "spring", uses = {DeckMapper.class})
public interface CarteMapper extends EntityMapper<CardsDTO, Carte> {

    default Carte fromId(final Long id) {
        if (Objects.isNull(id))
			return null;
		else {
        	 final Carte carte = new Carte();
             carte.setId(id);
             return carte;
        }
			
       
    }
    @Override
	@Mapping(source = "deck.id", target = "deckId")
    @Mapping(source = "player.id", target = "playerId")
    CardsDTO toDto(Carte carte);

    @Override
	@Mapping(source = "deckId", target = "deck")
    Carte toEntity(CardsDTO carteDTO);
}
