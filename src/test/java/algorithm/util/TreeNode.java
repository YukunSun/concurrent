package algorithm.util;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/14 22:08
 * Blog: bengle.me
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
