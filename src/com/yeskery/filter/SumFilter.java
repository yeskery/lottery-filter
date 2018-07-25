package com.yeskery.filter;

public class SumFilter extends AbstractFilter {
	
	private int[] all;
	
	private int[] data;
	
	public SumFilter(int star, boolean retain, int... data) {
		super(data.length, retain);
		if (star < 2 || star > 5) {
			throw new RuntimeException("star out of bounds");
		}
		this.data = data;
		all = new int[star * 9 + 1];
		for (int i = 0;i < all.length;i++) {
			all[i] = i;
		}
	}
	
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
