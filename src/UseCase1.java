public class UseCase1 {
    public static void main(String[] args) {

        MechanicalSystem system = new MechanicalSystem1();

        system.setMass(2.0);
        system.applyForce(new Vector2D(4.0, 0.0));

        System.out.println("Acceleration: " + system.acceleration());

        system.step(1.0);

        System.out.println("Position: " + system.getPosition());
        System.out.println("Velocity: " + system.getVelocity());
    }
}