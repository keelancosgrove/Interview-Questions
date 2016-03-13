import java.util.*;

public class Chapter3 {
	public static class Node<E>{
		E value;
		Node next;
		
		public Node(E value){
			this.value=value;
			next=null;
		}
		
		public void insertNode(E v){
			Node<E> n  = new Node<E>(v);
			Node<E> current = this;
			while (current.next != null){
				current = current.next;
			}
		    current.next = n;
		}
		
		public Node<E> deleteNode(Node<E> head, E v){
			Node<E> n = head;
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
	public static class Stack<E> {
		Node<E> top;
		int size = 0;
		void push(E item){
			Node<E> t = new Node<E>(item);
			top.next = t;
			top = t;
			size += 1;
		}
		
		E pop(){
			if (top != null){
			size -= 1;
			Node<E> temp = top;
			top = top.next;
			return temp.value;
			}
			return null;
		}
		
		E peek(){
			if (top != null){
				return top.value;
			}
			return null;
		}
	}
	
	public static class Queue<E> {
		Node<E> first;
		Node<E> last;
		
		void enqueue(E input){
			Node<E> n = new Node<E>(input);
			if (first == null){
				first = n;
			}
			if (last == null){
				last = n;
			}
			else {
				last.next = n;
				last = last.next;
			}
		}
		
		E dequeue(){
			if (first == null){
				return null;
			}
			E temp = first.value;
			first = first.next;
			return temp;
		}
	}
	
	//Implements three stacks using one array
	public static class ThreeStacks{
		static final int stackSize = 300;
		int[] buffer  = new int[stackSize*3];
		int[] stackPointers = {0,0,0};
		
		void push(int stackNum, int input){
			int stackP = stackSize*stackNum + stackPointers[stackNum] + 1;
			stackPointers[stackNum] += 1;
			buffer[stackP] = input;
		}
		
		int pop(int stackNum){
			if (stackPointers[stackNum] == 0){
				return 0;
			}
			int stackP = stackSize*stackNum + stackPointers[stackNum];
			int val = buffer[stackP];
			buffer[stackP] = 0;
			stackPointers[stackNum] -= 1;
			return val;
		}
	}
	
	//Implements a stack that keeps track of the minimum element
	public static class StackWithMin extends Stack<Integer>{
		Stack<Integer> minStack;
		int min(){
			if (minStack.top == null){
				return Integer.MAX_VALUE;
			}
			else return minStack.peek();
		}
		
		void push(int e){
			if (minStack.top == null){
				minStack.push(e);
			}
			else if (e<min()){
				minStack.push(e);
			}
			super.push(e);
		}
		
		Integer pop(){
			int value = super.pop();
			if (value == min()){
				minStack.pop();
			}
			return value;
		}
	}
	
	public static class SetofStacks<E> {
		ArrayList<Stack<E>> allStacks = new ArrayList<Stack<E>>();
		int capacity;
		int size = 0;
		void push(E e){
			Stack<E> currentStack = allStacks.get(size);
			if (currentStack.size == capacity){
				Stack<E> newStack = new Stack<E>();
				newStack.push(e);
				allStacks.add(newStack);
				size += 1;
			}
			else {
				currentStack.push(e);
			}
		}
		E pop(){
			E current = allStacks.get(size).pop();
			if (current == null && size>0){
				allStacks.remove(size);
				size -= 1;
				E e = allStacks.get(size).pop();
				return e;
			}
			return current;
		}
		
		E popSpecific(int index){
			if (index < 0 || index > size) return null;
			if (index == size) pop();
			Stack<E> specifiedStack = allStacks.get(index);
			if (specifiedStack.size == 1){
				E val = specifiedStack.pop();
				for (int i = index; i<size; i++){
					allStacks.set(i,allStacks.get(i+1));
				}
				allStacks.remove(size);
				return val;
			}
			E val = specifiedStack.pop();
			return val;
		}
	}
	
	public void towersOfHanoi(Stack<Integer> first){
		Stack<Integer> middle = new Stack<Integer>();
		Stack<Integer> last = new Stack<Integer>();
		solveHanoi(first,middle,last,first.size);
	}
	
	public void solveHanoi(Stack<Integer> first,Stack<Integer> middle, Stack<Integer> last, int index){
		if (index == 1){
			int finalElement = first.pop();
			last.push(finalElement);
		}
		else if (index == 2){
			int secondToLast = first.pop();
			middle.push(secondToLast);
			int firstLast = first.pop();
			last.push(firstLast);
			last.push(secondToLast);
		}
		else {
			solveHanoi(first,last,middle,index-1);
			int lastElement = first.pop();
			last.push(lastElement);
			solveHanoi(middle, first, last,index-1);
		}
	}
	
	public static class QueueWithStacks<E> {
		Stack<E> s1;
		Stack<E> s2;
		
		void push(E e){
			if (s1.size == 0){
				s2.push(e);
			}
			else s1.push(e);
		}
		
		E pop(){
			if (s1.size == 0){
				while (s2.size>0){
					E element = s2.pop();
					s1.push(element);
				}
				E result = s1.pop();
				return result;
			}
			else {
				while (s1.size>0){
					E element = s1.pop();
					s2.push(element);
				}
				E result = s2.pop();
				return result;
			}
		}
		
		E peek(){
			if (s1.size == 0){
				while (s2.size>0){
					E element = s2.pop();
					s1.push(element);
				}
				return s1.peek();
			}
			else {
				while (s1.size>0){
					E element = s1.pop();
					s2.push(element);
				}
				return s2.peek();
			}
		}
	}
	
	public static Stack<Integer> sortStack(Stack<Integer> input){
		Stack<Integer> buffer = new Stack<Integer>();
		int n = input.size;
		if (n == 0 || n == 1) return input;
		buffer.push(input.pop());
		while (buffer.size < n) {
			int i = input.pop();
			while (i<buffer.peek()){
				input.push(buffer.pop());
			}
			buffer.push(i);
		}
		return buffer;
	}
}
