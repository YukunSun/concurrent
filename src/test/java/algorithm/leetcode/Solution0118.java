package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/4 17:47
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/pascals-triangle/
 */
public class Solution0118 {

    @Test
    public void generateTest() {
        List<List<Integer>> list = generate(30);
        System.out.println(list);
    }

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 虽然结果是正确的，但是没有避免duplicate calculations，导致比较耗时
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < numRows; j++) {
            list.add(j, helper(numRows, j + 1));
        }
        result.add(0, list);
        return generate(--numRows);
    }

    /**
     * 根据坐标用于产生单个元素，假设从1开始
     *
     * @param i
     * @param j
     * @return
     */
    private int helper(int i, int j) {
        if (j > i) {//边界检查：列必然<行
            throw new IllegalArgumentException("row num must be gt col num");
        }
        if (i == j || j == 1) {//这里比较巧妙：省略了i<=2的判断
            return 1;
        }
        return helper(i - 1, j - 1) + helper(i - 1, j);
    }

    @Test
    public void generateEachElementTest() {
        Assert.assertEquals(helper(1, 1), 1);
        Assert.assertThrows(IllegalArgumentException.class, () -> helper(1, 2));
        Assert.assertEquals(helper(2, 1), 1);
        Assert.assertEquals(helper(4, 2), 3);
    }
}
