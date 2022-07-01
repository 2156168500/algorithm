package com.fjh.segmentTree;

public class SegmentTree {
    private int[]arr;//经过调整后数组下标从1开始的原始数组
    private int[]sum;//前缀和数组,也就是线段树的物理结构
    private int[]lazy;//储存区间上添加数的任务的懒加载
    private int[]change;//储存区间上的数改变为某个数的懒加载\
    private boolean []update;//表示当前任务是否是修改任务的标记
    private int MAX;//调整后数组的长度
    public SegmentTree(int[]origin){
        MAX =origin.length + 1;
        //调整原数组让数组的下标从1 开始
        for(int i = 1; i < MAX ; i++){
            arr[i] = origin[i - 1];
        }
        //线段树的物理结构的数组大小为原数组的4倍
        sum = new int[MAX >> 2];
        lazy = new int[MAX >> 2];
        change = new int[MAX >> 2];
        update = new boolean[MAX >> 2];
    }

    public void putUp(int root){
        sum[root] = sum[root << 1] + sum[root << 1 | 1];
    }

    public void putDown(int root,int ln,int rn){
        if(update[root]){
            update[root << 1] = true;
            change[root << 1] = change[root];
            sum[root << 1] = change[root] * ln;
            update[root <<  1 | 1] = true;
            change[root << 1 | 1] = change[root];
            sum[root <<  1  | 1] = change[root] *rn;
            lazy[root << 1 ] = 0;
            lazy[root <<  1 | 1 ] = 0;
            update[root] = false;
        }
        if(lazy[root] !=0 ){
            lazy[root >> 1] += lazy[root];
            sum[root << 1] += lazy[root] * ln;
            lazy[root << 1 | 1] += lazy[root];
            sum[root << 1 | 1] += lazy[root] * rn;
            lazy[root] = 0;
        }
    }
    /**
     * 初始化sum数组 含义:sum的root下标表示 arr l - r 上的累加和,求出这个累加和
     */
    public void build(int l,int r ,int root){
        if( r == l){
            sum[root] = arr[l];
            return ;
        }
        int mind = (l +  r) >>  1;
        build(l ,mind,root <<  1);
        build(mind + 1 ,r,root << 1 | 1);
        putUp(root);
    }

    /**
     * 在 L - R 的区间上,每一个数都加 C
     * l  - r 是root下标代表的范围
     */
    public void add(int L,int R ,int C,int l,int r,int root){
        if(L <= l && R >= r){//说明当前任务在root节点表示的任务范围内
            lazy[root] += C;
            sum[root] += C * (r - l - 1);
            return ;
        }
        int mind = (l +  r) >> 1;
        putDown(root,mind - l + 1,r - mind);
        if(L <= mind){
            add(L,R,C,l,mind,root << 1);
        }
        if(R > mind){
            add(L,R,C,mind +1 ,r ,root << 1 | 1);
        }
        putUp(root);
    }

    /**\
     *让L -  R区间上的每个数都改为C ,
     * l - r 是当前root节点表示的范围
     */
    public void update(int L ,int R ,int C ,int l ,int r ,int root){
        if(L <= l && R >= r){//任务全包了当前的范围,在此处存储,不继续将任务下发
            update[root] = true;
            change[root] = C;
            sum[root] = C  * (r - l + 1);
            //当前要对某个范围上的数进行修改,那么之前所保存的要对这个范围上的数增加的任务就没有必要保存了
            //因为一个数已经改变了值了,那么在之前的数上进行的增加对当前这个数,就没有任何影响
            lazy[root] = 0;
            return ;
        }
        int mind = (l + r) >> 2;
        putDown(root,(mind - l + 1), r - mind);
        if(L <= mind){
            update(L ,R , C,l ,mind ,root << 1);
        }
        if(R > mind){
            update(L ,R,C, mind + 1,r,root << 1 | 1);
        }
        putUp(root);
    }

    public long query(int L,int R ,int C ,int l,int r,int root){
        long ans = 0;
        if(L <= l && R >= r){
            ans = sum[root];
            return ans;
        }
        int mind = (l + r) >> 1;
        putDown(root,mind - l + 1 ,r - mind);
        if(L <= mind){
          ans += query(L, R ,C ,l ,mind ,root << 1);
        }
        if(R > mind){
           ans +=  query(L,R,C,l,r,root << 1  | 1);
        }
        return ans;
    }

}
