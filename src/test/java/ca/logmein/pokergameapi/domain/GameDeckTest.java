package ca.logmein.pokergameapi.domain;

import ca.logmein.pokergameapi.utils.DtoTest;

public class GameDeckTest extends DtoTest<GameDeck>{

	public GameDeckTest() {
		super(null, null);
	}


	@Override
	protected GameDeck getInstance() {
		return new GameDeck();
	}

}
