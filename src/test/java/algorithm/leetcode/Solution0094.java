package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/15 14:58
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/
 */
public class Solution0094 {
    static TreeNode nodeDemo = new TreeNode(1, new TreeNode(2), new TreeNode(3));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.left.left = new TreeNode(4);
        nodeDemo.left.right = new TreeNode(5);
        nodeDemo.left.right.left = new TreeNode(6);
        nodeDemo.left.right.right = new TreeNode(7);
    }

    /**
     * 中序遍历-迭代的方式,https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     */
    @Test
    public void in() {
        List<Integer> result = inorderTraversal(nodeDemo);
        System.out.println(result);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                cur = node.right;
            }
        }
        return list;
    }

    @Test
    public void in2() {
        List<Integer> result = inorderTraversal2(nodeDemo);
        System.out.println(result);//expect:[4, 2, 6, 5, 7, 1, 3]
    }

    private List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;//这个cur用于指向方向
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {//左孩子尽可能入栈
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                cur = node.right;
            }
        }
        return list;
    }
}
