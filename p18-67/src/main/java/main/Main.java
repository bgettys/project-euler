package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author bgettys
 * 
 */
public class Main {

	/**
	 * The number triangle is in the form of a tree except leaves can share
	 * parents. This helps to keep track of the structure as well as the totals
	 * as they're calculated. *
	 */
	private static class Node {

		public final int value;
		public final Node leftParent, rightParent;
		public int total;

		public Node(int value, Node leftParent, Node rightParent) {
			total = this.value = value;
			this.leftParent = leftParent;
			this.rightParent = rightParent;
		}

	}

	public static void main(String[] args) throws IOException {
		LinkedList<Node> nodeQueue = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/p67.txt")))) {
			String line;
			// This null is just here for the first number to have an extra
			// parent without special code inside the loop
			nodeQueue.addLast(null);
			while (null != (line = br.readLine())) {
				// Add a null to make creating new child nodes from the left
				// side a bit easier (on each level the first left parent will
				// be null)
				nodeQueue.addLast(null);
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					/*
					 * Each child node will point to two parents (or a null) and
					 * remove one parent from consideration as the tree is
					 * traversed
					 */
					nodeQueue.addLast(new Node(Integer.parseInt(st.nextToken()), nodeQueue.pollFirst(), nodeQueue
							.peekFirst()));
				}
			}
		}
		// Remove that special left-side null from before
		nodeQueue.removeFirst();
		/*
		 * Start moving back up the tree while updating totals and discarding
		 * the leaves as we're done with them
		 */
		Node node;
		while (null != (node = nodeQueue.pollFirst())) {
			updateTotal(node.total, node.leftParent);
			updateTotal(node.total, node.rightParent);
			if (node.leftParent != null) {
				nodeQueue.addLast(node.leftParent);
			} else if (node.rightParent == null) { // if both parents are null
													// then we found the root
				System.out.println(node.total);
			}
		}
	}

	private static void updateTotal(int childTotal, Node parent) {
		if (parent != null) {
			parent.total = Math.max(parent.total, childTotal + parent.value);
		}
	}

}
