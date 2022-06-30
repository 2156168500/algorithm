package com.fjh.union;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindCircleNum_leetCode547 {
    public static int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0 ;i < isConnected.length; i++){
            for (int j = i+1 ;j < isConnected[0].length;j++){
                if (isConnected[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.setSize;
    }
    public static class UnionFind {
        int[] parent;
        int[] help;
        int[] size;
        int setSize;
        public UnionFind(int N){
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            setSize = N;
        }
        private int findParent(int i){
            int k = 0;
            while (i !=parent[i]){
                help[k++] = i;
                i = parent[i];
            }
            for (int i1 = 0; i1 < k; i1++) {
                parent[help[i1]] = i;
            }
            return i;
        }
        public void union(int i,int j){
            int parent1 = findParent(i);
            int parent2 = findParent(j);
            if(parent1 == parent2){
                return ;
            }
            int size1 = size[parent1];
            int size2 = size[parent2];
            int more = size1 > size2 ? parent1 :parent2;
            int less = more == parent1 ? parent2 : parent1;
            parent[less] = more;
            size[more] = size1 + size2;
            size[less] = 0;
            setSize--;
        }
    }

    public static void main(String[] args) {
        int[][]arr = {{1,1,1},{1,1,1},{1,1,1}};
        findCircleNum(arr);
    }
}
