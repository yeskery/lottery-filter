package com.yeskery.filter;

/**
 * 过滤器接口接口的通用抽象实现
 *
 * @author yeskery
 * @date 2018-07-26 14:23
 */
public abstract class AbstractFilter implements Filter {

	private static final int HALF_SIGN = 2;

	/**
	 * 过滤器中需要过滤的数据的个数
	 */
	private int count;

	/**
	 * @see Filter#isRetain()
	 */
	private boolean retain;

	public AbstractFilter(int count, boolean retain) {
		this.count = count;
		this.retain = retain;
	}

	@Override
	public boolean filter(boolean retain, Node node) {
		if (retain) {
			if (count < (getAllDataCount() / HALF_SIGN)) {
				return judge(node);
			} else {
				return !judgeReverse(node);
			}
		} else {
			if (count < (getAllDataCount() / HALF_SIGN)) {
				return !judge(node);
			} else {
				return judgeReverse(node);
			}
		}
	}

	@Override
	public boolean validate() {
		if (retain) {
			if (count >= getAllDataCount()) {
				return false;
			}
		} else {
			if (count <= 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isRetain() {
		return retain;
	}

	/**
	 * 判断两个 char 数组是否含有交集
	 * @param dept 被比较的数组
	 * @param src 源数组
	 * @return true 代表还有交集，false 代表没有交集
	 */
	protected boolean contain(char[] dept, char[] src) {
		for (char deptChar : dept) {
			for (char srcChar : src) {
				if (srcChar == deptChar) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断字符数组是否包含指定字符
	 * @param dept 被比较的数组
	 * @param src 源字符
	 * @return true 代表包含，false 代表不包含
	 */
	protected boolean contain(char[] dept, char src) {
		for (char deptChar : dept) {
			if (src == deptChar) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断 int 数组是否包含指定 int
	 * @param dept 被比较的数组
	 * @param src 源数字
	 * @return true 代表包含，false 代表不包含
	 */
	protected boolean contain(int[] dept, int src) {
		for (int deptInt : dept) {
			if (src == deptInt) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串数组是否包含指定字符串
	 * @param dept 被比较的数组
	 * @param src 源字符串
	 * @return true 代表包含，false 代表不包含
	 */
	protected boolean contain(String[] dept, String src) {
		for (String deptStr : dept) {
			if (src.equals(deptStr)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 全部数据数组中排除指定部分数据后，获取剩余部分的数组
	 * @param all 全部数据数组
	 * @param data 部分数组数组
	 * @return 剩余部分的数组
	 */
	protected char[] getReverseArray(char[] all, char[] data) {
		char[] tempArray = new char[all.length - data.length];
		int index = 0;
		for (int i = 0;i < all.length;i++) {
			boolean contain = false;
			for (int j = 0;j < data.length;j++) {
				if (data[j] == all[i]) {
					contain = true;
				}
			}
			if (!contain) {
				tempArray[index++] = all[i];
			}
		}
		return tempArray;
	}

	/**
	 * @see AbstractFilter#getReverseArray(char[], char[])
	 */
	protected int[] getReverseArray(int[] all, int[] data) {
		int[] tempArray = new int[all.length - data.length];
		int index = 0;
		for (int i = 0;i < all.length;i++) {
			boolean contain = false;
			for (int j = 0;j < data.length;j++) {
				if (data[j] == all[i]) {
					contain = true;
				}
			}
			if (!contain) {
				tempArray[index++] = all[i];
			}
		}
		return tempArray;
	}

	/**
	 * @see AbstractFilter#getReverseArray(char[], char[])
	 */
	protected String[] getReverseArray(String[] all, String[] data) {
		String[] tempArray = new String[all.length - data.length];
		int index = 0;
		for (int i = 0;i < all.length;i++) {
			boolean contain = false;
			for (int j = 0;j < data.length;j++) {
				if (data[j].equals(all[i])) {
					contain = true;
				}
			}
			if (!contain) {
				tempArray[index++] = all[i];
			}
		}
		return tempArray;
	}

	/**
	 * 将 int 类型的数组转换成 char 类型的数组
	 * @param array int 类型的数组
	 * @return 转换后的 char 类型数组
	 */
	protected char[] parse(int[] array) {
		char[] data = new char[array.length];
		for (int i = 0;i < array.length;i++) {
			data[i] = (char) (array[i] + '0');
		}
		return data;
	}

	/**
	 * 将 char 类型的数组转换成 int 类型的数组
	 * @param array char 类型的数组
	 * @return 转换后的 int 类型数组
	 */
	protected int[] parse(char[] array) {
		int[] data = new int[array.length];
		for (int i = 0;i < array.length;i++) {
			data[i] = (int)(array[i]) - (int)('0');
		}
		return data;
	}

	/**
	 * 获取所有可能的查询条件的个数
	 * @return 所有可能的查询条件的个数
	 */
	protected abstract int getAllDataCount();

	/**
	 * 判断指定节点是否被过滤器过滤
	 * 使用创建过滤器时传入的 data 数组进行判断
	 * @param node 需要判断的节点
	 * @return true 代表该节点能通过该过滤器，false 代表该节点不能通过该过滤器
	 */
	protected abstract boolean judge(Node node);

	/**
	 * 判断指定节点是否被过滤器过滤
	 * 全部的数据移除传入的数据数组后，使用移除后的数组进行判断
	 * @param node 需要判断的节点
	 * @return 代表该节点能通过该过滤器，false 代表该节点不能通过该过滤器
	 *
	 * @see AbstractFilter#getReverseArray(char[], char[])
	 */
	protected abstract boolean judgeReverse(Node node);

}
