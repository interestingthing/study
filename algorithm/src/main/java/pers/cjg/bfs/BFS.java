package pers.cjg.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: chenjingang@guazi.com  2020-10-23 17:30
 */
public class BFS {


    public static void main1(String[] args) {

        //6
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        List<String> deadendList = Arrays.asList(deadends);
        String target = "0202";

        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        String e = "0000";
        q.offer(e);

        HashSet<String> set = new HashSet<>();
        set.add(e);

        Integer count1 = 0;
        Integer count2 = 0;
        Integer depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String poll = q.poll();
                if (target.equals(poll)) {
                    System.out.println(depth);
                    System.out.println(count1);
                    System.out.println(count2);
                    return;
                }
                for (int j = 0; j < 4; j++) {
                    assert poll != null;
                    String plus = plus(poll, j);
                    if (!e.equals(plus) && !deadendList.contains(plus)) {
                        if (set.add(plus)) {
                            q.offer(plus);
                            count1++;
                        }
                    }
                    String minus = minus(poll, j);
                    if (!e.equals(minus) && !deadendList.contains(minus)) {
                        if (set.add(minus)) {
                            q.offer(minus);
                            count2++;
                        }
                    }

                }
            }

            depth++;
        }
        System.out.println(-1);
    }

    // 124 3
    /**
     * 双向bfs利用两个集合会有交集来判断结束，注意两个集合添加元素的条件（一定要可以产生交集）
     * @param args
     */
    public static void main(String[] args) {

        //6
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        List<String> deadendList = Arrays.asList(deadends);
        String target = "0202";

        HashSet<String> q1 = new HashSet<>();
        String e = "0000";
        q1.add(e);
        HashSet<String> q2 = new HashSet<>();
        q2.add(target);

        HashSet<String> visited = new HashSet<>();
//        visited.add(e);

        Integer count1 = 0;
        Integer count2 = 0;
        Integer depth = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {

            HashSet<String> temp = new HashSet<>();

            for (String poll : q1) {
                if (deadendList.contains(poll)) {
                    continue;
                }
                if (q2.contains(poll)) {
                    System.out.println(depth);
                    System.out.println(count1);
                    System.out.println(count2);
                    return;
                }
                visited.add(poll);
                for (int j = 0; j < 4; j++) {
                    assert poll != null;
                    String plus = plus(poll, j);
                    if (!e.equals(plus) && !deadendList.contains(plus)) {
                        if (!visited.contains(plus)) {
                            temp.add(plus);
                            count1++;

                        }
                    }
                    String minus = minus(poll, j);
                    if (!e.equals(minus) && !deadendList.contains(minus)) {
                        if (!visited.contains(minus)) {
                            temp.add(minus);
                            count2++;
                        }
                    }

                }
            }

            if (temp.size() > q2.size()) {
                q1 = q2;
                q2 = temp;
            } else {
                q1 = temp;
            }
            depth++;
        }
        System.out.println(-1);
    }

    public static String plus(String poll, int j) {
        char[] c = poll.toCharArray();
        if (c[j] == '9') {
            c[j] = '0';
        } else {
            c[j] += 1;
        }
        return new String(c);
    }


    public static String minus(String poll, int j) {
        char[] c = poll.toCharArray();
        if (c[j] == '0') {
            c[j] = '9';
        } else {
            c[j] -= 1;
        }
        return new String(c);
    }
}
