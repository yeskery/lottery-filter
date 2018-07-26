package com.yeskery.filter.test;

import com.yeskery.filter.*;

import java.util.List;

public class FourStarTest {
    public static void main(String[] args) {
        long initStart = System.currentTimeMillis();
        NodeGenerator nodeGenerator = new NodeGenerator(4);
        List<Node> nodeList = nodeGenerator.generate();
        long initEnd = System.currentTimeMillis();
        System.out.println("初始化数据用时：" + (initEnd - initStart) + "ms");

        System.out.println("第一次查询");

        long checkStart = System.currentTimeMillis();
        FilterChain filterChain = new FilterChain();
        filterChain.add(new IntentFilter(true, 2, 3, 4));
        filterChain.add(new PositionFilter(4, 1, true, 3, 4, 7));
        filterChain.add(new PositionFilter(4, 2, true, 4, 5, 7, 8));
        filterChain.add(new SpanFilter(true, 4, 5, 6, 7));
        filterChain.add(new RemainderFilter(true, 5, 6, 7, 8));
        filterChain.add(new SumFilter(4, true, 5, 6, 15, 16, 25, 26, 35, 36));
        filterChain.add(new SizeFilter(4, true, "0001", "0010", "0011", "1000", "1001",
                "1010", "1111"));
        filterChain.add(new DualFilter(5, true, "0001", "0010", "0011", "1000", "1001",
                "1010", "1111"));
        List<Node> resultNodeList = filterChain.filter(nodeList);
        long checkEnd = System.currentTimeMillis();
        System.out.println("查询用时：" + (checkEnd - checkStart) + "ms, 共查询到：" + resultNodeList.size() + "个Node");
        System.out.println("第一个Node: " + resultNodeList.get(0) + ", 最后一个Node: " + resultNodeList.get(resultNodeList.size() - 1));

        System.out.println("第二次查询");
        //清空过滤链
        filterChain.clear();
        checkStart = System.currentTimeMillis();
        filterChain.add(new IntentFilter(false, 2, 3));
        filterChain.add(new PositionFilter(4, 1, false, 3, 4));
        filterChain.add(new PositionFilter(4, 2, true, 5, 6));
        filterChain.add(new PositionFilter(4, 4, false, 8, 9));
        filterChain.add(new SpanFilter(false, 4));
        filterChain.add(new RemainderFilter(false, 3));
        filterChain.add(new SumFilter(4, true, 22, 23, 24, 25, 26, 32, 33, 34, 35, 36));
        filterChain.add(new SizeFilter(4, false, "1000", "1001", "1010", "1111"));
        filterChain.add(new DualFilter(4, true, "0001", "0010", "0011", "1000", "1001",
                "1010"));
        resultNodeList = filterChain.filter(nodeList);
        checkEnd = System.currentTimeMillis();
        System.out.println("查询用时：" + (checkEnd - checkStart) + "ms, 共查询到：" + resultNodeList.size() + "个Node");
        System.out.println("第一个Node: " + resultNodeList.get(0) + ", 最后一个Node: " + resultNodeList.get(resultNodeList.size() - 1));
    }
}
