import java.math.BigInteger;
import java.math.BigDecimal;


public class ReverseFib {

	private BigInteger initialNumber;
	private BigDecimal GOLDEN_RATIO;

	public ReverseFib(BigInteger number, BigDecimal goldenRatio) {
		this.initialNumber = number;
		this.GOLDEN_RATIO = goldenRatio;
	}

	public ReverseFib(BigInteger number) {
		this(number, GoldenRatio.get(number.toString().length()));
	}

	public static void main(String... pumpkins) {

		// OpenFile results = new OpenFile("reverse_fib_steps.txt").clear();
		// BigInteger big2 = new BigInteger(pumpkins[1]);
		// ReverseFib RF = new ReverseFib(big2);

		// for (BigInteger i = new BigInteger(pumpkins[0]); i.compareTo(big2) <= 0; i = i.add(BigInteger.ONE))
		// 	results.write(RF.getLongestFib(i) + "\n", true);
		// results.close();

		ReverseFib RF = new ReverseFib(new BigInteger(pumpkins[0]));
		RF.run();
	}

	public BigInteger getLongestFib(BigInteger initialNumber) {
		BigInteger guessB = getGoldenRatioGuess(initialNumber), guessA = initialNumber.subtract(guessB);
		BigInteger[] longestFib = reverseFib(guessB, guessA);

		// Golden ratio works +- 1 (through empirical data)
		BigInteger[] temp = reverseFib(guessB.subtract(BigInteger.ONE), guessA.add(BigInteger.ONE));
		if (temp[4].compareTo(longestFib[4]) > 0)
			longestFib = temp;
		return longestFib[4];
	}

	public void run() {
		BigInteger guessB = getGoldenRatioGuess(initialNumber), guessA = initialNumber.subtract(guessB);
		BigInteger[] longestFib = reverseFib(guessB, guessA);

		// Golden ratio works +- 1 (through empirical data)
		BigInteger[] temp = reverseFib(guessB.subtract(BigInteger.ONE), guessA.add(BigInteger.ONE));
		if (temp[4].compareTo(longestFib[4]) > 0)
			longestFib = temp;

		System.out.printf("\nFirst Two Numbers: %s, %s", longestFib[0], longestFib[1]);
		System.out.printf("\nLast Two Numbers: %s, %s", longestFib[2], longestFib[3]);
		System.out.printf("\nNum Steps: %s\n\n", longestFib[4]);
	}

	public BigInteger[] reverseFib(BigInteger b, BigInteger a) {
		BigInteger temp = new BigInteger("-1");
		BigInteger lastB = BigInteger.ZERO;
		BigInteger numSteps = new BigInteger("2");
		BigInteger[] returns = new BigInteger[5];
		returns[0] = b;
		returns[1] = a;
		while (a.compareTo(BigInteger.ZERO) > 0) {
			lastB = b;
			temp = b.subtract(a);
			b = a;
			a = temp;
			numSteps = numSteps.add(BigInteger.ONE);
		}
		returns[2] = b;
		returns[3] = lastB;
		returns[4] = numSteps;
		return returns;
	}

	private BigInteger getGoldenRatioGuess(BigInteger number) {
		BigDecimal quantity = BigDecimal.ONE.divide(GOLDEN_RATIO, number.toString().length(), BigDecimal.ROUND_HALF_UP).add(BigDecimal.ONE);
		return new BigDecimal(number).divide(quantity, BigDecimal.ROUND_CEILING).toBigInteger();
		// 	(number / (1 + 1 / GOLDEN_RATIO) + 0.5)
	}


}