package com.jd.ljy.xiaoxinxin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Test {
    public static void main(String[] args) {
        B b = new B();
        System.out.println(new B().getValue());
    }

}

class A {
    protected int value;

    static {
        System.out.println("执行A的静态类");
    }

    public A() {
        System.out.println("执行A的无参构造变量");
    }

    public A(int v) {
        System.out.println("执行A的构造变量");
        setValue(v);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        try {
            value++;
            return value;
        } finally {
            this.setValue(value);
            System.out.println(value);
        }
    }

    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        int dp[][] = new int[m][n];
        return dfs2(m-1,n-1,dp, grid);
        //return dfs(0,0,m,n,grid,max);
    }

    private int dfs2(int m, int n, int[][] dp, int[][] grid) {
        if(m<0 || n<0){
            return 0;
        }
        if (dp[m][n] > 0){
            return dp[m][n];
        }
        return dp[m][n] = grid[m][n] + Math.max(dfs2(m-1,n,dp,grid),dfs2(m,n-1,dp,grid));
    }

    private int dfs(int i, int j, int m, int n, int[][] grid, int max) {
        max += grid[i][j];
        if(i<m-1 && j<n-1){
            return Math.max(dfs(i+1,j,m,n,grid,max),dfs(i,j+1,m,n,grid,max));
        }else if(i==m-1 && j<n-1){
            return Math.max(max,dfs(i,j+1,m,n,grid,max));
        }else if(i<m-1 && j==n-1){
            return Math.max(max,dfs(i+1,j,m,n,grid,max));
        }
        return max;
    }


}

class B extends A {

    static {
        System.out.println("执行B的静态类");
    }

    public B() {
        super();
        System.out.println("执行B的构造变量");
        int value = getValue();
        System.out.println(value);
        setValue(value - 3);
        System.out.println(this.value);

    }

    public void setValue(int value) {
        super.setValue(2 * value);
    }

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            if (map.containsKey(names[i])) {
                int j = 1;
                Integer integer = map.get(names[i]);
                String nameNew = names[i] + "(" + (integer+j) + ")";
                while (map.containsKey(nameNew)) {
                    j++;
                    nameNew = names[i] + "(" + (integer + j) + ")";
                }
                map.put(names[i], integer + j);
                map.put(nameNew, 0);
                names[i] = nameNew;
            } else {
                map.put(names[i], 0);
            }
        }
        return names;
    }
}
