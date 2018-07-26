package com.yeskery.filter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 过滤器链
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class FilterChain {
	
	private LinkedList<Filter> filters = new LinkedList<>();
	
	public void add(Filter filter) {
		if (filter.validate()) {
			filters.add(filter);
		}
	}
	
	public void remove(Filter filter) {
		filters.remove(filter);
	}
	
	public void clear() {
		filters.clear();
	}

	/**
	 * 使用预加载星级所有节点的方式进行过滤
	 * @param nodeList 星级所有节点
	 * @return 过滤后的节点集合
	 */
	public List<Node> filter(List<Node> nodeList) {
		if (filters.size() < 1) {
			return nodeList;
		}
		LinkedList<Node> resultNodeList = new LinkedList<>();
		Iterator<Node> iterator = nodeList.iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			boolean deleteSign = false;
			for (Filter filter : filters) {
				if (!filter.filter(filter.isRetain(), node)) {
					deleteSign = true;
					break;
				}
			}
			if (!deleteSign) {
				resultNodeList.add(node);
			}
		}
		return resultNodeList;
	}

	/**
	 * 使用星级节点迭代器方式进行过滤
	 * @param iterator 星级节点迭代器
	 * @param count 需要获取的符合过滤节点的个数
	 * @return 过滤后的节点集合
	 */
	public List<Node> filter(Iterator<Node> iterator, int count) {
		LinkedList<Node> resultNodeList = new LinkedList<>();
		if (filters.size() < 1) {
			while (iterator.hasNext()) {
				if (resultNodeList.size() >= count) {
					break;
				}
				resultNodeList.add(iterator.next());
			}
			return resultNodeList;
		}
		while (iterator.hasNext()) {
			if (resultNodeList.size() >= count) {
				break;
			}
			Node node = iterator.next();
			boolean deleteSign = false;
			for (Filter filter : filters) {
				if (!filter.filter(filter.isRetain(), node)) {
					deleteSign = true;
					break;
				}
			}
			if (!deleteSign) {
				resultNodeList.add(node);
			}
		}
		return resultNodeList;
	}

}
