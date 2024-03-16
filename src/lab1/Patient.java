package lab1;


import java.util.Objects;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
public class Patient implements Comparable<Patient> {

    private String name;
    private int priority;

    public Patient(String name, int priority) {
        if(priority < 1 || priority > 4) {
            throw new IllegalArgumentException("Priority should be in range 1-4");
        }

        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Patient o) {
        return o.getPriority() - getPriority();
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return priority == patient.priority && Objects.equals(name, patient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }


    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
