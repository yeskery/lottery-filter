package com.yeskery.parse.test;

import com.yeskery.parse.StarLinkedNode;

import java.util.Set;

/**
 * @author yeskery
 * @date 2018-07-27 15:16
 */
public class ThreeToTwoTest {
    public static void main(String[] args) {
        StarLinkedNode starLinkedNode = new StarLinkedNode();
        int[] data = new int[100];
        for (int i = 0;i < data.length;i++) {
            data[i] = i;
        }
        Set<String> set = starLinkedNode.twoToThreeDirect(data);
        System.out.println(set.size());
        set.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();

        set = starLinkedNode.twoToThreeGroup(data);
        System.out.println(set.size());
        set.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();

        data = new int[1000];
        for (int i = 0;i < data.length;i++) {
            data[i] = i;
        }

        set = starLinkedNode.threeToTwoDirect(data);
        System.out.println(set.size());
        set.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();

        set = starLinkedNode.threeToTwoGroup(data);
        System.out.println(set.size());
        set.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();
    }
}
