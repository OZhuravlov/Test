package com.study.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<E> implements List<E>, Iterable<E>{

    private Node<E> head;
    private Node<E> tail;
    private int size;


    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }


    public void add(E value, int index) {
        if(index > size) {
            throw new IndexOutOfBoundsException("index param " + index + " should by between 0 and " + size);
        }
        if(index == size){
            add(value);
            return;
        }
        Node<E> newNode = new Node<E>(value);
        Node<E> current = getNodeByIndex(index);
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev = newNode;
        if(current == head){
            head = newNode;
        } else {
            newNode.prev.next = newNode;
        }
        size++;
    }


    public E remove(int index) {
        Node<E> current = getNodeByIndex(index);
        E value;
        if(size == 1) {
            value = head.value;
            clear();
            return value;
        }

        value = current.value;
        if (current == tail) {
            current.prev.next = null;
            tail = current.prev;
        } else if (current == head) {
            current.next.prev = null;
            head = current.next;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return value;
    }

    public E get(int index) {
        return getNodeByIndex(index).value;
    }

    public E set(E value, int index) {
        Node<E> node = getNodeByIndex(index);
        node.value = value;
        return node.value;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(E value) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if(Objects.equals(current.value, value)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        Node<E> current = tail;
        for (int i = size-1; i >= 0; i--) {
            if(Objects.equals(current.value, value)){
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        Node<E> node = head;
        StringJoiner stringJoiner = new StringJoiner(", ", "LinkedList [", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(node.value.toString());
            node = node.next;
        }
        return stringJoiner.toString();
    }

    private Node<E> getNodeFromHead(int index) {
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node getNodeFromTail(int index) {
        Node<E> current = tail;
        for (int i = size-2; i >= index; i--) {
            current = current.prev;
        }
        return current;
    }

    private Node<E> getNodeByIndex(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException("index param " + index + " should by between 0 and " + (size-1));
        }
        if (index == 0) {
            return head;
        }
        if (index == size-1) {
            return tail;
        }
        return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        Node<E> current;
        int i = 0;
        @Override
        public boolean hasNext() {
            if (size == 0 || i >= size) {
                return false;
            }
            Node node = (current == null) ? head : current;
            return node != null;
        }

        @Override
        public E next() {
            if(current == null) {
                current = head;
            }
            Node<E> node = current;
            current = current.next;
            i++;
            return node.value;
        }
    }

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value){
            this.value = value;
        }
    }
}