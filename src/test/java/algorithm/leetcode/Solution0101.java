package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/29 22:33
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class Solution0101 {
    static TreeNode nodeDemo = new TreeNode(1, new TreeNode(2), new TreeNode(2));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.left.right = new TreeNode(3);
        nodeDemo.right.right = new TreeNode(3);
    }

    @Test
    public void symmetricTest() {
        Assert.assertEquals(isSymmetric(nodeDemo), false);
    }

    /**
     * 递归形式
     *
     * @param root
     * @return
     */
    private boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) &&
                isSymmetricHelper(left.left, right.right) &&
                isSymmetricHelper(left.right, right.left);
    }

    @Test
    public void symmetric2Test() {
        Assert.assertEquals(isSymmetric2(nodeDemo), false);
    }

    /**
     * 使用两个栈，缺点就是必须存储null，要不然会丢掉它左或右的属性
     *
     * @param root
     * @return
     */
    private boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root.left);
        s2.push(root.right);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            TreeNode node1 = s1.pop();
            TreeNode node2 = s2.pop();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            s1.push(node1.left);
            s1.push(node1.right);
            s2.push(node2.right);
            s2.push(node2.left);
        }
        return true;
    }

    @Test
    public void symmetric3Test() {
        Assert.assertEquals(isSymmetric3(nodeDemo), false);
    }

    /**
     * BETTER：可以使用一个栈（官方用的是一个队列），而且不用存储null
     *
     * @param root
     * @return
     */
    private boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric3Helper(root, root);
    }

    private boolean isSymmetric3Helper(TreeNode u, TreeNode v) {
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(u);
        s1.push(v);
        while (!s1.isEmpty()) {
            TreeNode node1 = s1.pop();
            TreeNode node2 = s1.pop();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            s1.push(node1.left);
            s1.push(node2.right);
            s1.push(node1.right);
            s1.push(node2.left);
        }
        return true;
    }
}
