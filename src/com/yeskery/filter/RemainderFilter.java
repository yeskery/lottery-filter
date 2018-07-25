package com.yeskery.filter;

public class RemainderFilter extends AbstractFilter {
	
	private final static int[] ALL = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	private int[] data;
	
	public RemainderFilter(boolean retain, int... data) {
		super(data.length, retain);
		this.data = data;
	}
	
	public RemainderFilter(boolean retain, char... data) {
		super(data.length, retain);
		this.data = parse(data);
	}

	@Override
	protected int getAllDataCount() {
		return ALL.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(data, node.getRemainder());
	}

	@Override
	protected boolean judgeReverse(Node node) {
		int[] reverse = getReverseArray(ALL, data);
		return contain(reverse, node.getRemainder());
	}

}
