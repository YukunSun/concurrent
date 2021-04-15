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
 * 144:https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 145:https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class Solution0144 {
    static TreeNode nodeDemo = new TreeNode(1, null, new TreeNode(2));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(3);
    }

    /**
     * 前序遍历，非递归，更容易理解，https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     */
    @Test
    public void preTest2() {
        List<Integer> list = preorderTraversal2(nodeDemo);
        System.out.println(list);
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    @Test
    public void preTest() {
        List<Integer> list = preorderTraversal(nodeDemo);
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
        List<Integer> list = preInPostorderTraversal2(nodeDemo);
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
        preInPostorderTraversal(nodeDemo);
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
