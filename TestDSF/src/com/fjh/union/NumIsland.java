package com.fjh.union;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumIsland {
    public int numIslands(char[][] grid) {
        int landSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    landSize++;
                    InterFace(grid,i,j);
                }
            }
        }
        return landSize;
    }
    public void InterFace(char[][]grid,int i , int j){
        if(i < 0 ||i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1'){
            return ;
        }
        grid[i][j] = 2;
        InterFace(grid,i - 1,j);
        InterFace(grid,i + 1,j);
        InterFace(grid,i ,j - 1);
        InterFace(grid,i ,j + 1);
    }
    public int numIslands2(char[][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        UnionFind unionFind = new UnionFind(grid);
        for(int i = 1;i < grid[0].length; i++){
            if(grid[0][i] == '1' && grid[0][i-1] == '1'){
                unionFind.union(0,i,0,i-1);
            }
        }
        for(int i = 1; i < grid.length; i++){
            if(grid[i][0] == '1' && grid[i-1][0] == '1' ){
                unionFind.union(i,0,i-1,0);
            }
        }
        for(int i = 1 ;i < grid.length; i++){
            for (int j = 1; j < grid[0].length; j++){
                if(grid[i][j] == '1' && grid[i-1][j] == '1'){
                    unionFind.union(i ,j,i - 1,j);
                }
                if(grid[i][j] == '1' && grid[i][j-1] == '1'){
                    unionFind.union(i,j,i,j - 1);
                }
            }
        }
        return unionFind.sets;
    }
    public static class UnionFind{
        public int[]parent;
        public int[]help;
        public int[]size;
        public int sets;
        public int len;
        public int col;
        public UnionFind(char[][]chars){
            int cow = chars[0].length;
            int row = chars.length;
            len = cow * row;
            col = cow;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int i = 0; i < row ; i++){
                for(int j = 0; j < cow; j++){
                   if(chars[i][j] == '1'){
                       int point = index(i,j);
                       parent[point] = point;
                       size[point] = 1;
                       sets++;
                   }
                }
            }
        }
        private int find(int index){
            int k = 0;
            while(index != parent[index]){
                index = parent[index];
                help[k++] = index;
            }
            for (int i = 0; i < k; i++) {
                parent[help[i]] = index;
            }
            return index;
        }
        public void union(int c1,int r1,int c2,int r2){
            int index1 = index(c1,r1);
            int index2 = index(c2,r2);
            int parent1 = find(index1);
            int parent2 = find(index2);
            if(parent1!= parent2){
                if(size[parent1] >= size[parent2]){
                    parent[parent2] = parent1;
                    size[parent1] += size[parent2];
                    size[parent2] = 0;
                }else{
                    parent[parent1] = parent2;
                    size[parent2] += size[parent1];
                    size[parent1] = 0;
                }
                sets--;
            }
        }

        private  int index(int i,int j){
            return i * col + j;
        }
    }

}
