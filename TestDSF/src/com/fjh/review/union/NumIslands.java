package com.fjh.review.union;

/**
 * @author 21561
 */
public class NumIslands {
    int[] fa;
    int size;
    public void init(int m,int n,char[][]gird){
        fa = new int[n * m ];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                int index = index(i , j, n);
                fa[index] = index;
                if(gird[i][j] == '1'){
                    size++;
                }
            }
        }
    }
    public int find(int x){
        return x == fa[x] ? x : (fa[x] = find(fa[x]));
    }
    public void union(int x , int y){
        int fx = find(x);
        int fy = find(y);
        if(fx != fy){
            fa[fx]  = fy;
            size--;
        }
    }
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length ;
        init(m,n,grid);
        for(int i = 0 ; i < m ; i++){
            for(int j = 0; j < n ; j++){
                if(grid[i][j] == '0'){
                    continue;
                }
                int index = index(i,j,n);
                if(j + 1 < n && grid[i][j + 1] == '1'){
                    union(index,index(i,j + 1,n));
                }
                if(i + 1 < m  && grid[i + 1][j] == '1'){
                    union(index,index(i + 1, j , n));
                }
            }
        }

        return size;
    }
    public int index(int i , int j ,int col){
      return  i * col + j;
    }
    public int numIslands2(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int size = 0;
        int m = grid.length;
        int n  = grid[0].length;
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n ; j++){
                if(grid[i][j] == '1'){
                    info(grid, i, j, m, n);
                    size++;
                }
            }
        }
        return size;
    }
    public void info(char[][]grid , int i , int j , int m ,int n ){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1'){
            return;
        }

            grid[i][j] = '2';
            info(grid,i -1,j , m, n );
            info(grid,i +1,j , m, n );
            info(grid,i ,j - 1 , m, n );
            info(grid,i,j + 1 , m, n );
    }

}
