import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit tests for the kernel methods in MechanicalSystem1.
 */
public final class MechanicalSystem1Test {

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
    public void testConstructorInitialState() {
        MechanicalSystem s = new MechanicalSystem1();

        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getNetForce());
    }

    @Test
    public void testSetMass() {
        MechanicalSystem s = new MechanicalSystem1();

        s.setMass(5.5);

        assertEquals(5.5, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getNetForce());
    }

    @Test
    public void testSetPosition() {
        MechanicalSystem s = new MechanicalSystem1();
        Vector p = new Vector2D(3.0, -2.0);

        s.setPosition(p);

        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(3.0, -2.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getNetForce());
    }

    @Test
    public void testSetVelocity() {
        MechanicalSystem s = new MechanicalSystem1();
        Vector v = new Vector2D(-1.5, 4.0);

        s.setVelocity(v);

        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(-1.5, 4.0), s.getVelocity());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getNetForce());
    }

    @Test
    public void testApplyForceOnce() {
        MechanicalSystem s = new MechanicalSystem1();
        Vector f = new Vector2D(2.0, 3.0);

        s.applyForce(f);

        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
        assertVectorEquals(new Vector2D(2.0, 3.0), s.getNetForce());
    }

    @Test
    public void testApplyForceTwiceAccumulates() {
        MechanicalSystem s = new MechanicalSystem1();

        s.applyForce(new Vector2D(2.0, 3.0));
        s.applyForce(new Vector2D(-1.0, 4.0));

        assertVectorEquals(new Vector2D(1.0, 7.0), s.getNetForce());
        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
    }

    @Test
    public void testClearForces() {
        MechanicalSystem s = new MechanicalSystem1();

        s.applyForce(new Vector2D(7.0, -2.0));
        s.clearForces();

        assertVectorEquals(new Vector2D(0.0, 0.0), s.getNetForce());
        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
    }

    @Test
    public void testReset() {
        MechanicalSystem s = new MechanicalSystem1();

        s.setMass(9.0);
        s.setPosition(new Vector2D(1.0, 2.0));
        s.setVelocity(new Vector2D(3.0, 4.0));
        s.applyForce(new Vector2D(5.0, 6.0));

        s.reset();

        assertEquals(1.0, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getPosition());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getVelocity());
        assertVectorEquals(new Vector2D(0.0, 0.0), s.getNetForce());
    }

    @Test
    public void testGettersAfterSeveralKernelUpdates() {
        MechanicalSystem s = new MechanicalSystem1();

        s.setMass(2.5);
        s.setPosition(new Vector2D(8.0, -1.0));
        s.setVelocity(new Vector2D(-3.0, 6.0));
        s.applyForce(new Vector2D(4.5, 2.5));
        s.applyForce(new Vector2D(0.5, -1.5));

        assertEquals(2.5, s.getMass(), EPS);
        assertVectorEquals(new Vector2D(8.0, -1.0), s.getPosition());
        assertVectorEquals(new Vector2D(-3.0, 6.0), s.getVelocity());
        assertVectorEquals(new Vector2D(5.0, 1.0), s.getNetForce());
    }
}