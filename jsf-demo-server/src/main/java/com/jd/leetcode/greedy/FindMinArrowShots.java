package com.jd.leetcode.greedy;


import com.jd.fastjson.JSON;

import java.util.Arrays;

/**
 * 452. 用最少数量的箭引爆气球
 *
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 *
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 *
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 *
 *
 * 贪心思路：
 * 先按照每个起球的起始坐标排序；
 * 举例：按照第一个气球的起止坐标就是第一支箭必须射在这之内；第二个箭如果没有重叠空间，那么射箭就需要+1；如果有重叠空间，就更新两支箭的重叠空间，让后面的来看是否重合
 */
public class FindMinArrowShots {

    public static int findMinArrowShots(int[][] points) {
        //让起始位置小的在前面
        Arrays.sort(points,(a,b)->{
            if (a[0]>b[0]){
                return 1;
            }else {
                return -1;
            }
        });
        int arrowNum = 1;
        for(int i=1; i<points.length; i++){
            //此气球开始的左边已经比上一个气球的覆盖范围更远，因此前面要射一箭了
            if (points[i][0] > points[i-1][1]){
                arrowNum++;
            }else {
                //还在范围内，更新覆盖范围
                points[i][1] = Math.min(points[i][1], points[i-1][1]);
            }
        }
        return arrowNum;


    }

    public static void main(String[] args) {
        int[][] aa = new int[2][];
        aa[0] = new int[]{-2147483646,-2147483645};
        aa[1] = new int[]{2147483646,2147483647};
        Arrays.sort(aa,(a,b)->a[0]-b[0]);
        //a[0]=2147483646
        //b[0]=-2147483636
        //int 类型的取值范围是从 -2,147,483,648 到 2,147,483,647
        //a[0]-b[0] > 2,147,483,647 出现溢出情况
        //可以使用这种方法来计算：
        Arrays.sort(aa,(a,b)->{
            if (a[0]>b[0]){
                return 1;
            }else {
                return -1;
            }
        });

        System.out.println(JSON.toJSONString(aa));
        System.out.println(findMinArrowShots(aa));
    }
}