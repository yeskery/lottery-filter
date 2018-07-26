package com.yeskery.filter;

import java.io.Serializable;

/**
 * 生成大底的号码，一个号码为一个 Node
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public class Node implements Serializable {

	public static final int MIN_STAR = 2;

	public static final int MAX_STAR = 5;

	private static final long serialVersionUID = 4074657644524856098L;
	/**
	 * 星级
	 */
	private int star;


	/**
	 * 数据
	 */
	private int data;

	/**
	 * 字符串形式的节点数据
	 */
	private String dataStr;

	/**
	 * 字符数组形式的节点数据
	 */
	private char[] dataArray;

	/**
	 * 跨度
	 */
	private int span;

	/**
	 * 和值
	 */
	private int sum;

	/**
	 * 合值
	 */
	private int remainder;

	/**
	 * 大小 0代表小 1代表大
	 */
	private String size;

	/**
	 * 单双 0代表双 1代表单
	 */
	private String dual;
	
	public Node(int star, int data) {
		this.star = star;
		this.data = data;
		dataStr = String.valueOf(data);
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0;i < star - dataStr.length();i++) {
			stringBuilder.append(0);
		}
		dataStr = stringBuilder.append(dataStr).toString();
		dataArray = dataStr.toCharArray();
		int[] tempDataArray = new int[dataArray.length];
		for (int i = 0;i < dataArray.length;i++) {
			tempDataArray[i] = Integer.parseInt(String.valueOf(dataArray[i]));
		}
		int min = tempDataArray[0], max = tempDataArray[0];
		StringBuilder sizeBuilder = new StringBuilder(), dualBuilder = new StringBuilder();
		for (int i = 0;i < tempDataArray.length;i++) {
			sum += tempDataArray[i];
			if (tempDataArray[i] < 5) {
				sizeBuilder.append(0);
			} else {
				sizeBuilder.append(1);
			}
			if (tempDataArray[i] % 2 == 0) {
				dualBuilder.append(0);
			} else {
				dualBuilder.append(1);
			}
			if (min > tempDataArray[i]) {
				min = tempDataArray[i];
			}
			if (max < tempDataArray[i]) {
				max = tempDataArray[i];
			}
		}
		size = sizeBuilder.toString();
		dual = dualBuilder.toString();
		span = max - min;
		String sumStr = String.valueOf(sum);
		if (sumStr.length() <= 1) {
			remainder = sum;
		} else {
			remainder = Integer.parseInt(String.valueOf(sumStr.charAt(sumStr.length() - 1)));
		}
	}

	public int getStar() {
		return star;
	}

	public int getData() {
		return data;
	}

	public String getDataStr() {
		return dataStr;
	}

	public char[] getDataArray() {
		return dataArray;
	}

	public int getSpan() {
		return span;
	}

	public int getSum() {
		return sum;
	}

	public int getRemainder() {
		return remainder;
	}

	public String getSize() {
		return size;
	}

	public String getDual() {
		return dual;
	}

	@Override
	public String toString() {
		return dataStr;
	}

}
