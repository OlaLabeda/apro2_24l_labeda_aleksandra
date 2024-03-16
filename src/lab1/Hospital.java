package lab1;


import java.util.LinkedList;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
class Hospital {
    private final LinkedList<Patient>[] priorityQueues;

    /**
     * Konstruktor tworzy 4 kolejki priorytetowe -
     * kazda dla kazdefo priorytetu
     */
    public Hospital() {
        this.priorityQueues = new LinkedList[4];
        for (int i = 0; i < 4; i++) {
            priorityQueues[i] = new LinkedList<>();
        }
    }

    /**
     * Metoda dodaje pacjenta do kolejki
     *
     * @param patient
     */
    public void addPatient(Patient patient) {
        priorityQueues[patient.getPriority() - 1].add(patient);
    }

    /**
     * Metoda usuwa pacjenta o najwyższym priorytecie z kolejki
     *
     * @return
     */

    public Patient removeNextPatientWithHighestPriority() {
        for (LinkedList<Patient> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                //zwraca element ze szczytu kolejki i usuwa pacjenta o najwyższym priorytecie
                return queue.poll();
            }
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Kolejka pacjentów w SOR:\n");
        for (LinkedList<Patient> queue : priorityQueues) {
            for (Patient patient : queue) {
                sb.append(patient.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Metoda zwraca pacjenta o najwyższym priorytecie
     *
     * @return
     */
    public Patient getNextPatient() {
        for (LinkedList<Patient> queue : priorityQueues) {
            if (!queue.isEmpty()) {
                //zwraca element ze szczytu kolejki bez usuwania go
                return queue.peek();
            }
        }
        return null;
    }
}