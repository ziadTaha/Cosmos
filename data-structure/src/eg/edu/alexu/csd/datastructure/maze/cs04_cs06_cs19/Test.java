package eg.edu.alexu.csd.datastructure.maze.cs04_cs06_cs19;

import java.io.File;
/**
 *
 * @author elzol
 *
 */

public class Test {
/**
 *
 * @param args
 * main para
 */
	public static void main(final String[] args) {
		Maze o = new Maze();
		File f = new File("f.txt");
		int[][] a = o.solveDFS(f);
		int[][] b = o.solveBFS(f);
		System.out.println("DFS");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("BFS");
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

}
