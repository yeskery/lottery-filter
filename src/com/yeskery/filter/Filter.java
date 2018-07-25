package com.yeskery.filter;

public interface Filter {
	
	boolean filter(boolean retain, Node node);
	
	boolean validte();
	
	boolean isRetain();

}
