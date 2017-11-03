package review;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("k1", "v1");
            map.put("k2", "v2");
            map.put("k3", "v3");
            map.put("k4", "v4");
            map.put("k5", "v5");


            String content = load("/Users/sunyk/Desktop/test.txt");
            System.out.println("输出内容为：" + content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }


    /**
     * 存储函数
     *
     * @return
     */
    public static String store() {


        return null;
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String load(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtil.readToBuffer(sb, filePath);
        return sb.toString();
    }
}
