import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit tests for the secondary methods inherited from
 * MechanicalSystemSecondary.
 */
public final class MechanicalSystemSecondaryTest {

    /**
     * Tolerance for comparing doubles.
     */
    private static final double EPS = 1.0e-6;

    /**
     * Helper to compare two vectors by coordinates.
     *
     * @param expected
     *            expected vector
     * @param actual
     *            actual vector
     */
    private static void assertVectorEquals(Vector expected, Vector actual) {
        assertEquals(expected.getX(), actual.getX(), EPS);
        assertEquals(expected.getY(), actual.getY(), EPS);
    }

    @Test
    public void testAccelerationSimple() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(2.0);
        s.applyForce(new Vector2D(6.0, 4.0));

        Vector a = s.acceleration();

        assertVectorEquals(new Vector2D(3.0, 2.0), a);
        assertEquals(2.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
        assertVectorEquals(new Vector2D(6.0, 4.0), s.getNetForce());
    }

    @Test
    public void testAccelerationWithMultipleForces() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(4.0);
        s.applyForce(new Vector2D(10.0, -2.0));
        s.applyForce(new Vector2D(-2.0, 6.0));

        Vector a = s.acceleration();

        assertVectorEquals(new Vector2D(2.0, 1.0), a);
        assertVectorEquals(new Vector2D(8.0, 4.0), s.getNetForce());
    }

    @Test
    public void testStepWithZeroTime() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(2.0);
        s.setPosition(new Vector2D(1.0, 1.0));
        s.setVelocity(new Vector2D(3.0, -2.0));
        s.applyForce(new Vector2D(8.0, 4.0));

        s.step(0.0);

        assertEquals(2.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(1.0, 1.0), s.getPosition());
        assertVectorEquals(new Vector2D(3.0, -2.0), s.getVelocity());
        assertVectorEquals(new Vector2D(8.0, 4.0), s.getNetForce());
    }

    @Test
    public void testStepSimpleCase() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(2.0);
        s.setPosition(new Vector2D(0.0, 0.0));
        s.setVelocity(new Vector2D(1.0, 1.0));
        s.applyForce(new Vector2D(4.0, 2.0));

        /*
         * acceleration = (2,1) newVelocity = (1,1) + (2,1)*1 = (3,2)
         * newPosition = (0,0) + (3,2)*1 = (3,2)
         */
        s.step(1.0);

        assertEquals(2.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(3.0, 2.0), s.getVelocity());
        assertVectorEquals(new Vector2D(3.0, 2.0), s.getPosition());
        assertVectorEquals(new Vector2D(4.0, 2.0), s.getNetForce());
    }

    @Test
    public void testStepFractionalTime() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(2.0);
        s.setPosition(new Vector2D(1.0, 2.0));
        s.setVelocity(new Vector2D(2.0, 0.0));
        s.applyForce(new Vector2D(2.0, 4.0));

        /*
         * acceleration = (1,2) deltaT = 0.5 newVelocity = (2,0) + (0.5,1) =
         * (2.5,1) newPosition = (1,2) + (1.25,0.5) = (2.25,2.5)
         */
        s.step(0.5);

        assertVectorEquals(new Vector2D(2.5, 1.0), s.getVelocity());
        assertVectorEquals(new Vector2D(2.25, 2.5), s.getPosition());
        assertVectorEquals(new Vector2D(2.0, 4.0), s.getNetForce());
    }

    @Test
    public void testKineticEnergyAtRest() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(10.0);
        s.setVelocity(new Vector2D(0.0, 0.0));

        assertEquals(0.0, s.kineticEnergy(), EPS);
        assertTrue(s.isAtRest());
    }

    @Test
    public void testKineticEnergyMoving() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setMass(2.0);
        s.setVelocity(new Vector2D(3.0, 4.0));

        /*
         * |v|^2 = 3^2 + 4^2 = 25 KE = 0.5 * 2 * 25 = 25
         */
        assertEquals(25.0, s.kineticEnergy(), EPS);
        assertFalse(s.isAtRest());
    }

    @Test
    public void testIsAtRestVerySmallVelocity() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setVelocity(new Vector2D(1.0e-7, -1.0e-7));

        assertTrue(s.isAtRest());
    }

    @Test
    public void testIsAtRestNonzeroVelocity() {
        MechanicalSystem s = new MechanicalSystem1();
        s.setVelocity(new Vector2D(1.0e-3, 0.0));

        assertFalse(s.isAtRest());
    }
}