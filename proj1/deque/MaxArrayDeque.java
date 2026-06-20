package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> storedComparator;

    public MaxArrayDeque(Comparator<T> c) {
        storedComparator = c;
    }

    public T max() {
        if (this.size() <= 0) {
            return null;
        }

        T maxItem = get(0);

        for (T item : this) {
            if (storedComparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (this.size() <= 0) {
            return null;
        }

        T maxItem = get(0);

        for (T item : this) {
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
