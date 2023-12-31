package com.github.ducknowledges.datastructures.stack;

import java.util.Deque;
import java.util.LinkedList;

public class Stack<T> {

  private final Deque<T> storage;

  public Stack() {
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
    return storage.pop();
  }

  // Time complexity O(1)
  public void push(T val) {
    storage.push(val);
  }

  public T peek() {
    if (storage.isEmpty()) {
      return null;
    }
    return storage.peek();
  }

  public boolean isEmpty() {
    return storage.isEmpty();
  }

  public Object[] toArray() {
    return storage.toArray();
  }
}
