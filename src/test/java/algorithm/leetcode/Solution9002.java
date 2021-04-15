package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/15 12:04
 * Blog: bengle.me
 * <p>
 * 打印二叉树的每层的最左/最右节点
 */
public class Solution9002 {
    static TreeNode nodeDemo = new TreeNode(3, new TreeNode(9), new TreeNode(20));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(15);
        nodeDemo.right.right = new TreeNode(7);
//        nodeDemo.left.left = new TreeNode(11);
    }

    /**
     * 一棵二叉树的左视图/右视图
     */
    @Test
    public void rightView() {
        printRight(nodeDemo);
    }

    public List<Integer> left(TreeNode root) {


        return null;
    }


    public void printRight(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            TreeNode lastNode = queue.get(levelNum - 1);//每层的最后一个节点，即右视图
//            TreeNode lastNode = queue.get(0);//每层的第一个节点，即左视图
            for (int i = 0; i < levelNum; i++) {
                TreeNode node = queue.remove(0);
                if (node == lastNode) {
                    System.out.println(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
}
