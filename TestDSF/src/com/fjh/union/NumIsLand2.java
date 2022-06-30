package com.fjh.union;

import java.util.ArrayList;
import java.util.List;

public class NumIsLand2 {
    public static List<Integer> numIsLand(int[][]points, int m, int n){
        List<Integer> list = new ArrayList<>();
        if(points == null || points.length < 1){
            return list;
        }
        UnionFind uf = new UnionFind(m,n);
        for (int[]point:
             points) {
            int ans=uf.connect(point[0],point[1]);
            list.add(ans);
        }
        return  list;
    }
    public static class UnionFind{
        private  int []parent;
        private int[]size;
        private int[]help;
        private  int col;
        private int row;
        private  int sets;
        public UnionFind(int m,int n){
            col = m;
            row = n;
            sets = 0;
            int len = col * row;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }
        private  int index(int i,int j){
            return i * col * j;
        }
        private int find(int index){
            int k = 0;
            while (parent[index] != index) {
                help[k++] = index;
                index = parent[index];
            }
            for (int i1 = 0; i1 < k; i1++) {
                parent[help[i1]] = index;
            }
            return index;
        }
        public void union (int i1,int j1,int i2 ,int j2){
            if(i1 < 0 || j1 >= col ||i1 >= row || j1 < 0|| i2 < 0 || j2 >= col || i2 >= row || j2 < 0){
                return ;
            }
            int index1 = index(i1,j1);
            int index2 = index(i2,j2);
            if(size[index1] == 0 || size[index2] == 0){
                return ;
            }
            int f1 = find(index1);
            int f2 = find(index2);
            if(f1 !=f2){
                if(size[f1] >= size[f2]){
                    parent[f2] = f1;
                    size[f1] += size[f2];
                }else{
                    parent[f1] = f2;
                    size[f2] += size[f1];
                }
                sets--;
            }
        }
        public int connect(int i ,int j){
            int index = index(i,j);
            if(size[index] == 0){
                parent[index] = index;
                size[index] = 1;
                sets++;
            }
            union(i,j,i - 1,j);
            union(i,j,i + 1,j);
            union(i,j,i,j -1);
            union(i,j,i,j + 1);
            return sets;
        }
    }
}
