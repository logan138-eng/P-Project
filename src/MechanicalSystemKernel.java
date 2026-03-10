/**
 * Kernel interface for {@code MechanicalSystem}.
 *
 * <p>
 * A {@code MechanicalSystem} models a simplified mechanical system with mass,
 * position, velocity, and a collection of currently applied forces.
 * </p>
 *
 * @mathmodel type MechanicalSystemKernel is modeled by (mass: real, position:
 *            Vector, velocity: Vector, forces: finite multiset of Vector)
 *
 * @initially <pre>
 * mass = 1.0 and
 * position = (0.0, 0.0) and
 * velocity = (0.0, 0.0) and
 * forces = {}
 *            </pre> 你好
 */
public interface MechanicalSystemKernel {

    /**
     * Sets the mass of this system to {@code m}.
     *
     * @param m
     *            the new mass
     * @updates this
     * @requires m > 0
     * @ensures this.mass = m
     */
    void setMass(double m);

    /**
     * Sets the position of this system to {@code p}.
     *
     * @param p
     *            the new position
     * @updates this
     * @requires p != null
     * @ensures this.position = p
     */
    void setPosition(Vector p);

    /**
     * Sets the velocity of this system to {@code v}.
     *
     * @param v
     *            the new velocity
     * @updates this
     * @requires v != null
     * @ensures this.velocity = v
     */
    void setVelocity(Vector v);

    /**
     * Adds {@code f} to the collection of applied forces on this system.
     *
     * @param f
     *            the force to apply
     * @updates this
     * @requires f != null
     * @ensures f is added to this.forces
     */
    void applyForce(Vector f);

    /**
     * Clears all currently applied forces from this system.
     *
     * @updates this
     * @ensures this.forces = {}
     */
    void clearForces();

    /**
     * Resets this system to its initial state.
     *
     * @replaces this
     * @ensures this is in the initial state
     */
    void reset();
}