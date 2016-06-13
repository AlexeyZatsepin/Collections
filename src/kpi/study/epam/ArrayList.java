package kpi.study.epam;

import java.util.*;

/**
 * EPAM_Collections
 * Created 6/2/16, with IntelliJ IDEA
 *
 * @author Alex
 */
public class ArrayList<T> implements List<T>{
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;
    private int size;
    private int modCount = 0;

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= DEFAULT_CAPACITY) {
            this.list = new Object[initialCapacity];
        } else if (initialCapacity > 0) {
            this.list = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        this.list = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - list.length > 0) {
            growAwesomeList(minCapacity);
        }
    }

    private void growAwesomeList(int minCapacity) {
        int oldCapacity = list.length;
        int newCapacity = oldCapacity * 2;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        list = Arrays.copyOf(list, newCapacity);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity(size + 1);
        list[size++] = o;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                System.arraycopy(list, i + 1, list, i, size - i - 1);
                list[--size] = null;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        return (T) list[index];
    }

    @Override
    public T set(int index, T o) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        list[index] = o;
        modCount++;
        return (T) list[index];
    }

    @Override
    public void add(int index, T o) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        ensureCapacity(size + 1);
        int toAdd = size - index;
        System.arraycopy(list, index, list, index + 1, toAdd);
        list[index] = o;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("No such index in this list. Size: " + size + ".");
        }
        int toRemove = size - index - 1;
        T t = (T) list[index];
        if (toRemove > 0) {
            System.arraycopy(list, index + 1, list, index, toRemove);
        }
        list[--size] = null;
        modCount++;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>(){
            int index = 0;
            int modCount = ArrayList.this.modCount;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                checkMods();
                return (T) list[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                checkMods();
                return (T) list[--index];
            }

            @Override
            public int nextIndex() {
                checkMods();
                return index + 1;
            }

            @Override
            public int previousIndex() {
                checkMods();
                return index - 1;
            }

            @Override
            public void remove() {
                checkMods();
                ArrayList.this.remove(index--);
                modCount++;
            }

            @Override
            public void set(T t) {
                checkMods();
                ArrayList.this.set(index, t);
                modCount++;
            }

            @Override
            public void add(T t) {
                checkMods();
                ArrayList.this.add(index, t);
                modCount++;
            }

            private void checkMods() {
                if (modCount != ArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public T[] toArray(Object[] a) {
        return (T[]) new Object[0];
    }
}
