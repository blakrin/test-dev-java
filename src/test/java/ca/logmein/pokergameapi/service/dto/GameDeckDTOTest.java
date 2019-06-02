package ca.logmein.pokergameapi.service.dto;

import ca.logmein.pokergameapi.utils.DtoTest;

public class GameDeckDTOTest extends DtoTest<GameDeckDTO>{

	public GameDeckDTOTest() {
		super(null, null);
	}

	@Override
	protected GameDeckDTO getInstance() {
		return new GameDeckDTO();
	}

		
}
