package lab1;

/**
 * Kolejka prioretytowa zaimplementowana na kopcu binarnym, gdzie pierwszy jest element maksymalny
 * @param <T> typ implementujący interfejs Comparable
 *
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
public class MaxHeapPriorityQueue<T extends Comparable<T>> extends MinHeapPriorityQueue<T> {
    @Override
    protected void moveUp(int index) {
        while(index > 0) {
            int parentIndex = (index - 1) / 2;
            if(queue.get(index).compareTo(queue.get(parentIndex)) > 0) {
                T temp = queue.get(index);
                queue.set(index, queue.get(parentIndex));
                queue.set(parentIndex, temp);

                index = parentIndex;
            } else break;
        }
    }

    @Override
    protected void moveDown(int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int newIndex = index;

        if(leftIndex < size() && queue.get(leftIndex).compareTo(queue.get(newIndex)) > 0) newIndex = leftIndex;
        if(rightIndex < size() && queue.get(rightIndex).compareTo(queue.get(newIndex)) > 0) newIndex = rightIndex;

        if(newIndex != index) {
            T temp = queue.get(index);
            queue.set(index, queue.get(newIndex));
            queue.set(newIndex, temp);
            moveDown(newIndex);
        }
    }
}
