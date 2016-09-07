package com.hust.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hust.cluster.Canopy;
import com.hust.convertor.Convertor;
import com.hust.convertor.TFIDFConvertor;

public class ClusterTest {

    private List<String[]> segList;

    @Before
    public void init() {
        segList = new ArrayList<String[]>();
        String[] str1 = { "12岁", "女生", "宿舍", "表演", "上吊", "身亡", "室友", "错过", "2次", "施救", "机会" };
        String[] str2 = { "12岁", "女生", "宿舍", "内", "上吊", "室友", "以为", "玩笑", "错失", "施救", "机会" };
        String[] str3 = { "12岁", "住校", "女生", "宿舍", "身亡" };
        String[] str4 = { "陕西", "手机", "早报", "0402" };
        String[] str5 = { "四川", "通报", "小学", "女生", "死亡", "事件", "系", "意外", "排除", "他", "杀" };
        String[] str6 = { "陕西", "手机", "早报", "0402" };
        String[] str7 = { "12岁", "住校", "女生", "宿舍", "身亡" };
        String[] str8 = { "12岁", "女生", "宿舍", "内", "上吊", "室友", "以为", "玩笑", "错失", "施救", "机会" };
        segList.add(str8);
        segList.add(str7);
        segList.add(str6);
        segList.add(str5);
        segList.add(str4);
        segList.add(str3);
        segList.add(str2);
        segList.add(str1);
    }

    @Test
    public void cluster() {
        Convertor convertor = new TFIDFConvertor();
        convertor.setList(segList);
        List<double[]> vectors = convertor.getVector();
        Canopy canopy = new Canopy();
        canopy.setVectors(vectors);
        canopy.setThreshold(0.9f);
        canopy.clustering();
        List<List<Integer>> result = canopy.getResultIndex();
        for (List<Integer> set : result) {
            for (int index : set) {
                String[] array = segList.get(index);
                for (String str : array) {
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
