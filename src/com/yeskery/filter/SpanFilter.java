package com.yeskery.filter;

public class SpanFilter extends AbstractFilter {
	
	private final static int[] ALL = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	private int[] data;
	
	public SpanFilter(boolean retain, int... data) {
		super(data.length, retain);
		this.data = data;
	}
	
	public SpanFilter(boolean retain, char... data) {
		super(data.length, retain);
		this.data = parse(data);
	}

	@Override
	protected int getAllDataCount() {
		return ALL.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(data, node.getSpan());
	}

	@Override
	protected boolean judgeReverse(Node node) {
		int[] reverse = getReverseArray(ALL, data);
		return contain(reverse, node.getSpan());
	}

}
