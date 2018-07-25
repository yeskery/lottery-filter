package com.yeskery.filter;

public class PostionFilter extends AbstractFilter {
	
	private final static char[] ALL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private char[] data;
	
	private int postion;
	
	public PostionFilter(int star, int postion, boolean retain, char... data) {
		super(data.length, retain);
		if (star < 2 || star > 5) {
			throw new RuntimeException("star out of bounds");
		}
		if (postion < 1 || postion > star) {
			throw new RuntimeException("postion out of bounds");
		}
		this.postion = postion;
		this.data = data;
	}
	
	public PostionFilter(int star, int postion, boolean retain, int... data) {
		super(data.length, retain);
		if (star < 2 || star > 5) {
			throw new RuntimeException("star out of bounds");
		}
		if (postion < 1 || postion > star) {
			throw new RuntimeException("postion out of bounds");
		}
		this.postion = postion;
		this.data = parse(data);
	}

	@Override
	protected int getAllDataCount() {
		return ALL.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(data, node.getDataArray()[postion - 1]);
	}

	@Override
	protected boolean judgeReverse(Node node) {
		char[] reverse = getReverseArray(ALL, data);
		return contain(reverse, node.getDataArray()[postion - 1]);
	}

}
