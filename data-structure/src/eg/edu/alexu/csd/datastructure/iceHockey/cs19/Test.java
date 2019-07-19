package eg.edu.alexu.csd.datastructure.iceHockey.cs19;
/**
 * @author Ziad Taha
 *
 */
public class Test {
/**
 * @param args
 *    main method parms
 */
	public static void main(final String[] args) {
		PlayersFinder o = new PlayersFinder();
		String[] photo = {"33JUBU33",
		"3U3O4433", "O33P44NB", "PO3NSDP3", "VNDSD333", "OINFD33X"};
		final int y = 3;
		final int z = 16;
		int x = o.findPlayers(photo, y, z).length;
		for (int i = 0; i < x; i++) {
			System.out.println(o.findPlayers(photo, y, z)[i]);
		}
	}
}
