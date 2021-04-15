package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/15 14:58
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/
 */
public class Solution0094 {
    static TreeNode nodeDemo = new TreeNode(3, new TreeNode(9), new TreeNode(20));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.right.left = new TreeNode(15);
        nodeDemo.right.right = new TreeNode(7);
    }

    /**
     * 中序遍历-迭代的方式,https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     */
    @Test
    public void in() {
        List<Integer> result = inorderTraversal(nodeDemo);
        System.out.println(result);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                root = node.right;
            }
        }
        return list;
    }
}
