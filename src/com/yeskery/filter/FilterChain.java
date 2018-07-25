package com.yeskery.filter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FilterChain {
	
	private LinkedList<Filter> filters = new LinkedList<>();
	
	public void add(Filter filter) {
		if (filter.validte()) {
			filters.add(filter);
		}
	}
	
	public void remove(Filter filter) {
		filters.remove(filter);
	}
	
	public void clear() {
		filters.clear();
	}
	
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
