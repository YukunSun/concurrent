package algorithm.leetcode;

import algorithm.util.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/15 15:31
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/
 */
public class Solution0145 {
    static TreeNode nodeDemo = new TreeNode(1, new TreeNode(2), new TreeNode(3));

    @BeforeClass
    public static void beforeClass() throws Exception {
        nodeDemo.left.left = new TreeNode(4);
        nodeDemo.right.left = new TreeNode(5);
        nodeDemo.right.right = new TreeNode(6);
        nodeDemo.right.right.left = new TreeNode(7);
    }

    /**
     * 后序遍历-迭代方式-https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     */
    @Test
    public void post() {
        List<Integer> list = postorderTraversal(nodeDemo);
        System.out.println(list);
    }

    /**
     * 既然是后序遍历，那么输出的结果就是L-R-N，那么就可以：N入栈，R,L依次入栈就可以了
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(list);//还是要先写出来结果，然后先射箭，后画靶，list存储的正好是倒序的结果
        return list;
    }
}
