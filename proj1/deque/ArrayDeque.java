package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int scope;
    private int size;
    private int head;
    private int tail;
    private T[] ontology;

    public ArrayDeque() {
        ontology = (T[]) new Object[8];
        scope = 8;
        size = 0;
        head = 0;
        tail = 0;
    }

    public ArrayDeque(int i) {
        ontology = (T[]) new Object[i];
        scope = i;
        size = 0;
        head = 0;
        tail = 0;
    }

    private class DequeIterator implements Iterator<T> {
        private int wizpos;

        DequeIterator() {
            wizpos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizpos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int realIndex = (head + wizpos) % scope;
            T theItem = ontology[realIndex];
            wizpos++;
            return theItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private void ontologyCopy(int newScope) {
        T[] newOntology = (T[]) new Object[newScope];
        if (head < tail) {
            arraycopy(ontology, head, newOntology, 0, tail - head);
        } else {
            arraycopy(ontology, head, newOntology, 0, scope - head);
            arraycopy(ontology, 0, newOntology, scope - head, tail);
        }
        head = 0;
        tail = size;
        scope = newScope;
        ontology = newOntology;
    }

    private void expansion() {
        if (size == scope) {
            int newScope = scope * 2;
            ontologyCopy(newScope);
        }
    }

    private void cutback() {
        if (scope >= 16 && size * 4 <= scope) {
            int newScope = Math.max(8, scope / 2);
            ontologyCopy(newScope);
        }

    }

    @Override
    public void addFirst(T item) {
        expansion();
        size++;
        head = (head - 1 + scope) % scope;
        ontology[head] = item;
    }

    @Override
    public void addLast(T item) {
        expansion();
        size++;
        ontology[tail] = item;
        tail = (tail + 1 + scope) % scope;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T theItem : this) {
            System.out.print(theItem + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        T x = ontology[head];
        head = (head + 1 + scope) % scope;
        cutback();
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        tail = (tail - 1 + scope) % scope;
        T neediness = ontology[tail];
        cutback();
        return neediness;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = (head + index) % scope;
        return ontology[realIndex];
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }

        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (this.size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
