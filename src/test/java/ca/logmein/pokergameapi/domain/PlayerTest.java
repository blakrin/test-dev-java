package ca.logmein.pokergameapi.domain;

import ca.logmein.pokergameapi.utils.DtoTest;

public class PlayerTest extends DtoTest<Player>{

	public PlayerTest() {
		super(null, null);
	}

	@Override
	protected Player getInstance() {
		return new Player();
	}


}
