package lab1;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */

public class HospitalMain {
    public static void main(String[] args) {

        Hospital hospital = new Hospital();

        Patient pacjent1 = new Patient("Kasia", 4);
        Patient pacjent2 = new Patient("Asia", 2);
        Patient pacjent3 = new Patient("Basia", 1);
        Patient pacjent4 = new Patient("Jan", 1);
        Patient pacjent5 = new Patient("Paweł", 1);
        Patient pacjent6 = new Patient("Bartosz", 3);
        Patient pacjent7 = new Patient("Jakub", 1);
        Patient pacjent8 = new Patient("Katarzyna", 1);
        hospital.addPatient(pacjent1);
        hospital.addPatient(pacjent2);
        hospital.addPatient(pacjent3);
        hospital.addPatient(pacjent4);
        hospital.addPatient(pacjent5);
        hospital.addPatient(pacjent6);
        hospital.addPatient(pacjent7);
        hospital.addPatient(pacjent8);
        System.out.println(hospital);

        hospital.removeNextPatientWithHighestPriority();

        System.out.println(hospital);
    }
}
