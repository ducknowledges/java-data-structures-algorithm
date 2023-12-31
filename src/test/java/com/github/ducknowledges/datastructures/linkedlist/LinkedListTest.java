package com.github.ducknowledges.datastructures.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LinkedList")
class LinkedListTest {

  private LinkedList list;
  private LinkedList emptyList;
  private LinkedList singleNodeList;
  private Node node;
  private List<Node> nodes;

  @BeforeEach
  void init() {
    emptyList = new LinkedList();

    node = new Node(1);
    singleNodeList = new LinkedList();
    singleNodeList.addInTail(node);

    nodes = List.of(
        new Node(1), new Node(1),
        new Node(2), new Node(1), new Node(4),
        new Node(1), new Node(1)
    );
    list = new LinkedList();
    list.addInTail(nodes.get(0));
    list.addInTail(nodes.get(1));
    list.addInTail(nodes.get(2));
    list.addInTail(nodes.get(3));
    list.addInTail(nodes.get(4));
    list.addInTail(nodes.get(5));
    list.addInTail(nodes.get(6));
  }

  @Test
  @DisplayName("should find node by value")
  void shouldFindNodeByValue() {
    assertThat(emptyList.find(1)).isNull();
    assertThat(emptyList.head).isNull();
    assertThat(emptyList.tail).isNull();

    assertThat(singleNodeList.find(2)).isNull();
    assertThat(singleNodeList.find(1)).isEqualTo(node);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.tail).isEqualTo(node);

    assertThat(list.find(0)).isNull();
    assertThat(list.find(2)).isEqualTo(nodes.get(2));
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.tail).isEqualTo(nodes.get(6));
  }

  @Test
  @DisplayName("should find all nodes with value")
  void shouldFindAllNodesByValue() {
    assertThat(emptyList.findAll(1)).isEmpty();

    assertThat(singleNodeList.findAll(1)).isNotEmpty();
    assertThat(singleNodeList.findAll(1)).hasSize(1);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.tail).isEqualTo(node);

    assertThat(list.findAll(5)).isEmpty();
    assertThat(list.findAll(1)).isNotEmpty();
    List<Node> actual = list.findAll(1);
    assertThat(actual).hasSize(5);
    assertThat(actual.get(0)).isEqualTo(nodes.get(0));
    assertThat(actual.get(1)).isEqualTo(nodes.get(1));
    assertThat(actual.get(2)).isEqualTo(nodes.get(3));
    assertThat(actual.get(3)).isEqualTo(nodes.get(5));
    assertThat(actual.get(4)).isEqualTo(nodes.get(6));
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.tail).isEqualTo(nodes.get(6));
  }

  @Test
  @DisplayName("should remove first found node by value")
  void shouldRemoveFirstFoundNodeByValue() {
    assertThat(emptyList.remove(1)).isFalse();
    assertThat(emptyList.head).isNull();
    assertThat(emptyList.tail).isNull();

    assertThat(singleNodeList.count()).isEqualTo(1);
    assertThat(singleNodeList.remove(2)).isFalse();
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.head.next).isNull();
    assertThat(singleNodeList.tail).isEqualTo(node);
    assertThat(singleNodeList.tail.next).isNull();

    assertThat(singleNodeList.remove(1)).isTrue();
    assertThat(singleNodeList.count()).isZero();
    assertThat(singleNodeList.head).isNull();
    assertThat(singleNodeList.tail).isNull();

    assertThat(list.count()).isEqualTo(7);
    assertThat(list.remove(8)).isFalse();
    assertThat(list.count()).isEqualTo(7);
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.head.next).isEqualTo(nodes.get(1));
    assertThat(list.tail).isEqualTo(nodes.get(6));
    assertThat(list.tail.next).isNull();

    for (Node n: list.findAll(1)) {
      assertThat(list.remove(1)).isTrue();
    }
    assertThat(list.count()).isEqualTo(2);
    assertThat(list.head).isEqualTo(nodes.get(2));
    assertThat(list.head.next).isEqualTo(nodes.get(4));
    assertThat(list.tail).isEqualTo(nodes.get(4));
    assertThat(list.tail.next).isNull();
  }

  @Test
  @DisplayName("should remove all nodes by value")
  void shouldRemoveAllNodesByValue() {
    emptyList.removeAll(1);
    assertThat(emptyList.head).isNull();
    assertThat(emptyList.tail).isNull();

    assertThat(singleNodeList.count()).isEqualTo(1);
    singleNodeList.removeAll(8);
    assertThat(singleNodeList.count()).isEqualTo(1);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.tail).isEqualTo(node);

    singleNodeList.removeAll(1);
    assertThat(singleNodeList.count()).isZero();
    assertThat(singleNodeList.head).isNull();
    assertThat(singleNodeList.tail).isNull();

    assertThat(list.count()).isEqualTo(7);
    list.removeAll(1);
    assertThat(list.count()).isEqualTo(2);
    assertThat(list.find(1)).isNull();
    assertThat(list.head).isEqualTo(nodes.get(2));
    assertThat(list.tail).isEqualTo(nodes.get(4));
    assertThat(list.tail.next).isNull();
  }

  @Test
  @DisplayName("should remove all nodes when node in tail by value")
  void shouldRemoveAllNodesWhenNodeInTail() {
    List <Node> nodes = List.of(
        new Node(1), new Node(1),
        new Node(2),
        new Node(1), new Node(1),
        new Node(4),
        new Node(1), new Node(1)
    );
    list = new LinkedList();
    list.addInTail(nodes.get(0));
    list.addInTail(nodes.get(1));
    list.addInTail(nodes.get(2));
    list.addInTail(nodes.get(3));
    list.addInTail(nodes.get(4));
    list.addInTail(nodes.get(5));
    list.addInTail(nodes.get(6));
    list.addInTail(nodes.get(7));

    assertThat(list.count()).isEqualTo(8);
    list.removeAll(1);
    assertThat(list.count()).isEqualTo(2);
    assertThat(list.find(1)).isNull();
    assertThat(list.head).isEqualTo(nodes.get(2));
    assertThat(list.head.next).isEqualTo(nodes.get(5));
    assertThat(list.tail).isEqualTo(nodes.get(5));
    assertThat(list.tail.next).isNull();
  }

  @Test
  @DisplayName("should clear linked list")
  void shouldClear() {
    assertThat(list.count()).isEqualTo(7);
    list.clear();
    assertThat(list.count()).isZero();
    assertThat(list.head).isNull();
    assertThat(list.tail).isNull();
  }

  @Test
  @DisplayName("should count nodes in linked list")
  void shouldCountNodes() {
    assertThat(emptyList.count()).isZero();
    assertThat(emptyList.head).isNull();
    assertThat(emptyList.tail).isNull();

    assertThat(singleNodeList.count()).isEqualTo(1);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.tail).isEqualTo(node);

    assertThat(list.count()).isEqualTo(7);
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.tail).isEqualTo(nodes.get(6));
  }

  @Test
  @DisplayName("should insert node after existing node")
  void shouldInsertNode() {
    assertThat(emptyList.count()).isZero();
    emptyList.insertAfter(null, node);
    assertThat(emptyList.count()).isEqualTo(1);
    assertThat(emptyList.head).isEqualTo(node);
    assertThat(emptyList.head.next).isNull();
    assertThat(emptyList.tail).isEqualTo(node);
    assertThat(emptyList.tail.next).isNull();

    Node insertedNode1 = new Node(8);
    Node insertedNode2 = new Node(2);

    assertThat(singleNodeList.count()).isEqualTo(1);
    singleNodeList.insertAfter(node, insertedNode1);
    assertThat(singleNodeList.count()).isEqualTo(2);
    assertThat(singleNodeList.head).isEqualTo(node);
    assertThat(singleNodeList.head.next).isEqualTo(insertedNode1);
    assertThat(singleNodeList.tail).isEqualTo(insertedNode1);
    assertThat(singleNodeList.tail.next).isNull();

    singleNodeList.insertAfter(null, insertedNode2);
    assertThat(singleNodeList.count()).isEqualTo(3);
    assertThat(singleNodeList.head).isEqualTo(insertedNode2);
    assertThat(singleNodeList.tail).isEqualTo(insertedNode1);

    assertThat(list.count()).isEqualTo(7);
    list.insertAfter(nodes.get(3), insertedNode1);
    assertThat(list.count()).isEqualTo(8);
    assertThat(list.find(8)).isEqualTo(insertedNode1);
    assertThat(list.head).isEqualTo(nodes.get(0));
    assertThat(list.tail).isEqualTo(nodes.get(6));
  }

}