package kpi.study.epam;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;

/**
 * EPAM_Collections
 * Created 6/3/16, with IntelliJ IDEA
 *
 * @author Alex
 */
public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int modCount;
    private int size;

    private class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(){}

        public Node(E data) {
            this.data = data;
        }
    }

    public T getFirst() {
        return (T) first.data;
    }

    public T getLast() {
        return (T) last.data;
    }

    public void addFirst(T element) {
        modCount++;
        if (first == null) {
            first = new Node<>(element);
            last = first;
            size++;
            return;
        }
        Node node = new Node<>(element);
        first.prev = node;
        node.next = first;
        first = node;
        size++;
    }

    public boolean add(T element) {
        modCount++;
        if (first == null) {
            first = new Node<>(element);
            last = first;
            size++;
            return true;
        }
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(element);
        last = temp.next;
        last.prev = temp;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size()) {
            add(element);
            return;
        }
        modCount++;
        Node temp = getNode(index);

        Node node = new Node<>(element);
        temp.next.prev = node;
        node.next = temp.next;
        node.prev = temp;
        temp.next = node;
        size++;
    }

    @Override
    public T remove(int index) {
        return null;
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
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public T get(int index) {
        checkIndex(index);
        Node temp = getNode(index);
        return (T) temp.data;
    }

    private Node getNode(int index) {
        int i;
        Node temp;
        if (index < size()/2) {
            i = 0;
            temp = first;
            while (i != index) {
                temp = temp.next;
                i++;
            }
            //* return (T) temp.data;
        } else {    // index >= size() / 2
            i = size() - 1;
            temp = last;
            while (i != index) {
                temp = temp.prev;
                i--;
            }

        }
        return temp;
    }

    public T set(int index, T value) {
        checkIndex(index);
        modCount++;
        Node temp = getNode(index);
        temp.data = value;
        return value;
    }

    public T removeFirst() {
        if (first == null) {
            throw new UnsupportedOperationException("The list is empty");
        } else if (last == first) {
            return removeFirstIsLast();
        }
        modCount++;
        Node temp = first;
        first = first.next;
        first.prev = null;
        size--;
        return (T) temp.data;
    }

    public T removeLast() {
        if (first == null && last == null) {
            throw new UnsupportedOperationException("The list is empty");
        } else if (last == first) {
            return removeFirstIsLast();
        }
        modCount++;
        Node temp = last;
        last = last.prev;
        last.next = null;
        size--;
        return (T) temp.data;
    }

    private T removeFirstIsLast() {
        size--;
        Node temp = first;
        first = last = null;
        return (T) temp.data;
    }

    @Override
    public boolean contains(Object element) {
        Node temp = first;
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void print() {
        Node temp = first;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public ListIterator<T> iterator() {
        return new ListIterator<T>() {
            int modCount = 0;
            Node<T> current = first;
            int currentIndex = 0;
            Node<T> justReturned = null;

            @Override
            public boolean hasNext() {
                return  (current != null && current.next != null);
            }

            @Override
            public T next() {
                justReturned = current;
                current = current.next;
                currentIndex++;

                return (T) justReturned.data;
            }

            @Override
            public boolean hasPrevious() {
                return (current != null && current.prev != null);
            }

            @Override
            public T previous() {
                justReturned = current;
                current = current.prev;
                currentIndex--;
                return justReturned.data;
            }

            @Override
            public int nextIndex() {
                return currentIndex++;
            }

            @Override
            public int previousIndex() {
                return currentIndex--;
            }

            @Override
            public void remove() {
                checkMods();
                modCount++;
                if (justReturned == null) {
                    throw new IndexOutOfBoundsException("Just Returned is null");
                }
                if (justReturned == first) {
                    LinkedList.this.removeFirst();
                } else if (justReturned == last) {
                    LinkedList.this.removeLast();
                } else {
                    justReturned.next.prev = justReturned.prev;
                    justReturned.prev.next = justReturned.next;
                    justReturned = null;
                }
            }

            @Override
            public void set(T t) {
                checkMods();
                modCount++;
                justReturned.data = t;
            }

            @Override
            public void add(T t) {
                checkMods();
                modCount++;
                if (justReturned == first) {
                    LinkedList.this.addFirst(t);
                } else if (justReturned == last) {
                    LinkedList.this.add(t);
                } else {
                    Node node = new Node<>(t);
                    justReturned.next.prev = node;
                    node.next = justReturned.next;
                    justReturned.next = node;
                    node.prev = justReturned;
                }
            }

            private void checkMods() {
                if (modCount != LinkedList.this.modCount) throw new ConcurrentModificationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
}
