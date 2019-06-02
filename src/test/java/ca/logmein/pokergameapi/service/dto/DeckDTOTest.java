package ca.logmein.pokergameapi.service.dto;

import ca.logmein.pokergameapi.utils.DtoTest;

public class DeckDTOTest extends DtoTest<DeckDTO> {

	public DeckDTOTest() {
		super(null, null);
	}

	@Override
	protected DeckDTO getInstance() {
		return new DeckDTO();
	}

	

}
