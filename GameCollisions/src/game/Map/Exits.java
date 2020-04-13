package game.Map;

public enum Exits {

	TOP,
	BOTTOM,
	RIGHT,
	LEFT;
	
	public static Exits byName(String name) {
		return valueOf(name.toUpperCase());
	}
	
}
