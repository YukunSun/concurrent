package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

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
//        nodeDemo.right.left = new TreeNode(4);
//        nodeDemo.right.right = new TreeNode(5);
        nodeDemo.left.left = new TreeNode(4);
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
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    @Test
    public void maxDepth2Test() {
        Assert.assertEquals(maxDepth2(nodeDemo), 3);
    }

    /**
     * 迭代求解：如果能计算有多少层就可以得出结果了
     *
     * @param root
     * @return
     */
    private int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int layerNum = queue.size();
            for (int i = 0; i < layerNum; i++) {
                TreeNode node = queue.poll();//应该把某一层的弹出完以后才能增加depth
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;//一层处理完就+1
        }
        return depth;
    }
}
