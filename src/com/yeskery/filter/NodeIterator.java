package com.yeskery.filter;

import java.util.Iterator;

/**
 * 节点的迭代器
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class NodeIterator implements Iterable<Node>, Iterator<Node> {
	
	private int star;
	
	private int index = 0;
	
	private int maxCount;
	
	public NodeIterator(int star) {
		if (star < Node.MIN_STAR || star > Node.MAX_STAR) {
			throw new RuntimeException("star out of bounds");
		}
		this.star = star;
		int count = (int)Math.pow((long)10, (long)star);
		maxCount = count;
	}

	@Override
	public Iterator<Node> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return index + 1 <= maxCount;
	}

	@Override
	public Node next() {
		return new Node(star, index++);
	}

}
