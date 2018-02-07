package algorithm;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2018/1/9 下午9:32
 * Blog: coderdaily.net
 * <p>
 * 斐波那契数列
 */
public class Fibonacci {
    public int f(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 1;
        }
        return f(i - 1) + f(i - 2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int j = 1; j < 10; j++) {
            System.out.println(fibonacci.f(j));
        }
    }
}
