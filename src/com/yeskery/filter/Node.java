package com.yeskery.filter;

import java.io.Serializable;

public class Node implements Serializable {
	
	private static final long serialVersionUID = 4074657644524856098L;
	
	private int star;
	
	private int data;
	
	private String dataStr;
	
	private char[] dataArray;
	
	private int span;
	
	private int sum;
	
	private int remainder;
	
	private String size;
	
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
