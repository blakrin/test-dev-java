package ca.logmein.pokergameapi.service.dto;

import ca.logmein.pokergameapi.utils.DtoTest;

public class CardRestTest  extends DtoTest<CardRest>{

	public CardRestTest() {
		super(null, null);
	}

	@Override
	protected CardRest getInstance() {
		return new CardRest();
	}

	

}
