import java.util.ArrayList;
import java.util.List;

/**
 * Proof-of-possibility prototype for the MechanicalSystem component design.
 *
 * This is intentionally a single-file implementation (no interfaces/abstract
 * hierarchy) to match the assignment directions.
 */
public class MechanicalSystemDemo {

    /**
     * Simple 2D vector for position/velocity/force. (Not java.util.Vector; this
     * is a math/physics vector.)
     */
    private static final class Vector {
        private final double x;
        private final double y;

        Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return this.x;
        }

        double getY() {
            return this.y;
        }

        Vector add(Vector other) {
            return new Vector(this.x + other.x, this.y + other.y);
        }

        Vector subtract(Vector other) {
            return new Vector(this.x - other.x, this.y - other.y);
        }

        Vector scale(double k) {
            return new Vector(this.x * k, this.y * k);
        }

        double magnitude() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    /**
     * MechanicalSystem models a simplified mechanical system that maintains
     * state variables such as position, velocity, mass, and applied forces.
     *
     * Kernel methods (from your design): - setMass, setPosition, setVelocity,
     * applyForce, clearForces, reset
     *
     * Secondary methods (from your design): - acceleration, step,
     * kineticEnergy, isAtRest
     */
    private static final class MechanicalSystem {
        // ---------------- Representation (example fields) ----------------
        private double mass;
        private Vector position;
        private Vector velocity;
        private final List<Vector> forces; // stores applied forces since last clear

        MechanicalSystem() {
            this.forces = new ArrayList<>();
            this.reset();
        }

        // ---------------- Kernel Methods ----------------

        void setMass(double m) {
            this.mass = m;
        }

        void setPosition(Vector p) {
            this.position = p;
        }

        void setVelocity(Vector v) {
            this.velocity = v;
        }

        void applyForce(Vector f) {
            this.forces.add(f);
        }

        void clearForces() {
            this.forces.clear();
        }

        void reset() {
            this.mass = 1.0;
            this.position = new Vector(0.0, 0.0);
            this.velocity = new Vector(0.0, 0.0);
            this.forces.clear();
        }

        // ---------------- Secondary Methods ----------------

        /**
         * Computes acceleration using Newton's 2nd law: a = F_total / m.
         */
        Vector acceleration() {
            Vector total = new Vector(0.0, 0.0);
            for (Vector f : this.forces) {
                total = total.add(f);
            }
            return total.scale(1.0 / this.mass);
        }

        /**
         * Advances the simulation by deltaT seconds using a simple Euler step:
         * v = v + a * dt x = x + v * dt
         */
        void step(double deltaT) {
            Vector a = this.acceleration();
            this.velocity = this.velocity.add(a.scale(deltaT));
            this.position = this.position.add(this.velocity.scale(deltaT));
        }

        /**
         * Kinetic energy: (1/2) m |v|^2.
         */
        double kineticEnergy() {
            double speed = this.velocity.magnitude();
            return 0.5 * this.mass * speed * speed;
        }

        /**
         * Returns true if velocity magnitude is below a small threshold.
         */
        boolean isAtRest() {
            double eps = 1e-9;
            return this.velocity.magnitude() < eps;
        }

        // Extra helpers just for printing in demo (not required by your design).
        Vector getPosition() {
            return this.position;
        }

        Vector getVelocity() {
            return this.velocity;
        }
    }

    /**
     * Main method showing the component in action.
     */
    public static void main(String[] args) {
        MechanicalSystem sys = new MechanicalSystem();

        // Configure initial state
        sys.setMass(2.0);
        sys.setPosition(new Vector(0.0, 0.0));
        sys.setVelocity(new Vector(0.0, 0.0));

        // Apply a constant force to the right: F = (4, 0) Newtons
        sys.applyForce(new Vector(4.0, 0.0));

        double dt = 0.5; // seconds
        for (int i = 1; i <= 6; i++) {
            sys.step(dt);
            System.out.println("Step " + i);
            System.out.println("  position = " + sys.getPosition());
            System.out.println("  velocity = " + sys.getVelocity());
            System.out.println("  accel    = " + sys.acceleration());
            System.out.println("  KE       = " + sys.kineticEnergy());
            System.out.println("  atRest?  = " + sys.isAtRest());
            System.out.println();
        }

        // Clear forces and see that acceleration becomes zero
        sys.clearForces();
        System.out
                .println("After clearForces(): accel = " + sys.acceleration());

        // Reset demo
        sys.reset();
        System.out.println("After reset(): position = " + sys.getPosition()
                + ", velocity = " + sys.getVelocity());
    }
}