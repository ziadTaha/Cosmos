package eg.edu.alexu.csd.datastructure.iceHockey.cs19;

import java.awt.Point;
import org.junit.Assert;
import org.junit.Test;
import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;
/**
 *
 * @author ziad taha
 *
 */
public class Jtest {

	/**
	 * An object to test the class on.
	 */
	private PlayersFinder x = new PlayersFinder();
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto4() {
		IPlayersFinder test = x;
		final String[] photo = {
				"44444H44S4",
				"K444K4L444",
				"4LJ44T44XH",
				"444O4VIF44",
				"44C4D4U444",
				"4V4Y4KB4M4",
				"G4W4HP4O4W",
				"4444ZDQ4S4",
				"4BR4Y4A444",
				"4G4V4T4444"};
		final int team = 4;
		final int threshold = 16;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(3, 8),
				new Point(4, 16),
				new Point(5, 4),
				new Point(16, 3),
				new Point(16, 17),
				new Point(17, 9)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto5() {
		IPlayersFinder test = x;
		final String[] photo = {
				"8D88888J8L8E888",
				"88NKMG8N8E8JI88",
				"888NS8EU88HN8EO",
				"LUQ888A8TH8OIH8",
				"888QJ88R8SG88TY",
				"88ZQV88B88OUZ8O",
				"FQ88WF8Q8GG88B8",
				"8S888HGSB8FT8S8",
				"8MX88D88888T8K8",
				"8S8A88MGVDG8XK8",
				"M88S8B8I8M88J8N",
				"8W88X88ZT8KA8I8",
				"88SQGB8I8J88W88",
				"U88H8NI8CZB88B8",
				"8PK8H8T8888TQR8"};
		final int team = 8;
		final int threshold = 9;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(1, 17),
				new Point(3, 3),
				new Point(3, 10),
				new Point(3, 25),
				new Point(5, 21),
				new Point(8, 17),
				new Point(9, 2),
				new Point(10, 9),
				new Point(12, 23),
				new Point(17, 16),
				new Point(18, 3),
				new Point(18, 11),
				new Point(18, 28),
				new Point(22, 20),
				new Point(23, 26),
				new Point(24, 15),
				new Point(27, 2),
				new Point(28, 26),
				new Point(29, 16)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto1() {
		IPlayersFinder test = x;
		final String[] photo = null;
		final int team = 4;
		final int threshold = 16;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = null;
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto2() {
		IPlayersFinder test = x;
		final String[] photo = {
				"11111",
				"1AAA1",
				"1A1A1",
				"1AAA1",
				"11111"};
		final int team = 1;
		final int threshold = 3;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(5, 5),
				new Point(5, 5)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto3() {
		IPlayersFinder test = x;
		final String[] photo = {
				"33JUBU33",
				"3U3O4433",
				"O33P44NB",
				"PO3NSDP3",
				"VNDSD333",
				"OINFD33X"};
		final int team = 3;
		final int threshold = 16;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(4, 5),
				new Point(13, 9),
				new Point(14, 2)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto6() {
		IPlayersFinder test = x;
		final String[] photo = {
				"1"};
		final int team = 1;
		final int threshold = 4;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(1, 1)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto7() {
		IPlayersFinder test = x;
		final String[] photo = {
				""};
		final int team = 3;
		final int threshold = 16;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto8() {
		IPlayersFinder test = x;
		final String[] photo = {
				"0"};
		final int team = 1;
		final int threshold = 4;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto9() {
		IPlayersFinder test = x;
		final String[] photo = {
				"33JUBU33",
				"3U3O4433",
				"O33P44NB",
				"PO3NSDP3",
				"VNDSD333",
				"OINFD33X"};
		final int team = 4;
		final int threshold = 8;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(10, 4)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
	/**
	 * given test.
	 */
	@Test
	public void randomPhoto10() {
		IPlayersFinder test = x;
		final String[] photo = {
				"33JUBU33",
				"3U3O4433",
				"O33P44NB",
				"PO3NSDP3",
				"VNDSD333",
				"OINFD33X"};
		final int team = 3;
		final int threshold = 24;
		Point[] returnedPoints;
		returnedPoints = test.findPlayers(photo, team,
				threshold);
		final Point[] ans = {
				new Point(13, 9)};
		Assert.assertArrayEquals(ans, returnedPoints);
	}
}
