package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/11 18:02
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class Solution0110 {
    private static TreeNode nodeDemo = new TreeNode(1, new TreeNode(2), new TreeNode(3));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(4);
        nodeDemo.right.right = new TreeNode(5);
    }

    @Test
    public void isBalancedTest() {
        System.out.println(isBalanced(nodeDemo));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return height(root) > -1;
    }

    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
