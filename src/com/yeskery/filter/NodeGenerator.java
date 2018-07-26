package com.yeskery.filter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 节点生成器，有两种生成模式，一种是预加载对应星级的所有节点对象
 * {@link NodeGenerator#generate()}，使用懒加载的单例模式，另一种
 * 是迭代器模式，按需加载对应星级的节点对象{@link NodeGenerator#iterator()}
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class NodeGenerator {
	
	private volatile static List<Node> twoStarNodeList;
	
	private volatile static List<Node> threeStarNodeList;
	
	private volatile static List<Node> fourStarNodeList;
	
	private volatile static List<Node> fiveStarNodeList;

	/**
	 * 星级
	 */
	private int star;

	public NodeGenerator(int star) {
		if (star < Node.MIN_STAR || star > Node.MAX_STAR) {
			throw new RuntimeException("star out of bounds");
		}
		this.star = star;
	}

	/**
	 * 预加载对应星级的所有节点对象到内存中，使用单例模式
	 * 一个星级对应一个节点集合
	 * @return 对应星级的所有节点集合
	 */
	public List<Node> generate() {
		List<Node> nodeList;
		switch (star) {
			case 2 : nodeList = twoStarNodeList;break;
			case 3 : nodeList = threeStarNodeList;break;
			case 4 : nodeList = fourStarNodeList;break;
			case 5 : nodeList = fiveStarNodeList;break;
			default: throw new RuntimeException("star out of bounds");
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
						default: throw new RuntimeException("star out of bounds");
					}
				}
			}
		}
		return nodeList;
	}

	/**
	 * 迭代器模式，按需加载对应星级的节点对象
	 * @return 对应星级的节点迭代器
	 */
	public Iterator<Node> iterator() {
		return new NodeIterator(star);
	}
}
