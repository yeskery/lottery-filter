package com.yeskery.filter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NodeGenerator {
	
	private volatile static List<Node> twoStarNodeList;
	
	private volatile static List<Node> threeStarNodeList;
	
	private volatile static List<Node> fourStarNodeList;
	
	private volatile static List<Node> fiveStarNodeList;

	private int star;
	
	public NodeGenerator(int star) {
		if (star < 2 || star > 5) {
			throw new RuntimeException("star out of bounds");
		}
		this.star = star;
	}
	
	public List<Node> generate() {
		List<Node> nodeList = null;
		switch (star) {
			case 2 : nodeList = twoStarNodeList;break;
			case 3 : nodeList = threeStarNodeList;break;
			case 4 : nodeList = fourStarNodeList;break;
			case 5 : nodeList = fiveStarNodeList;break;
		}
		if (nodeList == null) {
			synchronized (NodeGenerator.class) {
				if (nodeList == null) {
					nodeList = new LinkedList<>();
					int count = (int)Math.pow((long)10, (long)star);
					for (int i = 0;i < count;i++) {
						nodeList.add(new Node(star, i));
					}
					switch (star) {
						case 2 : twoStarNodeList = nodeList;break;
						case 3 : threeStarNodeList = nodeList;break;
						case 4 : fourStarNodeList = nodeList;break;
						case 5 : fiveStarNodeList = nodeList;break;
					}
				}
			}
		}
		return nodeList;
	}
	
	public Iterator<Node> iterator() {
		return new NodeIterator(star);
	}
	
}
