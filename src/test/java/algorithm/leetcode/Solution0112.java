package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/30 23:05
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/path-sum/
 */
public class Solution0112 {
    static TreeNode nodeDemo = new TreeNode(1, new TreeNode(2), new TreeNode(3));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(4);
        nodeDemo.right.right = new TreeNode(5);
    }

    @Test
    public void hashPathSumTest() {
//        Assert.assertEquals(hasPathSum(nodeDemo, 8), true);
//        Assert.assertEquals(hasPathSum(nodeDemo, 3), true);
        Assert.assertEquals(hasPathSum(nodeDemo, 9), true);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right == null && node.left == null && targetSum == node.val) {
                return true;
            }
            if (node.left != null) {
                node.left.val = node.val + node.left.val;//直接把值存到下一个节点...妙啊
                stack.push(node.left);
            }
            if (node.right != null) {
                node.right.val = node.val + node.right.val;
                stack.push(node.right);
            }
        }
        return false;
    }
}
