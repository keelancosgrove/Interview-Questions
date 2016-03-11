import java.util.*;
public class Chapter2 {
	
	public static class Node{
		int value;
		Node next;
		
		public Node(int value){
			this.value=value;
			next=null;
		}
		
		public void insertNode(int v){
			Node n  = new Node(v);
			Node current = this;
			while (current.next != null){
				current = current.next;
			}
		    current.next = n;
		}
		
		public Node deleteNode(Node head, int v){
			Node n = head;
			if (n.value == v){
				return head.next;
			}
			else {
				while (n.next != null){
					if (n.next.value == v){
						n.next = n.next.next;
						return head;
					}
					n = n.next;
				}
			}
			return head;
		}
		
	}
	
	//Removes duplicates from an unsorted linked list
	public static Node removeDuplicates(Node input){
		Node n = input;
		if (input == null) return input;
		while (n.next != null){
			int current = n.value;
			Node i = input;
			boolean found = false;
			while (i != n){
				if (current == i.value){
					found = true;
					break;
				}
				i = i.next;
			}
			if (found){
				n.next  = n.next.next;
			}
			n = n.next;
		}
		return input;
	}
	
	//Finds the nth to last element of a linked list
	public static int nthToLast(Node input, int n){
		Node nthNode = input;
		int counter = 0;
		while (counter<n-1){
			counter += 1;
			if (nthNode.next == null){
				throw new IndexOutOfBoundsException();
			}
			nthNode = nthNode.next;
		}
		while (nthNode.next != null){
			input = input.next;
			nthNode = nthNode.next;
		}
		return input.value;
	}
	
	public static boolean deleteMiddleNode(Node middleNode){
		if (middleNode.next == null) return false;
		middleNode.value = middleNode.next.value;
		middleNode.next = middleNode.next.next;
		return true;
	}
	
	//Adds the two numbers represented by the linked lists
	//Assumes the linked lists have the same length
	public static Node addNumbers(Node n1, Node n2){
		if (n1 == null){
			return n2;
		}
		else if (n2 == null){
			return n1;
		}
		int carry = 0;
		int firstVal = n1.value + n2.value;
		if (firstVal >= 10) {
			carry = 1;
		}
		else carry = 0;
		Node result = new Node(firstVal);
		n1 = n1.next;
		n2 = n2.next;
		while (n1 != null && n2 != null){
			int val = n1.value + n2.value + carry;
			result.insertNode(val%10);
			if (val >= 10){
				carry = 1;
			}
			else carry = 0;
			n1 = n1.next;
			n2 = n2.next;
		}
		return result;
	}
	
	//Returns node beginning a cycle in circularly linked list
	//Alternative, likely simpler but less space efficient solution is to just store nodes in a hashset
	public static Node findBegin(Node input){
		Node slow = input;
		Node fast = input;
		if (fast.next != null && fast.next.next != null){
			fast = fast.next.next;
		}
		else return null;
		slow = slow.next;
		while (slow != fast){
			slow = slow.next;
			fast = fast.next.next;
		}
		slow = input;
		while (slow != fast){
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

