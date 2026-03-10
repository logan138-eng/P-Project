/**
 * Enhanced interface for {@code MechanicalSystem}.
 *
 * <p>
 * This interface adds secondary operations to the kernel interface for
 * inspecting and evolving the state of a mechanical system.
 * </p>
 */
public interface MechanicalSystem extends MechanicalSystemKernel {

    /**
     * Reports the current acceleration of this system using Newton's second
     * law.
     *
     * @return the current acceleration vector
     * @requires this.mass > 0
     * @ensures acceleration = (sum of this.forces) / this.mass
     */
    Vector acceleration();

    /**
     * Advances this system forward by {@code deltaT} seconds.
     *
     * @param deltaT
     *            the time step
     * @updates this
     * @requires deltaT >= 0
     * @ensures this is advanced by one simulation step of duration deltaT
     */
    void step(double deltaT);

    /**
     * Reports the current kinetic energy of this system.
     *
     * @return the kinetic energy
     * @ensures kineticEnergy = (1/2) * this.mass * |this.velocity|^2
     */
    double kineticEnergy();

    /**
     * Reports whether this system is at rest.
     *
     * @return true if this system is considered at rest, false otherwise
     * @ensures isAtRest = (speed of this.velocity is sufficiently close to 0)
     */
    boolean isAtRest();

    /**
     * Reports the current position of this system.
     *
     * @return the current position
     * @ensures position = this.position
     */
    Vector getPosition();

    /**
     * Reports the current velocity of this system.
     *
     * @return the current velocity
     * @ensures velocity = this.velocity
     */
    Vector getVelocity();

}