/**
 * Simple immutable 2D vector type.
 */
public interface Vector {

    /**
     * Reports the x-coordinate.
     *
     * @return the x-coordinate
     * @ensures x = this.x-coordinate
     */
    double getX();

    /**
     * Reports the y-coordinate.
     *
     * @return the y-coordinate
     * @ensures y = this.y-coordinate
     */
    double getY();
}
