package com.fjh.heapsort;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class HeapGreat<T> {
    private ArrayList<T>  heap = new ArrayList<>();//数组->堆
    private HashMap<T,Integer> indexMap = new HashMap<>();//方向索引表->用来记录元素在数组中的位置
    private Comparator<T> comparator ;//比较器,决定堆中元素的比较方式
    private int heapSize;//堆中元素的个数
    public HeapGreat(Comparator<T>c){
        this.comparator = c;
    }
    //向上调整的方法
    public void heapInset(int index){
        while (index < 0 && comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0){
            swap(index,(index - 1) / 2);
            index = (index -1) / 2;
        }
    }
    //向下调整
    public void heapIfy(int index){
        int left = (index * 2) + 1;
        while (left < heapSize){
            int  minIndex = left + 1 < heapSize &&
                    comparator.compare(heap.get(left + 1), heap.get(left)) < 0
                    ? left : left+1;
            minIndex = comparator.compare(heap.get(minIndex),heap.get(index)) < 0?minIndex : index;
            if(index == minIndex){
                break;
            }
            swap(minIndex,index);
            index = minIndex;
            left = (index + 1) / 2;
        }
    }
    //插入操作
    public void put(T obj){
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInset(heapSize);
        heapSize++;
    }
    //弹出操作
    public T pop(){
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        heap.remove(heapSize - 1);
        indexMap.remove(ans);
        heapSize--;
        heapIfy(0);
        return ans;
    }
    //调整操作
    public void resign (T obj){
        heapIfy(indexMap.get(obj));
        heapInset(indexMap.get(obj));
    }
    //删除操作
    public void remove(T obj){
        T replace = heap.get(heapSize -  1);
        int index = indexMap.get(obj);
        heap.remove(--heapSize );
        indexMap.remove(obj);
        if(obj != replace){
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }
    }
    //交换
    public void swap(int a ,int b){
        T t1 = heap.get(a);
        T t2 = heap.get(b);
        heap.set(a,t2);
        heap.set(b,t1);
        indexMap.replace(t2,a);
        indexMap.replace(t1,b);
    }
}
