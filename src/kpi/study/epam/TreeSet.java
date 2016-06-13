package kpi.study.epam;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * EPAM_Collections
 * Created 6/6/16, with IntelliJ IDEA
 *
 * @author Alex
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> {
    private Node<T> root;
    private int size;
    public TreeSet() {
        this.size = 0;
        this.root = new Node<>();
    }

    private class Node<T>{
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        T value;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
    @Override
    public boolean add(T o) {
        if (root.value == null) {
            root.value = o;
        }else{
            Node<T> current = root;
            while (current != null) {
                int cmp = o.compareTo(current.value);
                if (cmp == 0) {
                    return false;
                } else if (cmp < 0) {
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current.left = new Node<>(o);
                        current.left.parent = current;
                        size++;
                        return true;
                    }
                } else {//if (cmp > 0)
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        current.right = new Node<>(o);
                        current.right.parent = current;
                        size++;
                        return true;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> current = root;
        while (current != null) {
            int resOfCompare = current.value.compareTo((T) o);
            if (resOfCompare < 0) {
                current = current.left;
            } else if (resOfCompare > 0) {
                current = current.right;
            } else {
                if (current.left == null && current.right == null) {
                    if (current.parent.left == current) {
                        current.parent.left = null;
                    } else current.parent.right = null;
                } else if (current.left != null) {
                    Node<T> maxLeft = current.left;
                    while (maxLeft.right != null) {
                        maxLeft = maxLeft.right;
                    }
                    current.value = maxLeft.value;
                    if (maxLeft.parent.right == maxLeft) {
                        if (maxLeft.left != null) {
                            maxLeft.parent.right = maxLeft.left;
                        } else {
                            maxLeft.parent.right = null;
                        }
                    } else {
                        maxLeft.parent.left = maxLeft.left;
                    }
                } else {
                    Node<T> maxRight = current.right;
                    while (maxRight.left != null) {
                        maxRight = maxRight.left;
                    }
                    current.value = maxRight.value;
                    if (maxRight.parent.left == maxRight) {
                        if (maxRight.right != null) {
                            maxRight.parent.left = maxRight.right;
                        } else {
                            maxRight.parent.left = null;
                        }
                    } else {
                        maxRight.parent.right = maxRight.right;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;//TODO
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;//TODO
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;//TODO
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;//TODO
    }

    @Override
    public void clear() {
        size=0;
        root = null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    public boolean contains(T k) {
        Node<T> current = root;
        while (current != null) {
            int cmp = k.compareTo(current.value);
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                current = current.left;
            }
            if (cmp > 0) {
                current = current.right;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return null;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    private Node findPositionToAdd(Node root,T value){
        if((root.getLeft()==null)&(root.getRight()==null)){
            return root;
        } else if (root.getRight()==null){
            return root;
        }else {
            return root.getLeft();
        }
    }


}
