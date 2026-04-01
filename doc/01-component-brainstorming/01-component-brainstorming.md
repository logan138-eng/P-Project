# Portfolio Part 1: Component Brainstorming

- **Name**: Yuchen Wang
- **Dot Number**: wang.18748
- **Due Date**: 2/6/2026

## Pre-Assignment
I am Yuchen(Logan). I love studying math and physics. Never got any touch with computer knowledge before I was admitted to the
university. Then, I found learning about computer knowledge is as fun as studying math and physics. Ususally watched some youtube channel about fun math problems.
I started to play violin and soccer when I was admitted to university as well. My favorite composers are Rachmaninoff and Mahler, and favorite soccer club is Barcelona(Favorite played is Pedri). I have the curiosity to all most everything. So, it's hard for me to find one thing that I can focus for a long time, which I hope to improve.
### Component Designs

> Please use this section to share your designs.

- Component Design #1: MechanicalSystem
  - **Description**:
    - MechanicalSystem models a simplified mechanical system by maintaining physical state variables such as position, velocity, mass, and applied forces, and provides behavior that updates or queries the system based on classical mechanics relationships.
  - **Kernel Methods**:
    - void setMass(double m)

    - void setPosition(Vector p)

    - void setVelocity(Vector v)

    - void applyForce(Vector f)

    - void clearForces()

    - void reset()

  - **Secondary Methods**:
    - Vector acceleration()

    - void step(double deltaT)

    - double kineticEnergy()

    - boolean isAtRest()

  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):

    - Would this component be mutable? Answer and explain:
      - Yes. The mechanical state (position, velocity, forces) evolves over time.

    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - Yes. A Vector class may be used internally to represent physical quantities.

    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - Not required, though physical constants (e.g., gravity) could be optionally included.

    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - acceleration() can be computed using applyForce history and setMass.
        step(Δt) updates position and velocity using current state getters.

- Component Design #2: EnergyTracker
  - **Description**:
    - EnergyTracker models how energy is added, transferred, and consumed over time within a system, maintaining a current energy state and supporting controlled transformations.

  - **Kernel Methods**:
    - void addEnergy(double amount)

    - void consumeEnergy(double amount)

    - double currentEnergy()

    - void clear()

  - **Secondary Methods**:
    - boolean hasSufficientEnergy(double amount)

    - double efficiencyAfterLoss(double lossFactor)

    - boolean isDepleted()


  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):
    - Would this component be mutable? Answer and explain:
      - Yes. Energy level changes as energy is added or consumed.

    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - No.

    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - Optional constants for efficiency thresholds.

    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - isDepleted() is implemented as currentEnergy() == 0

- Component Design #3: MeasurementProcess
  - **Description**:
    - MeasurementProcess models the process of collecting, refining, and analyzing experimental measurements by maintaining a sequence of observed values and supporting    behaviors that derive statistical or physical insight from them.

  - **Kernel Methods**:
    - void addMeasurement(double value)

    - double removeAnyMeasurement()

    - int count()

    - void clear()

  - **Secondary Methods**:
    - double meanValue()

    - double maxDeviation()

    - boolean isStable(double tolerance)

    - double uncertaintyEstimate()

  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):
    - Would this component be mutable? Answer and explain:
      - Yes. Measurements accumulate and may be removed.

    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - No.

    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - No.

    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - meanValue() can be computed by repeatedly removing values and reinserting them.
