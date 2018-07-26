package com.yeskery.filter;

/**
 * 过滤器接口，用来对节点进行过滤
 *
 * @author yeskery
 * @date 2018-07-26 12:23
 */
public interface Filter {

	/**
	 * 使用该方法对节点进行过滤
	 * @param retain {@link Filter#isRetain()}
	 * @param node 需要进行过滤的节点
	 * @return true 代表节点通过该过滤器，false 代表节点未通过该过滤器
	 */
	boolean filter(boolean retain, Node node);

	/**
	 * 判断该过滤器是否有效
	 * @return true 代表该过滤器有效，false 代表该过滤器无效
	 */
	boolean validate();

	/**
	 * 获取过滤器是保留还是删除状态
	 * @return true 代表保留状态，false 代表删除状态
	 */
	boolean isRetain();

}
