package com.yeskery.filter;

/**
 * 胆码过滤器
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class IntentFilter extends AbstractFilter {
	
	private final static char[] ALL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private char[] data;

	/**
	 * 胆码过滤器
	 * @param retain true 代表保留，false 代表删除
	 * @param data 需要过滤的数据数组
	 */
	public IntentFilter(boolean retain, char... data) {
		super(data.length, retain);
		this.data = data;
	}

	/**
	 * @see IntentFilter#IntentFilter(boolean, char...)
	 */
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
