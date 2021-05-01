package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/1 22:11
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class Solution0104 {
    static TreeNode nodeDemo = new TreeNode(1, new TreeNode(2), new TreeNode(3));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(4);
        nodeDemo.right.right = new TreeNode(5);
    }

    @Test
    public void maxDepthTest() {
        Assert.assertEquals(maxDepth(nodeDemo), 3);
    }

    /**
     * 递归的思想：先确定基准条件，再不断缩小规模
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
