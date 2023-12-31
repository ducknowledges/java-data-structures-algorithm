package com.github.ducknowledges.datastructures.queue;

import java.util.LinkedList;

public class Queue<T> {

  private final java.util.Deque<T> storage;

  public Queue() {
    this.storage = new LinkedList<>();
  }

  // Time complexity O(1)
  public void enqueue(T item) {
    storage.addLast(item);
  }

  // Time complexity O(1)
  public T dequeue() {
    return storage.pollFirst();
  }

  public int size() {
    return storage.size();
  }

  public boolean isEmpty() {
    return storage.isEmpty();
  }

  public Object[] toArray() {
    return storage.toArray();
  }

}
