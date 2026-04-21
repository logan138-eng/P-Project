/**
 * Kernel implementation for {@code MechanicalSystem}.
 *
 * @convention <pre>
 * $this.mass > 0 and
 * $this.position != null and
 * $this.velocity != null and
 * $this.netForce != null
 * </pre>
 *
 * @correspondence <pre>
 * this = (mass, position, velocity, forces), where
 * mass = $this.mass,
 * position = $this.position,
 * velocity = $this.velocity, and
 * sum(forces) = $this.netForce
 * </pre>
 */
public final class MechanicalSystem1 extends MechanicalSystemSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Mass of this system.
     */
    private double mass;

    /**
     * Position of this system.
     */
    private Vector position;

    /**
     * Velocity of this system.
     */
    private Vector velocity;

    /**
     * Net force currently applied to this system.
     */
    private Vector netForce;

    /**
     * Creates a fresh initial representation.
     */
    private void createNewRep() {
        this.mass = 1.0;
        this.position = new Vector2D(0.0, 0.0);
        this.velocity = new Vector2D(0.0, 0.0);
        this.netForce = new Vector2D(0.0, 0.0);
    }

    /**
     * Returns the sum of two vectors.
     *
     * @param v1
     *            the first vector
     * @param v2
     *            the second vector
     * @return v1 + v2
     * @requires v1 != null and v2 != null
     * @ensures add = v1 + v2
     */
    private static Vector add(Vector v1, Vector v2) {
        return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     *
     * @ensures this is in the initial state
     */
    public MechanicalSystem1() {
        this.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public void setMass(double m) {
        assert m > 0 : "Violation of: m > 0";
        this.mass = m;
    }

    @Override
    public void setPosition(Vector p) {
        assert p != null : "Violation of: p != null";
        this.position = p;
    }

    @Override
    public void setVelocity(Vector v) {
        assert v != null : "Violation of: v != null";
        this.velocity = v;
    }

    @Override
    public void applyForce(Vector f) {
        assert f != null : "Violation of: f != null";
        this.netForce = add(this.netForce, f);
    }

    @Override
    public void clearForces() {
        this.netForce = new Vector2D(0.0, 0.0);
    }

    @Override
    public void reset() {
        this.createNewRep();
    }

    @Override
    public double getMass() {
        return this.mass;
    }

    @Override
    public Vector getPosition() {
        return this.position;
    }

    @Override
    public Vector getVelocity() {
        return this.velocity;
    }

    @Override
    public Vector getNetForce() {
        return this.netForce;
    }

    // TODO: move these to secondary

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MechanicalSystem1)) {
            return false;
        }
        MechanicalSystem1 other = (MechanicalSystem1) o;

        return this.mass == other.mass
                && this.position.getX() == other.position.getX()
                && this.position.getY() == other.position.getY()
                && this.velocity.getX() == other.velocity.getX()
                && this.velocity.getY() == other.velocity.getY()
                && this.netForce.getX() == other.netForce.getX()
                && this.netForce.getY() == other.netForce.getY();
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(this.mass);
        result = 31 * result + Double.hashCode(this.position.getX());
        result = 31 * result + Double.hashCode(this.position.getY());
        result = 31 * result + Double.hashCode(this.velocity.getX());
        result = 31 * result + Double.hashCode(this.velocity.getY());
        result = 31 * result + Double.hashCode(this.netForce.getX());
        result = 31 * result + Double.hashCode(this.netForce.getY());
        return result;
    }

    @Override
    public String toString() {
        return "MechanicalSystem1[mass=" + this.mass + ", position="
                + this.position + ", velocity=" + this.velocity + ", netForce="
                + this.netForce + "]";
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public MechanicalSystem newInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'newInstance'");
    }

    @Override
    public void transferFrom(MechanicalSystem arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'transferFrom'");
    }

}
