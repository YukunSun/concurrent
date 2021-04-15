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
 * Time: 2021/4/15 12:29
 * Blog: bengle.me
 */
public class Solution0102 {
    static TreeNode nodeDemo = new TreeNode(3, new TreeNode(9), new TreeNode(20));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(15);
        nodeDemo.right.right = new TreeNode(7);
    }

    @Test
    public void level() {
        List<List<Integer>> result = levelOrder(nodeDemo);
        System.out.println(result);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int levelNum = queue.size();//这个隐藏了一个细节：queue.size()==这一层的节点数，这样才能方便分组
            for (int i = 0; i < levelNum; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}
