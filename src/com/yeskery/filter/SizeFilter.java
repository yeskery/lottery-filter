package com.yeskery.filter;

/**
 * 大小过滤器
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class SizeFilter extends AbstractFilter {
	
	private static final String[] ALL_TWO_STAR_ARRAY = {"11", "10", "00", "01"};
	
	private static final String[] ALL_THREE_STAR_ARRAY = {"111", "110", "101", "100",
			"000", "001", "010", "011"};
	
	private static final String[] ALL_FOUR_STAR_ARRAY = {"0000", "0001", "0010", "0011",
			"0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100",
			"1101", "1110", "1111"};
	
	private static final String[] ALL_FIVE_STAR_ARRAY = {"00000", "00001", "00010", "00011",
			"00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011",
			"01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011",
			"10100", "10101", "10110", "10111", "11000", "11001", "11010", "11011",
			"11100", "11101", "11110", "11111"};
	
	private String[] all;
	
	private String[] data;

	/**
	 * 大小过滤器
	 * @param star 星级
	 * @param retain true 代表保留，false 代表删除
	 * @param data 需要过滤的数据数组
	 */
	public SizeFilter(int star, boolean retain, String... data) {
		super(data.length, retain);
		if (star < Node.MIN_STAR || star > Node.MAX_STAR) {
			throw new RuntimeException("star out of bounds");
		}
		this.data = data;
		switch (star) {
			case 2: all = ALL_TWO_STAR_ARRAY;break;
			case 3: all = ALL_THREE_STAR_ARRAY;break;
			case 4: all = ALL_FOUR_STAR_ARRAY;break;
			case 5: all = ALL_FIVE_STAR_ARRAY;break;
			default: throw new RuntimeException("star out of bounds");
		}
	}

	@Override
	protected int getAllDataCount() {
		return all.length;
	}

	@Override
	protected boolean judge(Node node) {
		return contain(data, node.getSize());
	}

	@Override
	protected boolean judgeReverse(Node node) {
		String[] reverse = getReverseArray(all, data);
		return contain(reverse, node.getSize());
	}
}
