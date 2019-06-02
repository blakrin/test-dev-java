package ca.logmein.pokergameapi.domain;

import ca.logmein.pokergameapi.utils.DtoTest;

public class CarteTest extends DtoTest<Carte>{

	public CarteTest() {
		super(null, null);
	}

	@Override
	protected Carte getInstance() {
		return new Carte();
	}

	
}
