package com.jd.leetcode.greedy;

/**
 * 45. 跳跃游戏 II
 *
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *
 *
 * 思路：
 * 1、每次都在跳跃范围内，找到能跳到最远的那个点
 * 所以第一重循环是当前点位到当前点位的最远点；第二重循环也是当前点位到当前点位能到的最远点；
 * 2、在第二重循环内找到能跳到最远的点，然后 跳跃次数+1
 * 3、判断当前点位是不是已经满足
 */
public class canJumpⅡ {

    public static int jump(int[] nums) {
        if (nums.length<2){
            return 0;
        }
        //跳跃次数
        int jumpFrequency = 1;
        //从零点开始能跳到的最远位置
        int maxLength = nums[0];
        //
        int index = 0;
        for (int i=index; i<nums.length; i++){
            int nowMax = maxLength;
            if (nowMax>=nums.length-1) return jumpFrequency;
            for (int j=i; j<=nowMax && j<nums.length; j++){
                if (nums[j] + j > maxLength){
                    maxLength = nums[j] + j;
                    index = j;
                }
            }
            jumpFrequency++;
        }
        return jumpFrequency;
    }

    /**
     * 在循环中，每次走到当前能到的最大跳远范围的时候，就调过来；然后在循环中如果遇到可以跳到终点，就直接跳到终点；
     *
     * 贪心分析：在每次的跳跃最大范围内，找到下一次能跳到的最大范围，并每次在最大范围的地方加一次跳跃次数
     */
    public static int jump2(int[] nums){
        if (nums.length<2){
            return 0;
        }
        int jumpFrequency = 0;
        int maxLength = 0;
        int curLength = 0;
        for (int i=0; i<= maxLength && i<nums.length; i++){
            if (nums[i] + i>maxLength){
                maxLength = nums[i] + i;
            }
            if (maxLength>=nums.length-1){
                jumpFrequency++;
                break;
            }
            if (i == curLength){
                jumpFrequency++;
                curLength = maxLength;
            }

        }

        return jumpFrequency;

    }

    public static void main(String[] args) {
        jump(new int[]{1,1,1,1});
    }


}
