package ca.logmein.pokergameapi.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ca.logmein.pokergameapi.domain.Deck;
import ca.logmein.pokergameapi.service.dto.DeckDTO;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 */
@Mapper(componentModel = "spring", uses = {GameDeckMapper.class})
public interface DeckMapper extends EntityMapper<DeckDTO, Deck> {

	default Deck fromId(final Long id) {
        if (id == null)
			return null;
        final Deck deck = new Deck();
        deck.setId(id);
        return deck;
    }

    @Override
	@Mapping(source = "gameDeck.id", target = "gameDeckId")
    DeckDTO toDto(Deck deck);

    @Override
	@Mapping(target = "cartes", ignore = true)
    @Mapping(source = "gameDeckId", target = "gameDeck")
    Deck toEntity(DeckDTO deckDTO);
}
