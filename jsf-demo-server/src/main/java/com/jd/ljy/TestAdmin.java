package com.jd.ljy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestAdmin {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int height1 = sc.nextInt();
//        int height2 = sc.nextInt();
//        int height3 = sc.nextInt();
//        int tempHeight = height1 > height2 ? height1 : height2;
//        int max = tempHeight > height3 ? tempHeight : height3;
//        System.out.println(max);
//
//
//        long id = 1;

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(JSON.toJSONString(set));
        set.remove(1);
        System.out.println(JSON.toJSONString(set));


    }

    @Test
    public void testLambda1() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(4, 2));//结果：1
    }

    String toLowerString(MyFuncInterf<String> mf, String origin) {
        return mf.getValue(origin);
    }

    @Test
    public void testLambda2() {
        String value = toLowerString((str) -> {
            return str.toLowerCase();
        }, "ABC");
        System.out.println(value);
    }

    @Test
    public void testQuote() {
        int[] arr = {1, 2, 3};
        change(arr);
        System.out.println(arr[2]);
    }

    public void change(int[] arr) {
        arr[2] = 100;
    }

    @Test
    public void FatherSon() {
        Son son = new Son();
        son.setAge(1);
        son.printSchool(); //打印父类的学校
        son.updateSchool("bupt2021");
        System.out.println(son.getSchool()); //打印子类学校
        son.printSchool();  //打印父类学校


        Map<String, Object> feeObject = new HashMap<>();
        feeObject.put("msg", son);
        System.out.println(feeObject);
        son.setAge(2);
        System.out.println(feeObject);
    }

    @Test
    public void TestString() {
        Date date2 = new Date();
        Date date1 = getYesterday();

        System.out.println(date2.after(date1));

        System.out.println((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600)));
        System.out.println(date1);
        System.out.println(date2);
    }

    public static Date getYesterday() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.set(yesterday.get(Calendar.YEAR), yesterday.get(Calendar.MONTH), yesterday.get(Calendar.DAY_OF_MONTH) - 1);
        return yesterday.getTime();
    }

    @Test
    public void TestString11(){
        String aa = "123!123!123!!!!!";
        String[] split = aa.split("!");
        List<String> list = Arrays.asList(split);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void Test11() {
        String s = "pickupVerificationCode:098722;bbbbb:098722;aaaa:098722";
        HashMap<String, String> map = new HashMap<>();
        String[] split = s.split(";");
        for (String s1 : split) {
            String[] split1 = s1.split(":");
            map.put(split1[0], split1[1]);
        }
        System.out.println(map);

        //删除 addService 里面的pickupVerificationCode
        StringBuffer addServiceBuffer = new StringBuffer();
        String[] splitParams = s.split(";");
        for (int i = 0; i < splitParams.length; i++) {
            if (!splitParams[i].contains("pickupVerificationCode")) {
                addServiceBuffer.append(splitParams[i]).append(";");
            }
        }
        System.out.println(addServiceBuffer.toString());
    }

    @Test
    public void Test12() {
        JSONObject jsonObject = new JSONObject();
        String s = "[{\"city\":\"合肥市\",\"clientId\":0,\"collectionValue\":0,\"county\":\"肥西县\",\"customerCode\":\"021K242192\",\"goods\":\"美妆个护\",\"guaranteeValue\":0,\"josPin\":\"bingcheng226\",\"orderId\":\"JD-0aec3197-1860b1608f3-1\",\"orderSubmitTime\":1675223239015,\"packageCount\":1,\"promiseTimeType\":1,\"province\":\"安徽省\",\"receiveAddress\":\"安徽省 合肥市 肥西县 桃花镇 禹州华侨城二期柳园16栋401\",\"receiveMobile\":\"18119651130\",\"receiveName\":\"小小玲\",\"salePlat\":\"0010019\",\"senderAddress\":\"上海 上海市 嘉定区 马陆镇龙盘路888号\",\"senderCity\":\"上海市\",\"senderCounty\":\"嘉定区\",\"senderMobile\":\"021-80392283\",\"senderName\":\"得物App白冰冰\",\"senderProvince\":\"上海\",\"senderTel\":\"021-80392283\",\"source\":\"DEWU\",\"town\":\"桃花镇\",\"vloumn\":1000.0,\"weight\":1.0}]\n";
        String requestParam = JSONObject.parseArray(s).get(0).toString();
        Object orderId = JSONObject.parseObject(requestParam).get("orderId");
        if (jsonObject.get("order_id") == null && orderId != null) {
            jsonObject.put("order_id", orderId.toString());
            System.out.println(orderId.toString());
            System.out.println(jsonObject.toString());
        }

    }

    @Test
    public void Test13() {
        String a = "12345678910";
        String b = "12345";
        String c = "321";
        System.out.println(a.indexOf(b));
        System.out.println(a.indexOf(c));

    }

    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int a = 0;
        int b = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            a += entry.getValue() / 2;
            b += entry.getValue() % 2;
        }
        return new int[]{a, b};

    }

    @Test
    public void TestTime() {
//        Date d = new Date("1624889165000");
//
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String format = sf.format(d);
//        System.out.println(format);
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        System.out.println(timeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
        System.out.println("格式化结果：" + sd);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");
        String sd2 = sdf2.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        System.out.println("格式化结果：" + sd2);

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        String sd3 = sdf3.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        System.out.println("格式化结果：" + sd3);
    }

    @Test
    public void TestGetList() {
        ArrayList<Father> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Father father = new Father();
            father.setName("dasdad");
            father.setAge(123);
            arrayList.add(father);
        }
        System.out.println(JSON.toJSONString(arrayList));
    }

    @Test
    public void Test111(){
        String a = "1111";
        switch (a){
            case "1111":
                System.out.println(a);
                if(a.equals("1111")){
                    break;
                }
                System.out.println("1312312");
            case "bbbb":
                System.out.println("bbbb");
        }
        System.out.println("ccc");


        String aaaa = "";
        if(aaaa.equals("")){
            System.out.println(aaaa);
            System.out.println("111111");
        }

        List<String> arrayList = new ArrayList<>();
        arrayList.add("111");
        arrayList.add(a);
        arrayList.add(aaaa);
        arrayList.add("1131231");
        System.out.println(JSON.toJSONString(arrayList));

    }

    @Test
    public void test111(){
        String receiverAddress = "上海市上海市嘉定区马陆镇龙盘路999号";
        List<String> dewuDeliveryTypeAddressList = Arrays.asList("上海,嘉定区,马陆龙盘路999号,退仓|上海,嘉定区,马陆龙盘路999号,退仓|上海,嘉定区,马陆镇沈石路1000号,退仓|上海市,嘉定区,马陆镇龙盘路999号,退仓|上海,嘉定区,马陆镇龙盘路999号,退仓|上海,嘉定区,银丽路900号,退仓|上海,嘉定区,马陆镇龙盘路999号,退仓|上海,嘉定区,马陆龙盘路999号,退仓|上海,嘉定区,马陆镇沈石路1000号,退仓|上海,嘉定区,马陆镇龙盘路999号,退仓|上海,嘉定区,银丽路900号,退仓|上海,杨浦区,茭白园路200号,退仓|上海,嘉定区,马陆镇龙盘路999号,退仓|上海,嘉定区,马陆镇昌盛路111号,退仓|上海,嘉定区,马陆镇昌盛路111号,退仓|武汉市,黄陂区,横店街道临空西路110号,退仓|广州市,番禺区,化龙镇宾月大街1999号,退仓|成都市,龙泉驿区,柏合镇南六路999号,退仓|河北,廊坊,安次区北史家务乡富文道999号,退仓".split("\\|"));
        System.out.println(JSON.toJSONString(dewuDeliveryTypeAddressList));
        for (String dewuDeliveryTypeAddress : dewuDeliveryTypeAddressList) {
            System.out.println(dewuDeliveryTypeAddress);
            List<String> dewuDeliveryTypeAddressSplitList = Arrays.asList(dewuDeliveryTypeAddress.split(","));
            System.out.println(dewuDeliveryTypeAddressSplitList);
            if (receiverAddress.contains(dewuDeliveryTypeAddressSplitList.get(0))
                    && receiverAddress.contains(dewuDeliveryTypeAddressSplitList.get(1))
                    && receiverAddress.contains(dewuDeliveryTypeAddressSplitList.get(2))) {
                System.out.println((dewuDeliveryTypeAddressSplitList.get(3).equals("退仓") ? 2 : 1));
                break;
            }
        }
    }

    @Test
    public void MinimumRecolors() {
        String blocks = "WBBWWBBWBW";
        int k = 7;
        int n = blocks.length();
        int min = k;
        for(int i=0; i<=n-k; i++){
            int numW = 0;
            for(int j=i; j<i+7; j++){
                if(blocks.charAt(j) == 'W'){
                    numW++;
                }
            }
            min = Math.min(min,numW);
        }
    }

    @Test
    public void TestNull() {
        List<String> list = Arrays.asList("010K1714034:京东山西库+18625565101+上海市+上海市+嘉定区+叶城路1118号".split("\\|"));
        System.out.println(JSON.toJSONString(list));
        for (String s : list) {
            String s1 = Arrays.asList(s.split(":")).get(1);
            System.out.println(s1);
            List<String> s2 = Arrays.asList(s1.split("\\+"));
            System.out.println(s2);
        }
    }

    @Test
    public void TestBigDecimal() {
        BigDecimal QLHCFAmount = new BigDecimal(0);
        for (int i = 0; i < 10; i++) {
            addQLHCFAmount(QLHCFAmount);
        }
        System.out.println(QLHCFAmount);
    }

    @Test
    public void TestArrayList() {
        List<String> list = Arrays.asList("".split(","));
        List<String> list2 = new ArrayList<>(Arrays.asList("".split(",")));
        System.out.println(CollectionUtils.isNotEmpty(list));
        System.out.println(list);
        System.out.println(list.toArray().length);
        System.out.println(list.size());


        System.out.println(CollectionUtils.isNotEmpty(list2));
        System.out.println(list2);
        System.out.println(list2.get(0));
        System.out.println(list2.size());
    }

    @Test
    public void Test1321() {
        String jsonString = "{\n" +
                "        \"soap:Body\": {\n" +
                "            \"ns2:SaveSku\": {\n" +
                "                \"SaveSkuInfo\": {\n" +
                "                    \"SkuColor\": \"白\",\n" +
                "                    \"SkuGroup\": \"手机\",\n" +
                "                    \"SerialLength\": \"0\",\n" +
                "                    \"IsActive\": \"Y\",\n" +
                "                    \"Udf7\": \"小辣椒1624979722A\",\n" +
                "                    \"Descr\": \"手机.小辣椒.测试1624979722.华为配件ND.白\",\n" +
                "                    \"Unit\": \"台\",\n" +
                "                    \"Udf6\": \"小辣椒1624979722\",\n" +
                "                    \"IsSerial\": \"N\",\n" +
                "                    \"SkuGroup2\": \"小辣椒\",\n" +
                "                    \"SkuClassID\": \"华为配件ND\",\n" +
                "                    \"SkuID\": \"10004659600750000\",\n" +
                "                    \"SkuStyle\": \"测试1624979722\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONObject saveSkuInfo = jsonObject.getJSONObject("soap:Body").getJSONObject("ns2:SaveSku").getJSONObject("SaveSkuInfo");
        System.out.println(saveSkuInfo.toJSONString());


    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        //先将对于起点来说能够联通的点拎出来放在一个分组
        List<Integer>[] result = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            result[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            //有边的点加入后，相当于加入所有能够联通的点
            result[x].add(y);
            result[y].add(x);
        }

        //result已经是包含每个节点直接连着哪些节点了

        boolean[] ifSee = new boolean[n];
        //从起点开始搜索，如果搜到了destination直接返回true
        return dfs(source, result, destination, ifSee);

    }

    private Boolean dfs(int source, List<Integer>[] result, int destination, boolean[] ifSee) {
        //只要碰到destination,就返回true
        if (source == destination) {
            return true;
        }
        //定义这个节点已经被观察过了
        ifSee[source] = true;
        for (Integer integer : result[source]) {
            if (!ifSee[integer] && dfs(integer, result, destination, ifSee)) {
                return true;
            }
        }
        return false;

    }


    @Test
    public void Test100() {
        int num = 0;
        boolean ifdada = false;
        while (!ifdada) {
            System.out.println(ifdada);
            ifdada = true;
            num++;
        }


    }

    private Map<Integer, Integer> map = new HashMap<>();

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        for (int[] item : items1) {
            if (map.containsKey(item[0])) {
                map.put(item[0], map.get(item[0]) + item[1]);
            } else {
                map.put(item[0], item[1]);
            }
        }
        for (int[] item : items2) {
            if (map.containsKey(item[0])) {
                map.put(item[0], map.get(item[0]) + item[1]);
            } else {
                map.put(item[0], item[1]);
            }
        }
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(Arrays.asList(entry.getKey(), entry.getValue()));
        }
        list.sort((a, b) -> a.get(0) - b.get(0));
        return list;
    }

    public int[][] largestLocal(int[][] grid) {
        int[][] maxLocal = new int[grid.length - 2][grid.length - 2];
        int length = grid.length;
        for(int i=0; i<length+2; i++){
            for(int j=0; j<length+2; j++){
                for (int m = i; m < i + 3; m++) {
                    for (int n = j; n < j + 3; n++) {
                        maxLocal[i][j] = Math.max(maxLocal[i][j], grid[m][n]);
                    }
                }
            }
        }



        return maxLocal;
    }

    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        while (sb.length() <= 32 && num != 0) {
            num *= 2;
            int integer = (int)num;
            sb.append(integer);
            num -= integer;
        }

        return num == 0 ? sb.toString() : "ERROR";
    }


    public void addQLHCFAmount(BigDecimal QLHCFAmount) {
        QLHCFAmount = QLHCFAmount.add(new BigDecimal(1));
    }
}

@FunctionalInterface
interface MyFuncInterf<T> {
    public T getValue(String origin);
}


