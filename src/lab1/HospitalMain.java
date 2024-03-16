package lab1;

public class HospitalMain {
    public static void main(String[] args){

        Hospital hospital = new Hospital();

        Patient pacjent1 = new Patient("Kasia", 4);
        Patient pacjent2 = new Patient("Asia", 2);
        Patient pacjent3 = new Patient("Basia", 1);
        Patient pacjent4 = new Patient("Jan", 3);
        Patient pacjent5 = new Patient("Paweł", 1);

        hospital.addPatient(pacjent1);
        hospital.addPatient(pacjent2);
        hospital.addPatient(pacjent3);
        hospital.addPatient(pacjent4);
        hospital.addPatient(pacjent5);

        System.out.println(hospital);

        hospital.removeNextPatientWithHighestPriority();

        System.out.println(hospital);
    }
}
