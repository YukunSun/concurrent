package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/15 12:04
 * Blog: bengle.me
 * <p>
 * 打印二叉树的每层的最左/最右节点，即左视图/右视图
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/
 */
public class Solution0199 {
    static TreeNode nodeDemo = new TreeNode(3, new TreeNode(9), new TreeNode(20));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(15);
        nodeDemo.right.right = new TreeNode(7);
//        nodeDemo.left.left = new TreeNode(11);
    }

    /**
     * 一棵二叉树的左视图/右视图，垃圾解法，太啰嗦
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
    public void rightView2Test() {
        List<Integer> result = rightSideView(nodeDemo);
        System.out.println(result);
    }

    private List<Integer> result = new ArrayList<>();

    /**
     * 深度优先遍历：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/jian-dan-bfsdfs-bi-xu-miao-dong-by-sweetiee/
     *
     * @param root
     */
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {//这里太巧妙了，如果不相等意味着不是该层第一个访问的元素
            result.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }


    @Test
    public void printRight2Test() {
        printRight2(nodeDemo);
    }

    /**
     * bfs，推荐写法
     *
     * @param root
     */
    public void printRight2(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {//每一层一层的去poll
                TreeNode node = queue.poll();//可能是受三序遍历的影响，老是写到for外面，其实应该写到里面
                if (i == (levelNum - 1)) {//这个非常巧妙
                    System.out.println(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }
}
