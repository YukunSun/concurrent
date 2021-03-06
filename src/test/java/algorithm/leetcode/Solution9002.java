package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/15 12:04
 * Blog: bengle.me
 * <p>
 * 打印二叉树的每层的最左/最右节点，即左视图/右视图
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
                TreeNode node = queue.remove(0);//会一个个的往后移动，相当于一个指针
                if (node == lastNode) {//暂存的lastNode与当前节点进行比较，因为可能无法跟后边的节点区分
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

    /**
     * 遍历同时存储当前深度与max深度，判断当前是否最深节点，若是，则加入当前节点到result队列中，先递归右子树，再递归左子树；否则，依次递归右子树和左子树
     */
    @Test
    public void rightView2() {

    }
}
