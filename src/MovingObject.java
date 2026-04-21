public class MovingObject {

    private final MechanicalSystem system;

    public MovingObject(double mass) {
        this.system = new MechanicalSystem1();
        this.system.setMass(mass);
    }

    public void push(double fx, double fy) {
        this.system.applyForce(new Vector2D(fx, fy));
    }

    public void update(double dt) {
        this.system.step(dt);
    }

    public Vector getPosition() {
        return this.system.getPosition();
    }
}