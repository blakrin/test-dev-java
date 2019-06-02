package ca.logmein.pokergameapi.service.dto;

import ca.logmein.pokergameapi.utils.DtoTest;

public class PlayerDTOTest extends DtoTest<PlayerDTO> {

	public PlayerDTOTest() {
		super(null, null);
	}

	@Override
	protected PlayerDTO getInstance() {
		return new PlayerDTO();
	}
	
	

}
