package eg.edu.alexu.csd.datastructure.maze.cs04_cs06_cs19;
/**
 *
 * @author 3abdala
 *
 */
public class Point {
/**
 * @serialField x,y
 */
	private int x, y;
/**
 *
 * @return x
 */
	public int getX() {
		return x;
	}
/**
 *
 * @return y
 */
	public int getY() {
		return y;
	}
/**
 *
 * @param xx
 * xx
 */
	public void setX(final int xx) {
		x = xx;
	}
/**
 *
 * @param yy
 * yy
 */
	public void setY(final int yy) {
		y = yy;
	}
/**
 * @serialData constructor
 */
	public Point() {
	}
/**
 *
 * @param xxx
 * xx
 * @param yyy
 * yy
 */
	public Point(final int xxx, final int yyy) {
		x = xxx;
		y = yyy;
	}
}
