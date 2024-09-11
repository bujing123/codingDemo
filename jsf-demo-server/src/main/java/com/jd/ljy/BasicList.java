package com.jd.ljy;

import com.jd.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BasicList {

    /**
     * 每次扩容扩大到上一次集合的1.5倍，但是如果新增一个集合，则扩大到新集合+旧集合的长度
     *
     * fail-fast: 一旦发现遍历的同时其他人来修改，则立即抛出异常
     *
     * fail-safe:发现遍历的同时其他人来修改，应当有应对策略，比如牺牲一致性来让整个遍历完成（正在遍历的list不被修改）。
     */
    @Test
    public void TestArrayList(){
        ArrayList<Object> arrayList = new ArrayList<>();
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();  //CopyOnWriteArrayList是 fail-safe的典型代表，遍历的同时可以修改，原理是读写分离
    }

    @Test
    public void TestGetList(){
        List<String> arrayList = new ArrayList<>();
        arrayList.add("JDVA15249101138-1-2-");
        arrayList.add("JDVA15249101138-2-2-");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("packageCodeList",arrayList);
        map.put("orderCode","EO0027243069588");
        System.out.println(JSON.toJSONString(map));
        String waybillCode = "adsadadsa";
        System.out.println(waybillCode);
        System.out.println(new ArrayList<>(Arrays.asList(waybillCode)).get(0));
    }

    public List<String> toList(String waybillCode){
        waybillCode = "adsadadsa";
        System.out.println(new ArrayList<>(Arrays.asList(waybillCode)));
        return new ArrayList<>(Arrays.asList(waybillCode));
    }

    public static double sqrt(double num, double error){
        double low,high;
        if (num > 1) {
            low=0;
            high=num;
        }else {
            low=0;
            high=num+1;
        }
        //使用二分法进行检测
        double middle = 0.00;
        while (high - low >error){
            middle = (high + low) / 2;
            if(middle*middle>num){
                high=middle;
            }else {
                low=middle;
            }
        }
        return middle;
    }



    @Test
    public void Test0001(){
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("13213");
        ArrayList<String> arrayList2 = new ArrayList<>();


        BeanUtils.copyProperties(arrayList1,arrayList2);
        System.out.println(arrayList2);
    }



    public boolean Test001101(ListNode head){
        /**
         * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
         */
        //快慢指针
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode low = dummy;
        ListNode high = dummy;
        while(high != null && high.next !=null) {
            low = low.next;
            high = high.next;
            if(high != null) {
                high = high.next;
            }
        } //high已指向尾节点，low指向中间（若是偶数，则偏右）

        ListNode temp = low.next;
        low.next = null;
        //比较前后半段是否相等。
        temp = reverseList(temp);
        while(temp != null) {
            if(head.val != temp.val) {
                return false;
            }
            head = head.next;
            temp = temp.next;
        }
        return true;

    }

    /**
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next  = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }



    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++){
            arr[i][0] = scores[i];
            arr[i][1] = ages[i];
        }
        //按照分数排序，如果分数相同，则年龄小的排前面
        Arrays.sort(arr, (a,b) -> a[0] == b[0] ? a[1]-b[1] : a[0]-b[0]);
        System.out.println(JSON.toJSONString(arr));
        //排序完成后，根据年龄取最大递增
        int[] dp = new int[n];//记录每个位置最大的分数
        dp[0] = arr[0][0];
        int max = dp[0];//每次记录当前最大值
        for(int i=1; i<n; i++){
            if(arr[i][1] >= arr[i-1][1]){
                dp[i] = dp[i-1] + arr[i][0];
            }else{
                dp[i] = arr[i][0];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] scores = new int[]{9,2,8,8,2};
        int[] ages = new int[]{4,1,3,3,5};
        System.out.println(bestTeamScore(scores,ages));

        BigDecimal bigDecimal = new BigDecimal(1.00);
        BigDecimal bigDecimal1 = bigDecimal.multiply(new BigDecimal(1000000.00));
        System.out.println(bigDecimal1);
    }

    @Test
    public void test111(){
        String a = "新增验货要款色码验证红色白色灰色蓝色紫色新增验货要款色码验证红色白色灰色蓝色紫色新增验货要款色码验证红色白色灰色蓝色紫色新增验货要款色码验证红色白色灰色蓝色紫色新增验货要款色码验证红色白色灰色蓝色紫色123";
        System.out.println(a.length());
        String b = a.substring(0,100);
        System.out.println(b);
    }

}
