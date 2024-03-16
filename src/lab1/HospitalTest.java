package lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {

    @Test
    void addPatient_ShouldAddPatientToCorrectQueue() {
        Hospital hospital = new Hospital();
        Patient patient1 = new Patient("John Doe", 2);
        Patient patient2 = new Patient("Jane Smith", 1);

        hospital.addPatient(patient1);
        hospital.addPatient(patient2);

        assertEquals(patient2, hospital.getNextPatient());
    }

    @Test
    void removeNextPatientWithHighestPriority_ShouldRemoveAndReturnPatientWithHighestPriority() {
        Hospital hospital = new Hospital();
        Patient patient1 = new Patient("John Doe", 2);
        Patient patient2 = new Patient("Jane Smith", 1);
        Patient patient3 = new Patient("Alice Johnson", 3);

        hospital.addPatient(patient1);
        hospital.addPatient(patient2);
        hospital.addPatient(patient3);

        assertEquals(patient2, hospital.removeNextPatientWithHighestPriority());
        assertEquals(patient1, hospital.getNextPatient());
    }

    @Test
    void removeNextPatientWithHighestPriority_ShouldReturnNullIfQueueIsEmpty() {
        Hospital hospital = new Hospital();

        assertNull(hospital.removeNextPatientWithHighestPriority());
    }
}
