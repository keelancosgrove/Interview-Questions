import java.util.*;
public class Chapter4 {
	
	//Represents a binary tree
	public static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value){
			this.value=value;
			left = null;
			right = null;
		}
		
		public Node(int value, Node left, Node right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
		//Returns true if the tree contains a node with value v
		public boolean contains(int v){
			return (v == value)||left.contains(v)||right.contains(v);
		}
		
		//Returns true if value is greater than all values in the tree
		public boolean greaterThanAll(int value){
			if (value<this.value) return false;
			if (this.left == null && this.right == null) return true;
			return (left.greaterThanAll(value) && right.greaterThanAll(value));
		}
		
		//Returns true if value is less than all values in the tree
		public boolean lessThanAll(int value){
			if (value>this.value) return false;
			if (this.left == null && this.right == null) return true;
			return (left.lessThanAll(value) && right.lessThanAll(value));
		}
				
		//returns true if tree is a binary search tree
		public boolean isBST(){
			if (this.left == null && this.right == null) return true;
			return (left.greaterThanAll(value) && right.lessThanAll(value) && left.isBST() && right.isBST());
		}
		
		//finds parent of node in BST
		public Node findParent(Node v){
			int val = v.value;
			if (left == v || right == v) return this;
			if (val>=value) return right.findParent(v);
			else if (val<value) return left.findParent(v);
			return null;
		}
		
		//Finds node with minimum value starting from root in binary search tree
		public Node findMinNode(){
			Node n = this;
			while (n.left != null){
				n = n.left;
			}
			return n;
		}
		
		//Deletes node in binary search tree
		public void deleteNode(Node v){
			Node parent = findParent(v);
			boolean isLeft = (parent.left == v);
			//Both of node's children are null
			if (v.left == null && v.right == null){
				if (isLeft){ 
					parent.left = null;
				}
				else parent.right = null;
			}
			//Exactly one of node's children is null
			if (v.left == null && v.right != null){
				if (isLeft){
					parent.left = v.right;
				}
				else parent.right = v.right;
			}
			if (v.left != null && v.right == null){
				if (isLeft){
					parent.left = v.left;
				}
				else parent.right = v.left;
			}
			//Both of node's children are not null - removes node and recursively swaps upward
			else {
				Node minNode = v.right.findMinNode();
				if (isLeft){
					parent.left = minNode;
					if (parent.left.right != null){
						parent.left.right.deleteNode(minNode);
					}
				}
				else {
					parent.right = minNode;
					if (parent.right.right != null){
						parent.right.right.deleteNode(minNode);
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args){
		Node testNode = new Node(3, new Node(1, null, new Node(2, null, null)), new Node(6, null, null));
		Node minNode = testNode.findMinNode();
		System.out.println(minNode.value);
		testNode.deleteNode(minNode);
		Node newMinNode = testNode.findMinNode();
		System.out.println(newMinNode.value);
		System.out.println(testNode.greaterThanAll(4));
		System.out.println(testNode.greaterThanAll(8));
		System.out.println(testNode.isBST());
	}
}
