package ca.logmein.pokergameapi.service.dto;

import ca.logmein.pokergameapi.utils.DtoTest;

public class CardsDTOTest extends DtoTest<CardsDTO>{

	public CardsDTOTest() {
		super(null, null);
	}


	@Override
	protected CardsDTO getInstance() {
		return new CardsDTO();
	}

}
