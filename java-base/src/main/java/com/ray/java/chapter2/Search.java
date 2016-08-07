package com.ray.java.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找方法
 * 面试经常听别人说泛型T,问泛型只能写T吗?回答说还有问号(?),觉得泛型只有T这么一说,实际上这个字母自己随意定的,只是基于JDK而言大家习惯使用T用于泛型而已,例如Map的实现就是<K,V>
 * 接口Comparable用于比较内容,查看JDK源码可以发现,常见的Number,Integer等都实现了该接口
 * 实现该接口后需实现compareTo方法,用于比较,通常小于返回-1,匹配返回0,大于返回1
 * 以下是Integer的compareTo实现,compareTo调用compare方法
 * public int compareTo(Integer anotherInteger) {return compare(this.value, anotherInteger.value); }
 * public static int compare(int x, int y) { return (x < y) ? -1 : ((x == y) ? 0 : 1); }
 * @author LeiYong
 */
public class Search {
    /**
     * 基础查询
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int baseSearch(List<T> list,T t){
        for (int i = 0; i < list.size(); i++) {
            T obj = list.get(i);
            if (obj.compareTo(t)==0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查询
     * @param list
     * @param t
     * @param <A>
     * @return
     */
    public static <A extends Comparable<A>> int binarySearch(List<A> list,A t){
        return binarySearch(list,t,0,list.size()-1);
    }

    /**
     * 二分查询实现
     * 何为二分查询?就是从中间开始查询,被查询的对象必须是有序的
     * 此处end为最大值开始位置所在,start为最小值开始位置所在
     * @param list
     * @param t
     * @param <X>
     * @return
     */
    public static <X extends Comparable<X>> int binarySearch(List<X> list,X t,int start,int end){
        if(start<0||end<0||start>end){
            return -1;
        }
        int binary = (start + end)/2;
        X x = list.get(binary);
        int result = t.compareTo(x);
        if (result==0){
            return binary;
        }
        if(binary==start&&binary==end){
            return -1;
        }
        //大于0表示值大于,t>x则表示需要从当前到end中寻找
        if (result>0){
            return binarySearch(list,t,binary+1,end);
        }else{
            return binarySearch(list,t,start,binary-1);
        }
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 10; i < 100; i++) {
            list.add(i);
        }
        int index = baseSearch(list,49);
        System.out.println("index:"+index);
        int benaryIndex = binarySearch(list,49);
        System.out.println("benaryIndex:"+benaryIndex);
        int benaryIndex2 = binarySearch(list,50);
        System.out.println("benaryIndex2:"+benaryIndex2);
        int benaryIndex3 = binarySearch(list,51);
        System.out.println("benaryIndex3:"+benaryIndex3);
        int benaryIndex4 = binarySearch(list,99);
        System.out.println("benaryIndex4:"+benaryIndex4);
        int benaryIndex5 = binarySearch(list,100);
        System.out.println("benaryIndex5:"+benaryIndex5);
    }
}