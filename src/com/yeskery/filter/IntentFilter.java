package com.yeskery.filter;

public class IntentFilter extends AbstractFilter {
	
	private final static char[] ALL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private char[] data;

	public IntentFilter(boolean retain, char... data) {
		super(data.length, retain);
		this.data = data;
	}
	
	public IntentFilter(boolean retain, int... data) {
		super(data.length, retain);
		this.data = parse(data);
	}

	@Override
	protected int getAllDataCount() {
		return ALL.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(node.getDataArray(), data);
	}

	@Override
	protected boolean judgeReverse(Node node) {
		char[] reverse = getReverseArray(ALL, data);
		return contain(node.getDataArray(), reverse);
	}

}
