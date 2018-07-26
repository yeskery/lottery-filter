package com.yeskery.filter.test;

import java.util.Iterator;
import java.util.List;

import com.yeskery.filter.DualFilter;
import com.yeskery.filter.FilterChain;
import com.yeskery.filter.IntentFilter;
import com.yeskery.filter.Node;
import com.yeskery.filter.NodeGenerator;
import com.yeskery.filter.PositionFilter;
import com.yeskery.filter.RemainderFilter;
import com.yeskery.filter.SizeFilter;
import com.yeskery.filter.SpanFilter;
import com.yeskery.filter.SumFilter;

public class FiveStarIteratorTest {

	public static void main(String[] args) {
		long initStart = System.currentTimeMillis();
		NodeGenerator nodeGenerator = new NodeGenerator(5);
		Iterator<Node> iterator = nodeGenerator.iterator();
		long initEnd = System.currentTimeMillis();
		System.out.println("初始化数据用时：" + (initEnd - initStart) + "ms");
		
		System.out.println("第一次查询");
		
		long checkStart = System.currentTimeMillis();
		FilterChain filterChain = new FilterChain();
		filterChain.add(new IntentFilter(true, 1, 2, 3));
		filterChain.add(new PositionFilter(5, 1, true, 1, 2, 3));
		filterChain.add(new PositionFilter(5, 2, true, 3, 4, 5));
		filterChain.add(new PositionFilter(5, 3, true, 2, 3, 4));
		filterChain.add(new SpanFilter(true, 3, 4, 5, 6));
		filterChain.add(new RemainderFilter(true, 3, 4, 5, 6, 7, 8));
		filterChain.add(new SumFilter(5, true, 12, 13, 14, 23, 24, 26, 27, 33, 34, 43, 44));
		filterChain.add(new SizeFilter(5, true, "00001", "01000", "01001", "01010", "01011", 
				"01111", "10000", "10001", "10010", "10011", "11010"));
		filterChain.add(new DualFilter(5, true, "00001", "00010", "01000", "01001", "01111",
				"10000", "10110", "10111"));
		List<Node> resultNodeList = filterChain.filter(iterator, 10);
		long checkEnd = System.currentTimeMillis();
		System.out.println("查询用时：" + (checkEnd - checkStart) + "ms, 共查询到：" + resultNodeList.size() + "个Node");
		System.out.println("第一个Node: " + resultNodeList.get(0) + ", 最后一个Node: " + resultNodeList.get(resultNodeList.size() - 1));
		
		System.out.println("第二次查询");
		//清空过滤链
		filterChain.clear();
		//重新使用需要重新获取迭代器
		iterator = nodeGenerator.iterator();
		checkStart = System.currentTimeMillis();
		filterChain.add(new IntentFilter(false, 2, 3, 4));
		filterChain.add(new PositionFilter(5, 1, false, 3, 4, 5));
		filterChain.add(new PositionFilter(5, 2, false, 3, 4, 5));
		filterChain.add(new PositionFilter(5, 3, false, 1, 2, 3));
		filterChain.add(new PositionFilter(5, 5, false, 4, 5, 6));
		filterChain.add(new SpanFilter(false, 0, 4, 8));
		filterChain.add(new RemainderFilter(false, 2, 4, 6, 7));
		filterChain.add(new SumFilter(5, true, 12, 22, 23, 24, 25, 32, 34, 35, 45));
		filterChain.add(new SizeFilter(5, false, "00010", "00011", "01001", "01010", "10000",
				"10001", "10111", "11000", "11110", "11111"));
		filterChain.add(new DualFilter(5, true, "00010", "00011", "01001", "01010", "10000",
				"10001", "10111", "11000", "11110", "11111"));
		resultNodeList = filterChain.filter(iterator, 10);
		checkEnd = System.currentTimeMillis();
		System.out.println("查询用时：" + (checkEnd - checkStart) + "ms, 共查询到：" + resultNodeList.size() + "个Node");
		System.out.println("第一个Node: " + resultNodeList.get(0) + ", 最后一个Node: " + resultNodeList.get(resultNodeList.size() - 1));
	}

}
