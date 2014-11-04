package mirrg.mir40.math;

import java.util.Random;

public class HelpersMath
{

	public static final Random random = new Random();

	public static int floorRandom(double value)
	{
		return floorRandom(value, random);
	}

	public static int floorRandom(double value, Random random)
	{
		int integer = (int) Math.floor(value);
		double addition = value - integer;

		if (random.nextDouble() < addition) {
			integer++;
		}

		return integer;
	}

	public static int trim(int value, int min, int max)
	{
		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

}
