import java.util.*;
import java.io.*;

public class BinaryMinHeap {

	private int [] data;
	private int heapSize;

	public BinaryMinHeap(int capacity) {
		data = new int[capacity+1]; //bc we start from index 1, not 0
		data[0] = Integer.MIN_VALUE;
		heapSize = 0;
	}

	public BinaryMinHeap() {
		this(32); // this is essentially calling the constructor above BinaryMinHeap(32);
	}

	public int size() {
		return heapSize;
	}

	public int peek() { //peek gives you root

		if (isEmpty())
			throw new RuntimeException("HeapSize = 0");
		return data[1];
	}

	public boolean isEmpty() {
		return heapSize==0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		for (int i = 1; i<=heapSize; i++)
			sb.append(data[i] + ", ");

		String s = sb.toString().substring(0, sb.length()-2);
		return s + "]";
	}

	public void add(int value) {

		if (heapSize >= data.length-1)
			throw new RuntimeException("OUT OF CAPACITY");

		heapSize++;
		data[heapSize] = value;
		siftUp(heapSize); // Move up the new item from the end of array if needed
	}

	public void siftUp(int index) {
		int pi, temp; //pi = parent index

		if (index != 1) {
			pi = parentIndex(index);
			if (data[pi] > data[index]) {
				temp = data[pi];
				data[pi] = data[index];
				data[index] = temp;

				siftUp(pi);
			}
		}
	}

	public int poll() {
		if (isEmpty())
			throw new RuntimeException("HEAP IS ALREADY EMPTY");

		int min = data[1];
		data[1] = data[heapSize];
		heapSize--;

		if (heapSize > 0)
			siftDown(1);

		return min;
	}

	public void siftDown(int index) {
		int lci = leftChildIndex(index); //lci = left child index
		int rci = rightChildIndex(index); //rci = rightchild index
		int minIndex = 0, temp;

		if (lci > heapSize && rci > heapSize) {
			return;
		}
		else if (lci <= heapSize && rci > heapSize)
			minIndex = lci;
		else if (lci <= heapSize && rci <= heapSize) {

			if (data[lci] <= data[rci])
				minIndex = lci;
			else
				minIndex = rci;
		}


		if (data[index] > data[minIndex]) {
			temp = data[minIndex];
			data[minIndex] = data[index];
			data[index] = temp;

			siftDown(minIndex);
		}
	}

	public static int leftChildIndex(int nodeIndex) {
		return nodeIndex*2;
	}

	public static int rightChildIndex(int nodeIndex) {
		return nodeIndex*2 + 1;
	}

	public static int parentIndex(int nodeIndex) {
		return nodeIndex/2;
	}

	public static boolean isMinHeap(int[] array) {
		return isMinHeap(array, 1);
	}

	/************   Iterative Implementation *******/
  	public static boolean isMinHeap2(int[]arr){

		for(int i = 0; i<arr.length/2; i++){
			if(arr[i]>arr[leftChildIndex(i)] || arr[i] > arr[rightChildIndex(i)] )
				return false;
		}
		return true;
  	}

    /************   Recursive Implementation *******/
	public static boolean isMinHeap(int[] arr , int i){
		// if 'i' is a leaf node, return true as every leaf node is a heap
		if (rightChildIndex(i) > arr.length) {
			return true;
	 	}

		/**********   if 'i' is an internal node  **************/

		// recursively check if the left child is a heap
		boolean left = (arr [i] <= arr [leftChildIndex(i)]) && isMinHeap(arr , leftChildIndex(i));

		// recursively check if the right child is a heap
		boolean right =  (rightChildIndex(i) == arr.length  ||   // right true if child out of bounds
			(arr [i] <= arr [rightChildIndex(i)] && isMinHeap(arr ,rightChildIndex(i))));

		// return true if both left and right child are heaps
		return left && right;
  	}

	public static void main(String[]args){
		/*int[] heapArr = {-1,10,28,15,33,51,18,19,65,60,98,83,27,20,60,23};
		int[] notHeapArr = {-1,10,28,15,33,83,18,19,65,60,98,51,27,20,60,23};

		System.out.println(BinaryMinHeap.isMinHeap(heapArr));
		System.out.println(BinaryMinHeap.isMinHeap(notHeapArr) + "\n");

		BinaryMinHeap bmh = new BinaryMinHeap(20);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		bmh.add(5);
		bmh.add(8);
		bmh.add(13);
		bmh.add(7);
		bmh.add(18);
		bmh.add(23);
		bmh.add(15);
		bmh.add(2);


		pq.add(5);
		pq.add(8);
		pq.add(13);
		pq.add(7);
		pq.add(18);
		pq.add(23);
		pq.add(15);
		pq.add(2);
		System.out.println(bmh);
		System.out.println(pq);

		pq.poll();
		bmh.poll();
		System.out.println("\n" + bmh);
		System.out.println(pq + "\n");*/


		boolean passing = true;
		for (int i = 0; i < 1000; i++){ // run 1000 tests
		  BinaryMinHeap bmh = new BinaryMinHeap();
		  PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

			// add a random number of elements
			int howManyToAdd = (int)(Math.random()*32+1);;
		  for (int j = 0; j < howManyToAdd ; j++){
			int numToAdd = (int)(Math.random()*89+10);
			bmh.add(numToAdd);
			pq.add(numToAdd);
			if (!bmh.toString().equals(pq.toString())){
				passing = false;
				System.out.println("Failed during add>>\n"+bmh+"|"+"\n"+"|"+pq+"|");
			}
		  }
		  //System.out.println(">>"+bmh+"|"+"\n"+"|"+pq+"|");
		  // poll a random number of elements
		  int howManyToPoll = (int)(Math.random()*howManyToAdd);
		  for (int k = 0; k < howManyToPoll ; k++){
			bmh.poll();
			pq.poll();
			//System.out.println(bmh+"\n"+pq);
			if (!bmh.toString().equals(pq.toString())){
				passing = false;
				System.out.println("Failed After poll >>\n"+bmh+"\n"+pq);

			}
		  }
		}
		if (passing)
		  System.out.println("Passed all Tests! :)");
		else
		   System.out.println("Failed some Tests! :(");


	}



}