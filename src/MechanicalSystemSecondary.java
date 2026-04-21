/**
 * Implementation of MechanicalSystem
 */
public abstract class MechanicalSystemSecondary implements MechanicalSystem {

    /**
     * Tolerance for deciding whether the system is at rest.
     */
    private static final double REST_EPSILON = 1.0e-6;

    /**
     * Returns the sum of two vectors.
     *
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return v1 + v2
     * @requires v1 != null and v2 != null
     * @ensures add = v1 + v2
     */
    private static Vector add(Vector v1, Vector v2) {
        return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Returns the vector v scaled by scalar s.
     *
     * @param v
     *            the vector
     * @param s
     *            the scale factor
     * @return s * v
     * @requires v != null
     * @ensures scale = s * v
     */
    private static Vector scale(Vector v, double s) {
        return new Vector2D(v.getX() * s, v.getY() * s);
    }

    /**
     * Returns the squared magnitude of a vector.
     *
     * @param v
     *            the vector
     * @return |v|^2
     * @requires v != null
     * @ensures magnitudeSquared = (v.x)^2 + (v.y)^2
     */
    private static double magnitudeSquared(Vector v) {
        return v.getX() * v.getX() + v.getY() * v.getY();
    }

    /**
     * Returns the magnitude of a vector.
     *
     * @param v
     *            the vector
     * @return |v|
     * @requires v != null
     * @ensures magnitude = sqrt((v.x)^2 + (v.y)^2)
     */
    private static double magnitude(Vector v) {
        return Math.sqrt(magnitudeSquared(v));
    }

    @Override
    public Vector acceleration() {
        Vector netForce = this.getNetForce();
        double mass = this.getMass();
        return scale(netForce, 1.0 / mass);
    }

    @Override
    public void step(double deltaT) {
        Vector acceleration = this.acceleration();
        Vector oldVelocity = this.getVelocity();
        Vector oldPosition = this.getPosition();

        Vector newVelocity = add(oldVelocity, scale(acceleration, deltaT));
        Vector newPosition = add(oldPosition, scale(newVelocity, deltaT));

        this.setVelocity(newVelocity);
        this.setPosition(newPosition);
    }

    @Override
    public double kineticEnergy() {
        double speedSquared = magnitudeSquared(this.getVelocity());
        return 0.5 * this.getMass() * speedSquared;
    }

    @Override
    public boolean isAtRest() {
        return magnitude(this.getVelocity()) < REST_EPSILON;
    }

    // TODO: add equals, toString, hashCode

   @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MechanicalSystem)) {
            return false;
        }
        MechanicalSystem other = (MechanicalSystem) o;

        return this.getMass() == other.getMass()
                && this.getPosition().getX() == other.getPosition().getX()
                && this.getPosition().getY() == other.getPosition().getY()
                && this.getVelocity().getX() == other.getVelocity().getX()
                && this.getVelocity().getY() == other.getVelocity().getY()
                && this.getNetForce().getX() == other.getNetForce().getX()
                && this.getNetForce().getY() == other.getNetForce().getY();
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(this.getMass());
        result = 31 * result + Double.hashCode(this.getPosition().getX());
        result = 31 * result + Double.hashCode(this.getPosition().getY());
        result = 31 * result + Double.hashCode(this.getVelocity().getX());
        result = 31 * result + Double.hashCode(this.getVelocity().getY());
        result = 31 * result + Double.hashCode(this.getNetForce().getX());
        result = 31 * result + Double.hashCode(this.getNetForce().getY());
        return result;
    }

    @Override
    public String toString() {
        return "MechanicalSystem1[mass=" + this.getMass() + ", position="
                + this.getPosition() + ", velocity=" + this.getVelocity() + ", netForce="
                + this.getNetForce() + "]";
    }

}