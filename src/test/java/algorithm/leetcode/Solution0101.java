package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
}
