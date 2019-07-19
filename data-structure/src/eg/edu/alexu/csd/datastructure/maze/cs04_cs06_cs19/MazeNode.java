package eg.edu.alexu.csd.datastructure.maze.cs04_cs06_cs19;
/**
 *
 * @author abo el zanazen
 *
 */
public class MazeNode {
/**
 * @serialField me
 */
	private Point me;
	/**
	 * @serialField parent
	 */
	private Point papa;
/**
 *
 * @param current
 * me
 * @param parent
 * papa
 * @return
 */
	public  MazeNode(final Point current, final Point parent) {
		this.me = current;
		this.papa = parent;
	}
/**
 *
 * @return me
 *  now
 */
	public Point getCurrent() {
		return this.me;
	}
/**
 *
 * @return papa
 * now
 */
	public Point getParent() {
		return this.papa;
	}
}
