package com.yeskery.filter;

public abstract class AbstractFilter implements Filter {
	
	private int count;
	
	private boolean retain;
	

	public AbstractFilter(int count, boolean retain) {
		this.count = count;
		this.retain = retain;
	}

	@Override
	public boolean filter(boolean retain, Node node) {
		if (retain) {
			if (count < (getAllDataCount() / 2)) {
				return judge(node);
			} else {
				return !judgeReverse(node);
			}
		} else {
			if (count < (getAllDataCount() / 2)) {
				return !judge(node);
			} else {
				return judgeReverse(node);
			}
		}
	}

	@Override
	public boolean validte() {
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
	
	protected boolean contain(char[] dept, char src) {
		for (char deptChar : dept) {
			if (src == deptChar) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean contain(int[] dept, int src) {
		for (int deptInt : dept) {
			if (src == deptInt) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean contain(String[] dept, String src) {
		for (String deptStr : dept) {
			if (src.equals(deptStr)) {
				return true;
			}
		}
		return false;
	}
	
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
	
	protected char[] parse(int[] array) {
		char[] data = new char[array.length];
		for (int i = 0;i < array.length;i++) {
			data[i] = (char) (array[i] + '0');
		}
		return data;
	}
	
	protected int[] parse(char[] array) {
		int[] data = new int[array.length];
		for (int i = 0;i < array.length;i++) {
			data[i] = (int)(array[i]) - (int)('0');
		}
		return data;
	}
	
	protected abstract int getAllDataCount();
	
	protected abstract boolean judge(Node node);
	
	protected abstract boolean judgeReverse(Node node);

}
