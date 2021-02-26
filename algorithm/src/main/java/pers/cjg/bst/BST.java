package pers.cjg.bst;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: chenjingang@guazi.com  2020-10-22 15:19
 */
public class BST {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class Node {
        private Node left;
        private Node right;
        private Integer value;
        Boolean isFirst = true;
    }

    public static int target = 3;

    public static void fun(Node node, int result) {
//        Stack<Node> nodes = new Stack<>();
//        Node root = new Node();
        if (node == null) {
            return;
        }
        if (result + node.value == target) {
            System.out.println("符合情况");
        }
        fun(node.left, result + node.value);
        fun(node.right, result + node.value);
    }

    public static void fun1(Node node, int result) {
        Stack<Node> nodes = new Stack<>();
//        Node root = new Node();
        if (node == null) {
            return;
        }
        if (result + node.value == target) {
            System.out.println("符合情况");
        }
        fun1(node.left, result + node.value);
        fun1(node.right, result + node.value);
    }

    public static void main(String[] args) {
        Integer[] pre = new Integer[]{1, 2, 4, 5, 3, 6, 7};
        Integer[] mid = new Integer[]{4, 2, 5, 1, 6, 3, 7};
        Integer[] back = new Integer[]{4, 5, 2, 6, 7, 3, 1};

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mid.length; i++) {
            map.put(mid[i], i);
        }

        Node tree = buildTree(
                pre, 0, 6,
                mid, 0, 6,
                map
        );
        System.out.println("后序遍历");
        traverse(tree);
        System.out.println();
//        Integer integer = oneSideMax(tree);
//        System.out.println(integer);
        System.out.println("先序遍历");
        preTraverse(tree);
        System.out.println();
        System.out.println("中序遍历");
        midTraverse(tree);
        System.out.println();
        System.out.println("后序遍历1");
        backTraverse(tree);
        System.out.println();
        System.out.println("后序遍历2");
        backTraverse1(tree);

//        fun(tree, 0);
        Node node1 = new Node();
        node1.value = 3;

        Node node = new Node();
        node.value = 1;
        node.right = node1;

        Node node4 = new Node();
        node4.value = 4;

        Node node3 = new Node();
        node3.value = 2;
        node3.right = node4;

        System.out.println();
        Node merge = merge(node, node3);

        System.out.println(merge);

    }

    /**
     * 根据前序、中序重建二叉树
     *
     * @param pre
     * @param preStart
     * @param preEnd
     * @param mid
     * @param midStart
     * @param midEnd
     * @param map
     * @return
     */
    public static Node buildTree(
            Integer[] pre, Integer preStart, Integer preEnd,
            Integer[] mid, Integer midStart, Integer midEnd,
            Map<Integer, Integer> map
    ) {
        if (preStart > preEnd || midStart > midEnd) {
            return null;
        }
        System.out.println(preStart + "\t" + preEnd + "\t" + midStart + "\t" + midEnd);
        Node root = Node.builder()
                .value(pre[preStart])
                .isFirst(true)
                .build();
        Integer midIndex = map.get(pre[preStart]) - midStart;

        root.left = buildTree(
                pre, preStart + 1, preStart + midIndex,
                mid, midStart, midStart + midIndex - 1,
                map
        );

        root.right = buildTree(
                pre, preStart + midIndex + 1, preEnd,
                mid, midStart + midIndex + 1, midEnd,
                map
        );
        return root;
    }


    /**
     * 递归遍历
     *
     * @param node
     */
    static void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.left);
        traverse(node.right);
        System.out.print(node.value + " ");
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    static void backTraverse(Node node) {
        Stack<Node> stack = new Stack<>();
        if (node == null) {
            return;
        }
//        traverse1(node.left);

        Node currentNode = node;
        Node last = node;
        while (currentNode != null || !stack.isEmpty()) {

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty()) {
                currentNode = stack.peek();
                /**
                 * 没有右子树或者已经被访问，则进行出栈操作
                 * 记录最后访问的节点，防止右子树重复访问
                 */
                if (currentNode.right == null || currentNode.right == last) {
                    System.out.print(currentNode.value + " ");
                    stack.pop();
                    last = currentNode;
                    currentNode = null;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
    }

    static void backTraverse1(Node node) {
        Stack<Node> stack = new Stack<>();
        if (node == null) {
            return;
        }
//        traverse1(node.left);

        Node currentNode = node;
        while (currentNode != null || !stack.isEmpty()) {

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty()) {
                currentNode = stack.peek();
                if (currentNode.isFirst) {
                    currentNode.isFirst = false;
                    currentNode = currentNode.right;
                } else {
                    System.out.print(currentNode.value + " ");
                    stack.pop();
                    currentNode = null;
                }
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */

    static void midTraverse(Node node) {
        Stack<Node> stack = new Stack<>();
        if (node == null) {
            return;
        }
//        traverse1(node.left);

        Node currentNode = node;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (!stack.isEmpty()) {
                currentNode = stack.pop();
                System.out.print(currentNode.value + " ");
                currentNode = currentNode.right;
            }
        }
    }

    /**
     * 先序遍历
     *
     * @param node
     */
    static void preTraverse(Node node) {
        Stack<Node> stack = new Stack<>();
        if (node == null) {
            return;
        }
//        traverse1(node.left);

        Node currentNode = node;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                System.out.print(currentNode.value + " ");
                stack.push(currentNode);
                currentNode = currentNode.left;
            }


            if (!stack.isEmpty()) {
                currentNode = stack.pop();
                currentNode = currentNode.right;
            }

        }
    }

    /**
     * 最大路径和
     *
     * @param node
     * @return
     */
    static Integer oneSideMax(Node node) {
        if (node == null) {
            return 0;
        }
        Integer left = oneSideMax(node.left);
        Integer right = oneSideMax(node.right);
        return Math.max(left, right) + node.value;
    }


    static Node merge(Node list1, Node list2) {
        Node curr = new Node();
        Node head = curr;


        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                curr.right = list1;
                list1 = list1.right;
            } else {
                curr.right = list2;
                list2 = list2.right;
            }
            curr = curr.right;
        }
        if (list1 != null)
            curr.right = list1;
        else
            curr.right = list2;

        return head.right;
    }
}
