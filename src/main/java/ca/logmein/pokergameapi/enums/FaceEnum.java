package ca.logmein.pokergameapi.enums;

public enum FaceEnum {
	AXE(1), EIGHT(8), FIVE(5), FOUR(4), JACK(11),
	KING(13), NINE(9),QUEEN(12), SEVEN(7), SIX(6),TEN(10),TREE(3),TWO(2);
	
	private final int value;

	private FaceEnum(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
