package com.github.ducknowledges.datastructures.orderedlist;

import java.util.ArrayList;

public class OrderedList<T extends Comparable<T>> {

  public Node<T> head;
  public Node<T> tail;
  private boolean _ascending;
  private int size;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
    size = 0;
  }

  public int compare(T v1, T v2) {
    if(v1 instanceof String) {
      return compareStrings(((String) v1).trim(), ((String) v2).trim());
    }
    return v1.compareTo(v2);
  }

  private int compareStrings(String str1, String str2) {
    return str1.compareTo(str2);
  }

  public void add(T value) {
    Node<T> newNode = new Node<>(value);
    if (this.isEmpty()) {
      addInTail(newNode);
      return;
    }

    Node<T> current = head;
    while (current != null) {
      if ((_ascending && compare(value, current.value) < 0) || (!_ascending && compare(value, current.value) > 0)) {
        if (current.prev != null) {
          current.prev.next = newNode;
          newNode.prev = current.prev;
        } else {
          head = newNode;
        }
        newNode.next = current;
        current.prev = newNode;
        size++;
        return;
      }
      if (current.next == null) {
        addInTail(newNode);
        return;
      }
      current = current.next;
    }
  }

  private void addInTail(Node _item) {
    if (this.head == null) {
      this.head = _item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = _item;
      _item.prev = tail;
    }
    this.tail = _item;
    this.size++;
  }

  public Node<T> find(T val) {
    Node<T> current = head;

    while (current != null) {
      if (compare(val, current.value) == 0) {
        return current;
      }
      current = current.next;
    }

    return null;
  }

  public void delete(T val) {
    Node<T> current = head;

    while (current != null) {
      if (compare(val, current.value) == 0) {
        if (current.prev != null) {
          current.prev.next = current.next;
        } else {
          head = current.next;
        }

        if (current.next != null) {
          current.next.prev = current.prev;
        } else {
          tail = current.prev;
        }

        size--;
        return;
      }
      current = current.next;
    }
  }

  public void clear(boolean asc) {
    _ascending = asc;
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public int count() {
    return size;
  }

  ArrayList<Node<T>> getAll() {
    ArrayList<Node<T>> r = new ArrayList<Node<T>>();
    Node<T> node = head;
    while(node != null)
    {
      r.add(node);
      node = node.next;
    }
    return r;
  }

  public boolean isEmpty() {
    return size < 1;
  }

  public Object[] toArray() {
    Object[] array = new Object[this.size];
    Node<T> node = head;
    int index = 0;
    while(node != null) {
      array[index] = node.value;
      index++;
      node = node.next;
    }
    return array;
  }
}

class Node<T> {
  public T value;
  public Node<T> next;
  public Node<T> prev;

  public Node(T _value) {
    value = _value;
    next = null;
    prev = null;
  }
}
