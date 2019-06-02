package ca.logmein.pokergameapi.service.dto;

import ca.logmein.pokergameapi.utils.DtoTest;

public class PlayerTotalCardTest extends DtoTest<PlayerTotalCard>{

	public PlayerTotalCardTest() {
		super(null, null);
	}

	

	@Override
	protected PlayerTotalCard getInstance() {
		return new PlayerTotalCard();
	}

}
