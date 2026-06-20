package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private static class AllNode<T> {
        private AllNode<T> prev;
        private final T item;
        private AllNode<T> next;

        AllNode(AllNode<T> p, T i, AllNode<T> n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private final AllNode<T> sentinel;
    private int size;

    private class DequeIterator implements Iterator<T> {
        private AllNode<T> current;

        DequeIterator() {
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T res = current.item;
            current = current.next;
            return res;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    public LinkedListDeque() {
        sentinel = new AllNode<T>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        AllNode<T> p = sentinel.next;
        sentinel.next = new AllNode<>(sentinel, item, p);
        p.prev = sentinel.next;
    }

    @Override
    public void addLast(T item) {
        size++;
        AllNode<T> p = sentinel.prev;
        sentinel.prev = new AllNode<>(p, item, sentinel);
        p.next = sentinel.prev;
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
        AllNode<T> p = sentinel.next;
        T x = p.item;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        AllNode<T> p = sentinel.prev;
        T y = p.item;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        return y;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        AllNode<T> p;
        if (index <= size / 2) {
            p = sentinel.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = sentinel.prev;
            int reverseIdx = size - 1 - index;
            for (int i = 0; i < reverseIdx; i++) {
                p = p.prev;
            }
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return recurseHelper(sentinel.next, index);
    }

    private T recurseHelper(AllNode<T> cur, int idx) {
        if (idx == 0) {
            return cur.item;
        }
        return recurseHelper(cur.next, idx - 1);
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }

        Deque<?> other = (Deque<?>) o;
        if (this.size != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            Object thisItem = this.get(i);
            Object otherItem = other.get(i);

            if (thisItem == null) {
                if (otherItem != null) {
                    return false;
                }
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }

}
