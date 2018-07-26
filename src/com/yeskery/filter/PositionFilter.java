package com.yeskery.filter;

/**
 * 位置过滤器
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class PositionFilter extends AbstractFilter {

	public static final int MIN = 1;
	
	private final static char[] ALL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private char[] data;
	
	private int position;

	/**
	 * 位置过滤器
	 * @param star 星级
	 * @param position 需要过滤的位置 1代表第1位
	 * @param retain true 代表保留，false 代表删除
	 * @param data 需要过滤的数据数组
	 */
	public PositionFilter(int star, int position, boolean retain, char... data) {
		super(data.length, retain);
		if (star < Node.MIN_STAR || star > Node.MAX_STAR) {
			throw new RuntimeException("star out of bounds");
		}
		if (position < MIN || position > star) {
			throw new RuntimeException("position out of bounds");
		}
		this.position = position;
		this.data = data;
	}

	/**
	 *
	 * @see PositionFilter#PositionFilter(int, int, boolean, char...)
	 */
	public PositionFilter(int star, int position, boolean retain, int... data) {
		super(data.length, retain);
		if (star < Node.MIN_STAR || star > Node.MAX_STAR) {
			throw new RuntimeException("star out of bounds");
		}
		if (position < MIN || position > star) {
			throw new RuntimeException("position out of bounds");
		}
		this.position = position;
		this.data = parse(data);
	}

	@Override
	protected int getAllDataCount() {
		return ALL.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(data, node.getDataArray()[position - 1]);
	}

	@Override
	protected boolean judgeReverse(Node node) {
		char[] reverse = getReverseArray(ALL, data);
		return contain(reverse, node.getDataArray()[position - 1]);
	}

}
