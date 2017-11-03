package 并发类容器;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/5
 */
public class ConcurrentHashMap_02 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.put("k3", "v4");
        for (Map.Entry<String, Object> en : chm.entrySet()) {
            System.out.println(en.getKey() + "----" + en.getValue());
        }
    }
}
