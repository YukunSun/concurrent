package review;

import java.util.HashMap;
import java.util.Map;

public class Main_02 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("keyA", "valueA");

        Map[][] a = new HashMap[2][3];

        String text = store(a); //把数组保存到一个文本字符串中
        a = load(text); //把文本字符串中的内容读取为数组必须严格按照上述的“每行一个、key=value”的格式保存。

    }

    private static Map[][] load(String text) {



        return new Map[0][];
    }


    private static String store(Map[][] a) {
        return null;
    }
}
