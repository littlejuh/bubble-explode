 

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author marco.mangan@pucrs.br
 * @author marco.gonzales@pucrs.br
 */
public class TabuleiroTest {

	@Test
	public final void testLoneBubblesCantExplode() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("Y.........");
		b.explodirBolha(0, 0);

		// @formatter:off
		String expected = "Score: 0\nLast Shape: NONE\n\n    0123456789\n\n" + 
					  "  0 .......... 0 \n" +
					  "  1 .......... 1 \n" +
					  "  2 .......... 2 \n" +
					  "  3 .......... 3 \n" +
					  "  4 .......... 4 \n" +
					  "  5 .......... 5 \n" +
					  "  6 .......... 6 \n" +
					  "  7 .......... 7 \n" +
					  "  8 .......... 8 \n" +
					  "  9 .......... 9 \n" +
					  " 10 .......... 10\n" +
					  " 11 .......... 11\n" +
					  " 12 Y......... 12\n\n" +
					  "    0123456789\n";
		// @formatter:on
		String actual = b.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testHorizontalLine() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("YYYY......");
		b.explodirBolha(0, 0);

		String expected = "LINE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testVerticalLine() {
		Tabuleiro b = new Tabuleiro();
		// @formatter:off
		b.analisarString("Y........." 
				    + "Y........." 
				    + "Y........." 
				    + "Y.........");
		// @formatter:on
		b.explodirBolha(0, 0);

		String expected = "LINE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testSquare() {
		Tabuleiro b = new Tabuleiro();
		// @formatter:off
		b.analisarString("YY........" 
					+ "YY........");
		// @formatter:on
		b.explodirBolha(0, 0);

		String expected = "SQUARE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testHorizontalRectangle() {
		Tabuleiro b = new Tabuleiro();
		// @formatter:off
		b.analisarString("YYY......." 
					+ "YYY.......");
		// @formatter:on
		b.explodirBolha(0, 0);

		String expected = "RECTANGLE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testVerticalRectangle() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("YY........" + "YY........" + "YY........");
		b.explodirBolha(0, 0);

		String expected = "RECTANGLE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testCross() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString(".Y........" + "YYY......." + ".Y........");
		b.explodirBolha(0, 1);

		String expected = "CROSS";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testStairsOne() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("YY........" + ".YY.......");
		b.explodirBolha(0, 1);

		String expected = "STAIRS";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testStairsTwo() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString(".Y........" + "YY........" + "Y.........");
		b.explodirBolha(0, 1);

		String expected = "STAIRS";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testStairsThree() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString(".YY......." + "YY........");
		b.explodirBolha(0, 1);

		String expected = "STAIRS";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testStairsFour() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("Y........." + "YY........" + ".Y.......");
		b.explodirBolha(0, 0);

		String expected = "STAIRS";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testForkOne() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("Y.Y......." + "YYY......." + ".Y.......");
		b.explodirBolha(0, 0);

		String expected = "FORK";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testForkTwo() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString(".YY......." + "YY........" + ".YY......");
		b.explodirBolha(0, 1);

		String expected = "FORK";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testForkThree() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString(".Y........" + "YYY......." + "Y.Y......");
		b.explodirBolha(0, 1);

		String expected = "FORK";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testForkFour() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("YY........" + ".YY......." + "YY........");
		b.explodirBolha(0, 0);

		String expected = "FORK";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testTeeOne() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("YYY......." + ".Y........" + ".Y.......");
		b.explodirBolha(0, 0);

		String expected = "TEE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testTeeTwo() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("..Y......." + "YYY......." + "..Y.......");
		b.explodirBolha(0, 2);

		String expected = "TEE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testTeeThree() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString(".Y........" + ".Y........" + "YYY.......");
		b.explodirBolha(0, 1);

		String expected = "TEE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testTeeFour() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("Y........." + "YYY......." + "Y........");
		b.explodirBolha(0, 0);

		String expected = "TEE";
		String actual = b.getFormaAnterior();
		assertEquals(expected, actual);
	}

	@Test
	public final void testEndGameEmptyTrue() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("..........");

		boolean expected = true;
		boolean actual = b.verificarFim();
		assertEquals(expected, actual);
	}
	@Test
	public final void testHEndGameDuoFalse() {
		Tabuleiro b = new Tabuleiro();
		b.analisarString("YY........");

		boolean expected = false;
		boolean actual = b.verificarFim();
		assertEquals(expected, actual);
	}	
	
	@Test
	public final void testClearTabuleiro() {
		Tabuleiro b = new Tabuleiro();
		// @formatter:off
		b.analisarString(".........." + 
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  "YY........"
				);
		// @formatter:on
		
		b.explodirBolha(12, 0);

		// @formatter:off
		String expected = "Score: 5\nLast Shape: NONE\n\n    0123456789\n\n" + 
					  "  0 .......... 0 \n" +
					  "  1 .......... 1 \n" +
					  "  2 .......... 2 \n" +
					  "  3 .......... 3 \n" +
					  "  4 .......... 4 \n" +
					  "  5 .......... 5 \n" +
					  "  6 .......... 6 \n" +
					  "  7 .......... 7 \n" +
					  "  8 .......... 8 \n" +
					  "  9 .......... 9 \n" +
					  " 10 .......... 10\n" +
					  " 11 .......... 11\n" +
					  " 12 .......... 12\n\n" +
					  "    0123456789\n";
		// @formatter:on
		String actual = b.toString();
		assertEquals(expected, actual);
	}	
	
	@Test
	public final void testUpdateTabuleiro() {
		Tabuleiro b = new Tabuleiro();
		// @formatter:off
		b.analisarString(".........." + 
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  ".........." +
					  "GG........" +
					  "YY........" 
				);
		// @formatter:on
		
		b.explodirBolha(12, 0);

		// @formatter:off
		String expected = "Score: 5\nLast Shape: NONE\n\n    0123456789\n\n" + 
					  "  0 .......... 0 \n" +
					  "  1 .......... 1 \n" +
					  "  2 .......... 2 \n" +
					  "  3 .......... 3 \n" +
					  "  4 .......... 4 \n" +
					  "  5 .......... 5 \n" +
					  "  6 .......... 6 \n" +
					  "  7 .......... 7 \n" +
					  "  8 .......... 8 \n" +
					  "  9 .......... 9 \n" +
					  " 10 .......... 10\n" +
					  " 11 .......... 11\n" +
					  " 12 GG........ 12\n\n" +
					  "    0123456789\n";
		// @formatter:on
		String actual = b.toString();
		assertEquals(expected, actual);
	}	
}
