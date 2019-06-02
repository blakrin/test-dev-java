package ca.logmein.pokergameapi.domain;

import ca.logmein.pokergameapi.utils.DtoTest;

public class DeckTest extends DtoTest<Deck>{

	public  DeckTest() {
		super(null, null);
	}

	@Override
	protected Deck getInstance() {
		return new Deck();
	}

}
