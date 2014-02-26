package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node {
		
		public final int value;
		public final Node leftParent, rightParent;
		public int total;
		
		public Node(int value, Node leftParent, Node rightParent) {
			this.total = this.value = value;
			this.leftParent = leftParent;
			this.rightParent = rightParent;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		LinkedList<Node> nodeQueue = new LinkedList<Node>();
		InputStream is = Main.class.getResourceAsStream("/p67.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			String line;
			nodeQueue.addLast(null);
			while (null != (line = br.readLine())) {
				nodeQueue.addLast(null);
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					nodeQueue.addLast(new Node(Integer.parseInt(st.nextToken()), nodeQueue.pollFirst(), nodeQueue.peekFirst()));
				}
			}
		} finally {
			br.close();
		}
		nodeQueue.removeFirst();
		Node node;
		while (null != (node = nodeQueue.pollFirst())) {
			updateTotal(node.total, node.leftParent);
			updateTotal(node.total, node.rightParent);
			if (node.leftParent != null) {
				nodeQueue.addLast(node.leftParent);
			} else if (node.rightParent == null) {
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
