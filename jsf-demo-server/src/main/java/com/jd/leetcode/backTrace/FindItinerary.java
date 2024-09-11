package com.jd.leetcode.backTrace;


import java.util.*;

/**
 * 332. 重新安排行程
 * <p>
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * <p>
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * <p>
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * <p>
 * 思路：
 * 1、使用根正苗红的回溯法：
 * 终止条件：list里面存储的数据是 size+1；此时说明路径收集完了
 * 每次循环，从list里面遍历查找没有使用过，并且起飞位置是list收集的最后一个节点的；递归后，删除lsit最后一个值，used修改为false；
 * 剪枝：同一层之下发现相同的没有被使用的就直接剪枝不用看了
 * 2、使用map存储的办法：
 * 先把所有数据存储到map中；key存起始节点；value存储可以飞去的结尾机场；使用 1 0 标识是否使用过这个机票
 * 终止条件：list里面存储的数据是 size+1；此时说明路径收集完了
 * 每次循环：从map中找是否有节点符合，然后往下递归；每次递归后，删除list节点，把map中 1 0 标识改回去
 */
public class FindItinerary {
    static List<String> res;
    static List<String> list = new ArrayList<>();
    static boolean[] used;

    public static List<String> findItinerary(List<List<String>> tickets) {

        //先按照到达机场的字母排序来排序
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        used = new boolean[tickets.size()];
        list.add("JFK");
        backTrace((ArrayList<List<String>>) tickets);
        return res;
    }

    /**
     * 只需要找到第一个满足条件的就返回了，因此使用返回值
     *
     * @param tickets
     * @return
     */
    static boolean backTrace(ArrayList<List<String>> tickets) {
        if (list.size() == tickets.size() + 1) {
            res = new ArrayList<>(list);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            //存在相同机票直接，并且没有达成，直接跳过
            if (i > 0 && tickets.get(i).get(0).equals(tickets.get(i - 1).get(0))
                    && tickets.get(i).get(1).equals(tickets.get(i - 1).get(1))
                    && !used[i - 1]) {
                continue;
            }

            //没有使用过，并且值相同
            if (!used[i] && tickets.get(i).get(0).equals(list.get(list.size() - 1))) {
                list.add(tickets.get(i).get(1));
                used[i] = true;
                if (backTrace(tickets)) {
                    return true;
                }
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
        return false;
    }


    static boolean backTrace2(ArrayList<List<String>> tickets) {
        if (list.size() == tickets.size() + 1) {
            res = new ArrayList<>(list);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (i > 0 && tickets.get(i).get(0).equals(tickets.get(i - 1).get(0))
                    && tickets.get(i).get(1).equals(tickets.get(i - 1).get(1)) && !used[i - 1]) {
                continue;
            }

            if (used[i]) {
                continue;
            }
            if (list.get(list.size() - 1).equals(tickets.get(i).get(0))) {
                list.add(tickets.get(i).get(1));
                used[i] = true;
                if (backTrace2(tickets)) {
                    return true;
                }
                used[i] = false;
                list.remove(list.size() - 1);

            }
        }
        return false;
    }


    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "KUL"));
        tickets.add(Arrays.asList("JFK", "NRT"));
        tickets.add(Arrays.asList("NRT", "JFK"));
        findItinerary(tickets);
    }


    private Deque<String> res2 = new LinkedList<>();
    private Map<String, Map<String, Integer>> map;

    public List<String> findItinerary2(List<List<String>> tickets) {
        map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            Map<String, Integer> temp;
            if (map.containsKey(start)) {
                temp = map.get(start);
                temp.put(end, temp.getOrDefault(end, 0) + 1);
            } else {
                temp = new TreeMap<>();
                temp.put(end, 1);
            }
            map.put(start, temp);
        }
        res2.add("JFK");
        backTrace(tickets.size());
        return new ArrayList<>(res2);

    }

    boolean backTrace(int size) {
        if (res2.size() == size) {
            return true;
        }
        String start = res2.peekLast();
        if (map.containsKey(start)) {
            Map<String, Integer> temp = map.get(start);
            for (Map.Entry<String, Integer> entry : temp.entrySet()) {
                Integer value = entry.getValue();
                if (value > 0) {
                    res2.add(entry.getKey());
                    entry.setValue(value - 1);
                    if (backTrace(size)) {
                        return true;
                    }
                    res2.removeLast();
                    entry.setValue(value);
                }
            }
        }
        return false;
    }


}
