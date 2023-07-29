package com.fjh.review.union;

/**
 * @author 21561
 */
public class Union {
    int[]fa;
    public void init(int n){
        fa = new int[n];
        for(int i = 0; i < n  ; i++){
            fa[i] = i;
        }
    }
    public int find(int x){
        return  x == fa[x] ? x :  (fa[x] = find(fa[x]));
    }

    public void union(int x ,int y){
        fa[find(x)] = find(y);
    }

}
