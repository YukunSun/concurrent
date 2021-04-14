package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/14 21:59
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class Solution0144 {
    static TreeNode node = new TreeNode(1, null, new TreeNode(2));

    @BeforeClass
    public static void beforeClass() throws Exception {
        node.right.left = new TreeNode(3);
    }

    @Test
    public void preTest() {
        List<Integer> list = preorderTraversal(node);
        System.out.println(list);
    }

    /**
     * 前序遍历，非递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();//用于存root
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);//存结果
                stack.push(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return list;
    }

    /**
     * 递归-前中后序遍历-返回一个list
     */
    @Test
    public void preInPost2Test() {
        List<Integer> list = preInPostorderTraversal2(node);
        System.out.println(list);
    }

    private List<Integer> list = new ArrayList<>();

    /**
     * 放到list里返回
     *
     * @param root
     * @return
     */
    public List<Integer> preInPostorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return list;
    }

    void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);//左
        helper(root.right);//右
        list.add(root.val);
    }

    /**
     * 递归-前中后序遍历-直接打印
     */
    @Test
    public void preInPostTest() {
        preInPostorderTraversal(node);
    }

    /**
     * 直接打印
     *
     * @param root
     */
    public void preInPostorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        preInPostorderTraversal(root.left);//左
        preInPostorderTraversal(root.right);//右
        System.out.println(root.val);
    }
}
