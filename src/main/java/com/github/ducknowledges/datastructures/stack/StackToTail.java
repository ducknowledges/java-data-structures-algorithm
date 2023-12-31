package com.github.ducknowledges.datastructures.stack;

import java.util.Deque;
import java.util.LinkedList;

public class StackToTail<T> {

  private final Deque<T> storage;

  public StackToTail() {
    this.storage = new LinkedList<>();
  }

  public int size() {
    return storage.size();
  }

  // Time complexity O(1)
  public T pop() {
    if(storage.isEmpty()) {
      return null;
    }
    return storage.removeLast();
  }

  // Time complexity O(1)
  public void push(T val) {
    storage.addLast(val);
  }

  public T peek() {
    if (storage.isEmpty()) {
      return null;
    }
    return storage.peekLast();
  }

  public Object[] toArray() {
    return storage.toArray();
  }
}

