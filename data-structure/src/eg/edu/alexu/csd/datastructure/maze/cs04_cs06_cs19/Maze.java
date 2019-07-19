package eg.edu.alexu.csd.datastructure.maze.cs04_cs06_cs19;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19.MyLinkedBased;
import eg.edu.alexu.csd.datastructure.stack.cs19.MyStack;
/**
 *
 * @author abo el zanazen ,3abdlah& el zol
 *
 */
public class Maze implements IMazeSolver {
/**
 * @serialField length
 */
	private int length;
	/**
	 * @serialField width
	 */
	private int width;
	/**
	 * @serialField visited
	 */
	private boolean[][] visited;
	/**
	 * @serialField queue
	 */
	private MyLinkedBased queue;
	/**
	 * @serialField stack
	 */
	private MyStack stack;
	/**
	 * @serialField route
	 */
	private final MyStack route = new MyStack();
	/**
	 * @serialField sc
	 */
	private Scanner sc;
	/**
	 * @serialField check
	 */
	private boolean check = false;

	@Override
	public int[][] solveBFS(final File maze) {
		// TODO Auto-generated method stub
		openFile(maze);
		final Point size = getSize();
		length = size.getX();
		width = size.getY();
		final char[][] map = getMap();
		closeFile();
		visited = new boolean[length][width];
		final Point start = getStart(map);
		final Point end = getEnd(map);
		boolean deWey = false;
		MazeNode current = new MazeNode(start, null);
		queue = new MyLinkedBased();
		queue.enqueue(current);
		visited[current.getCurrent().getX()]
				[current.getCurrent().getY()] = true;
		boolean f = false;
		while (!queue.isEmpty()) {
			current = (MazeNode) queue.dequeue();
			if (!f) {
				f = true;
				route.push(new MazeNode(new Point(current.
						getCurrent().getX(),
						current.getCurrent()
						.getY()), null));
			} else {
route.push(new MazeNode(new Point(current.getCurrent().getX(),
current.getCurrent().getY()),
new Point(current.getParent().getX(),
current.getParent().getY())));
			}
if ((current.getCurrent().getX() == end.getX())
&& (current.getCurrent().getY() == end.getY())) {
				deWey = true;
				break;
			}
	checkNeighborsBFS(current.getCurrent(), map);
		}
		int[][] answer = null;
		MyStack ans;
		if (deWey) {
			ans = new MyStack();
			ans.push(end);
			Point daddy = ((MazeNode)
					route.pop()).getParent();
			while ((daddy.getX() != start.getX())
			|| (daddy.getY() != start.getY())) {
				while (!(((MazeNode)
				route.peek()).getCurrent().getX()
				== daddy.getX()
				&& (((MazeNode)
				route.peek()).getCurrent().getY()
						== daddy.getY()))) {
					route.pop();
				}
				ans.push(((MazeNode)
						route.peek()).getCurrent());
				daddy = ((MazeNode)
						route.peek()).getParent();
				route.pop();
			}
			ans.push(start);
			final int ss = ans.size();
			answer = new int[ans.size()][2];
			for (int i = 0; i < ss; i++) {
				answer[i][0] = ((Point)
						ans.peek()).getX();
				answer[i][1] = ((Point)
						ans.peek()).getY();
				ans.pop();
			}
			return answer;
		} else {
			return null;
		}

	}

	@Override
	public int[][] solveDFS(final File maze) {
		// TODO Auto-generated method stub

		char[][] m = readFile(maze);
		Point current = new Point();
		current.setX(0);
		current.setY(0);
		int check1 = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 'E') {
					current.setX(i);
					current.setY(j);
					check1 = 1;
					break;
				}
				if (check1 == 1) {
					break;
				}
			}
		}
		if (check1 == 0) {
			throw new RuntimeException();
		}
		check1 = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 'S') {
					current.setX(i);
					current.setY(j);
					check1 = 1;
					break;
				}
				if (check1 == 1) {
					break;
				}
			}
		}
		if (check1 == 0) {
			throw new RuntimeException();
		}

		boolean[][] visited1 = new boolean
				[m.length][m[0].length];

		int i = current.getX();
		int j = current.getY();
		visited1[i][j] = true;
		MyStack sol = new MyStack();
		sol.push(current);
		while (m[i][j] != 'E'
				&& !sol.isEmpty()) {
			if (i + 1 < m.length
					&& !visited1[i + 1][j]
					&& m[i + 1][j] != '#') {

				i++;
				current = new Point();
				current.setX(i);
				current.setY(j);
				visited1[i][j] = true;
				sol.push(current);

			} else if (i - 1 >= 0
					&& !visited1[i - 1][j]
					&& m[i - 1][j] != '#') {

				i--;
				current = new Point();
				current.setX(i);
				current.setY(j);
				visited1[i][j] = true;
				sol.push(current);

			} else if (j + 1 < m[i].length
					&& !visited1[i][j + 1]
					&& m[i][j + 1] != '#') {

				j++;
				current = new Point();
				current.setX(i);
				current.setY(j);
				visited1[i][j] = true;
				sol.push(current);

			} else if (j - 1 >= 0 && !visited1[i][j - 1]
					&& m[i][j - 1] != '#') {

				j--;
				current = new Point();
				current.setX(i);
				current.setY(j);
				visited1[i][j] = true;
				sol.push(current);

			} else {

				sol.pop();

				current = new Point();
				if (!sol.isEmpty()) {
					current = (Point) sol.peek();
					i = current.getX();
					j = current.getY();
				}

			}
		}
		int r = sol.size();
		if (sol.isEmpty()) {
			return null;
		}
		int[][] routeD = new int[r][2];
		for (i = r - 1; i >= 0; i--) {
			Point current1 = new Point();
			current1 = (Point) sol.pop();
			// System.out.println(sol.pop());
			routeD[i][0] = current1.getX();
			routeD[i][1] = current1.getY();

		}

		return routeD;
	}
/**
 *
 * @param theMap
 * map
 * @return start
 */
	private Point getStart(final char[][] theMap) {
		for (int i = 0; i < length; i++) {
			for (int ii = 0; ii < width; ii++) {
				if (theMap[i][ii] == 'S') {
					return new Point(i, ii);

				}
			}
		}
		return null;
	}
/**
 *
 * @param theMap
 * map
 * @return end
 */
	private Point getEnd(final char[][] theMap) {
		for (int i = 0; i < length; i++) {
			for (int ii = 0; ii < width; ii++) {
				if (theMap[i][ii] == 'E') {
					return new Point(i, ii);

				}
			}
		}
		return null;
	}
/**
 *
 * @param point
 * current
 * @param map
 * maze
 */
	private void checkNeighborsBFS(final Point point,
			final char[][] map) {
		if (point.getX() + 1 < length) {
if (!visited[point.getX() + 1][point.getY()]) {
				if ((map[point.getX() + 1]
						[point.getY()] == '.')
				|| (map[point.getX() + 1]
						[point.getY()] == 'E')) {
					visited[point.getX() + 1]
							[point.getY()] = true;
queue.enqueue(new MazeNode(new Point(point.getX()
	+ 1, point.getY()), point));

				}
			}
		}
		if (point.getY() + 1 < width) {
			if (!visited[point.getX()][point.getY() + 1]) {
				if ((map[point.getX()]
						[point.getY() + 1] == '.')
				|| (map[point.getX()]
						[point.getY() + 1] == 'E')) {
					visited[point.getX()]
				[point.getY() + 1] = true;
queue.enqueue(new MazeNode(new Point(point.getX(),
				point.getY() + 1), point));

				}
			}
		}
		if (point.getX() - 1 >= 0) {
			if (!visited[point.getX() - 1][point.getY()]) {
				if ((map[point.getX() - 1][point.getY()] == '.')
	|| (map[point.getX() - 1][point.getY()] == 'E')) {
		visited[point.getX() - 1][point.getY()] = true;
		queue.enqueue(new MazeNode(new Point(point.getX() - 1,
					point.getY()), point));

				}
			}
		}
		if (point.getY() - 1 >= 0) {
			if (!visited[point.getX()][point.getY() - 1]) {
	if ((map[point.getX()][point.getY() - 1] == '.')
	|| (map[point.getX()][point.getY() - 1] == 'E')) {
	visited[point.getX()][point.getY() - 1] = true;
	queue.enqueue(new MazeNode(new Point(point.getX(),
	point.getY() - 1), point));

				}
			}
		}
	}
/**
 *
 * @param point
 * current
 * @param map
 * maze
 */
	private void checkNeighborsDFS(final Point point,
			final char[][] map) {
		check = false;
		if (point.getX() + 1 < length && !check) {
	if (!visited[point.getX() + 1][point.getY()]) {
	if ((map[point.getX() + 1][point.getY()] == '.')
	|| (map[point.getX() + 1][point.getY()] == 'E')) {
	check = true;
	visited[point.getX() + 1][point.getY()] = true;
	stack.push(new MazeNode(new Point(point.getX() + 1,
	point.getY()), point));

				}
			}
		}
		if (point.getY() + 1 < width && !check) {
if (!visited[point.getX()][point.getY() + 1]) {
	if ((map[point.getX()][point.getY() + 1] == '.')
|| (map[point.getX()][point.getY() + 1] == 'E')) {
check = true;
visited[point.getX()][point.getY() + 1] = true;
stack.push(new MazeNode(new Point(point.getX(),
point.getY() + 1), point));

				}
			}
		}
		if (point.getX() - 1 >= 0 && !check) {
			if (!visited[point.getX() - 1][point.getY()]) {
		if ((map[point.getX() - 1][point.getY()] == '.')
		|| (map[point.getX() - 1][point.getY()] == 'E')) {
	    check = true;
	    visited[point.getX() - 1][point.getY()] = true;
	    stack.push(new MazeNode(new Point(point.getX() - 1,
		point.getY()), point));

				}
			}
		}
		if (point.getY() - 1 >= 0 && !check) {
			if (!visited[point.getX()][point.getY() - 1]) {
			if ((map[point.getX()][point.getY() - 1] == '.')
			|| (map[point.getX()][point.getY() - 1] == 'E')) {
			check = true;
			visited[point.getX()][point.getY() - 1] = true;
			stack.push(new MazeNode(new Point(point.getX(),
			point.getY() - 1), point));

				}
			}
		}
	}
/**
 *
 * @param file
 * file to read
 */
	private void openFile(final File file) {
		try {
			sc = new Scanner(file);
		} catch (final Exception e) {
			System.out.println("no file, bruh");
		}
	}
/**
 *
 * @return point
 */
	private Point getSize() {
		if (sc.hasNext()) {
			return new Point(Integer.parseInt(sc.next()),
					Integer.parseInt(sc.next()));
		} else {
			System.out.println("error");
			return null;
		}
	}
/**
 *
 * @return array of characters
 */
	private char[][] getMap() {

		final char[][] maze = new char[length][width];
		int i = 0;
		while (sc.hasNext()) {
			final String line = sc.next();
			maze[i] = line.toCharArray();
			i++;
		}
		return maze;

	}
/**
 * @serialData close file
 */
	private void closeFile() {
		sc.close();
	}
/**
 *
 * @param f
 *  maze file
 * @return array of charachters
 */
	public char[][] readFile(final File f) {
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			int i = 0;
			int x = 0, y = 0;
			String line = new String();
			String store = new String();
			line = br.readLine();
			while (Character.isDigit(line.charAt(i))) {
			store += line.charAt(i);
			x = Integer.parseInt(store);
				i++;
			}
			i++;
			store = "";
			while (i < line.length()
	&&	Character.isDigit(line.charAt(i))) {
				store += line.charAt(i);
				y = Integer.parseInt(store);
				i++;
			}
			char[][] m = new char[x][y];
			i = 0;
			while ((line = br.readLine()) != null) {
				if (line.length() != y) {
					throw new RuntimeException();

				}
				m[i] = line.toCharArray();
				i++;

			}
			if (i != x) {
				throw new RuntimeException();
			}
			System.out.println(y);
			fr.close();
			br.close();
			return m;

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
