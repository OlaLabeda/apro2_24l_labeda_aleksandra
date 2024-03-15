package lab1;


import java.util.LinkedList;

public class Hospital {
    private LinkedList<Patient> priorityQueue = new LinkedList<>();

    public static void main(String[] args){
        Hospital hospital = new Hospital();
        System.out.println(hospital.getpriorityQueueSize());
        Patient pacjent = new Patient("Alice", 1);
        hospital.addPatient(pacjent);
        pacjent = hospital.getNextPatient();
        if(pacjent != null){
            System.out.println(pacjent.getName()+" "+pacjent.getPriority());
        }
        else{
            System.out.println("Empty queue");
        }
    }
    public void addPatient(Patient patient) {
        for (int i = 0; i < priorityQueue.size(); i++) {
            Patient patient1 = priorityQueue.get(i);
            System.out.print(patient.getName()+" "+patient.getPriority()+": ");
            System.out.println(patient.compareTo(patient1));
            if (patient.compareTo(patient1) > 0) {
                priorityQueue.add(i, patient);
                return;
            }
        }
        priorityQueue.add(patient);
    }

    public int getpriorityQueueSize() {
        return priorityQueue.size();
    }

    public Patient getNextPatient() {
        if (getpriorityQueueSize()!=0) {
            return priorityQueue.poll();
        } else {
            return priorityQueue.peek();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hospital Queue:\n");
        priorityQueue.forEach(
                patient -> sb.append("Name: ").append(patient.getName()).append(", ").append("Priority: ").append(patient.getPriority()).append("\n"));
        return sb.toString();
    }
}
