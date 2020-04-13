package game.Maths;

import java.util.Random;

public class Maths {

	public static float randomFloat(float upper, float lower) {
		return (float) ((Math.random() * (upper - lower)) + lower);
	}
	
	public static int randomInt(int upper, int lower) {
		return new Random().nextInt(upper + 1 - lower) + lower;
	}
	
	public static int randomInt(Random random, int upper, int lower) {
		return random.nextInt(upper + 1 - lower) + lower;
	}
	
	public static boolean randomBool() {
		return randomInt(1, 0) == 1;
	}
	
	public static boolean randomBool(Random random) {
		return randomInt(random, 1, 0) == 1;
	}
	
}
