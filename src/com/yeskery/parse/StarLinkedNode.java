package com.yeskery.parse;

import com.yeskery.filter.NumberFormatter;

import java.util.*;

/**
 * 二三星互转工具
 * @author yeskery
 * @date 2018-07-26 17:56
 */
public class StarLinkedNode {
    /**
     * 节点类
     */
    private static class Node {
        /**
         * 数据
         */
        int data;
        /**
         * 上一个节点
         */
        Node prev;
        /**
         * 下一个节点
         */
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 节点的双向循环链表
     */
    private static class LinkedNode {
        private static final int STAR_MIN_COUNT = 2;

        private Node top;

        private Node tail;

        private int size = 0;

        /**
         * 添加一个新节点
         * @param value 节点值
         */
        public void add(int value) {
            if (!repeat(value)) {
                if (top == null && tail == null) {
                    top = new Node(value);
                } else if (top != null && tail == null) {
                    Node node = new Node(value);
                    top.next = node;
                    node.prev = top;
                    tail = node;
                } else {
                    Node node = new Node(value);
                    node.prev = tail;
                    node.next = top;
                    tail.next = node;
                    tail = node;
                    top.prev = tail;
                }
                size++;
            }
        }

        private List<Integer> threeStarForeachNodeGroup(int data) {
            int[] dataArray = parseToIntArray(data);
            for (int i : dataArray) {
                add(i);
            }
            List<Integer> result = new LinkedList<>();
            Node node = top;
            do {
                Node tmp = top;
                do {
                    result.add(parseToInt(node.data, tmp.data));
                    tmp = tmp.next;
                } while (tmp != top && tmp != null);
                node = node.next;
            } while (node != top && node != null);
            return result;
        }

        private List<Integer> twoStarForeachNodeGroup(int data) {
            int[] dataArray = parseToIntArray(data);
            for (int i : dataArray) {
                add(i);
            }
            List<Integer> result = new LinkedList<>();
            Node node = top;
            do {
                Node tmp = top;
                do {
                    Node interim = top;
                    do {
                        result.add(parseToInt(node.data, tmp.data, interim.data));
                        interim = interim.next;
                    } while (interim != top && interim != null);
                    tmp = tmp.next;
                } while (tmp != top && tmp != null);
                node = node.next;
            } while (node != top && node != null);
            return result;
        }

        private List<Integer> twoStarForeachNodeOnly(int data) {
            int[] dataArray = parseToIntArray(data);
            Arrays.sort(dataArray);
            for (int i : dataArray) {
                add(i);
            }
            List<Integer> result = new LinkedList<>();
            if (size < STAR_MIN_COUNT) {
                return result;
            }
            Node node = top;
            do {
                Node tmp = top;
                do {
                    result.add(parseToInt(node.data, tmp.data, tail.data));
                    tmp = tmp.next;
                } while (tmp != top && tmp != null);
                node = node.next;
            } while (node != tail  && node != null);
            return result;
        }

        private List<Integer> threeStarForeachNodeOnly(int data) {
            int[] dataArray = parseToIntArray(data);
            Arrays.sort(dataArray);
            for (int i : dataArray) {
                add(i);
            }
            List<Integer> result = new LinkedList<>();
            if (size < STAR_MIN_COUNT) {
                return result;
            }
            Node node = top;
            do {
                Node tmp = node.next;
                do {
                    result.add(parseToInt(node.data, tmp.data));
                    tmp = tmp.next;
                } while (tmp != top && tmp != null);
                node = node.next;
            } while (node != tail);
            return result;
        }

        private Integer parseToInt(int... units) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int unit : units) {
                stringBuilder.append(unit);
            }
            return Integer.parseInt(stringBuilder.toString());
        }

        private int[] parseToIntArray(int data) {
            String dataStr = String.valueOf(data);
            char[] charArray = dataStr.toCharArray();
            int[] result = new int[charArray.length];
            for (int i = 0;i < charArray.length;i++) {
                result[i] = (int)(charArray[i]) - (int)('0');
            }
            return result;
        }

        private boolean repeat(int data) {
            if (top == null) {
                return false;
            }
            if (tail == null) {
                return top.data == data;
            }
            if (top.prev == null && tail.next == null) {
                Node node = top;
                while (node != null) {
                    if (node.data == data) {
                        return true;
                    }
                    node = node.next;
                }
            }
            Node node = top;
            while (node != tail) {
                if (node.data == data) {
                    return true;
                }
                node = node.next;
            }
            return false;
        }
    }

    /**
     * 三星转二星（直）
     * @param dataArray 需要转换的数据数组
     * @return 转换后的数据集合
     */
    public Set<String> threeToTwoDirect(int[] dataArray) {
        Set<String> set = new TreeSet<>();
        for (int data : dataArray) {
            List<Integer> list = new LinkedNode().threeStarForeachNodeGroup(data);
            List<String> resultList = new ArrayList<>(list.size());
            for (int num : list) {
                resultList.add(NumberFormatter.format(num, 2));
            }
            set.addAll(resultList);
        }
        return set;
    }

    /**
     * 二转三星（直）
     * @param dataArray 需要转换的数据数组
     * @return 转换后的数据集合
     */
    public Set<String> twoToThreeDirect(int[] dataArray) {
        Set<String> set = new TreeSet<>();
        for (int data : dataArray) {
            List<Integer> list = new LinkedNode().twoStarForeachNodeGroup(data);
            List<String> resultList = new ArrayList<>(list.size());
            for (int num : list) {
                resultList.add(NumberFormatter.format(num, 3));
            }
            set.addAll(resultList);
        }
        return set;
    }

    /**
     * 三星转二星（组）
     * @param dataArray 需要转换的数据数组
     * @return 转换后的数据集合
     */
    public Set<String> threeToTwoGroup(int[] dataArray) {
        Set<String> set = new TreeSet<>();
        for (int data : dataArray) {
            List<Integer> list = new LinkedNode().threeStarForeachNodeOnly(data);
            List<String> resultList = new ArrayList<>(list.size());
            for (int num : list) {
                resultList.add(NumberFormatter.format(num, 2));
            }
            set.addAll(resultList);
        }
        return set;
    }

    /**
     * 二星转三星（组）
     * @param dataArray 需要转换的数据数组
     * @return 转换后的数据集合
     */
    public Set<String> twoToThreeGroup(int[] dataArray) {
        Set<String> set = new TreeSet<>();
        for (int data : dataArray) {
            List<Integer> list = new LinkedNode().twoStarForeachNodeOnly(data);
            List<String> resultList = new ArrayList<>(list.size());
            for (int num : list) {
                resultList.add(NumberFormatter.format(num, 3));
            }
            set.addAll(resultList);
        }
        return set;
    }

}
