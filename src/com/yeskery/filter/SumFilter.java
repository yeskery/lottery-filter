package com.yeskery.filter;

/**
 * 和值过滤器
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class SumFilter extends AbstractFilter {
	
	private int[] all;
	
	private int[] data;

	/**
	 * 和值过滤器
	 * @param star 星级
	 * @param retain true 代表保留，false 代表删除
	 * @param data 需要过滤的数据数组
	 */
	public SumFilter(int star, boolean retain, int... data) {
		super(data.length, retain);
		if (star < Node.MIN_STAR || star > Node.MAX_STAR) {
			throw new RuntimeException("star out of bounds");
		}
		this.data = data;
		all = new int[star * 9 + 1];
		for (int i = 0;i < all.length;i++) {
			all[i] = i;
		}
	}

	/**
	 *
	 * @see SumFilter#SumFilter(int, boolean, int...)
	 */
	public SumFilter(int star, boolean retain, char... data) {
		super(data.length, retain);
		if (star < 2 || star > 5) {
			throw new RuntimeException("star out of bounds");
		}
		this.data = parse(data);
		all = new int[star * 9 + 1];
		for (int i = 0;i < all.length;i++) {
			all[i] = i;
		}
	}

	@Override
	protected int getAllDataCount() {
		return all.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(data, node.getSum());
	}

	@Override
	protected boolean judgeReverse(Node node) {
		int[] reverse = getReverseArray(all, data);
		return contain(reverse, node.getSum());
	}

}
