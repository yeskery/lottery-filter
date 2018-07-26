package com.yeskery.filter;

/**
 * 合值过滤器
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class RemainderFilter extends AbstractFilter {
	
	private final static int[] ALL = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	private int[] data;

	/**
	 * 合值过滤器
	 * @param retain true 代表保留，false 代表删除
	 * @param data 需要过滤的数据数组
	 */
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
