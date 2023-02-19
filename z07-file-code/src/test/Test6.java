package test;

import javax.xml.transform.Source;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test6 {
    public static void main(String[] args) {
         /*
            需求：统计一个文件夹中每种文件的个数并打印。（考虑子文件夹）
            打印格式如下：
            txt:3个
            doc:4个
            jpg:6个


        */

        File file = new File("z07-file-code\\aaaaaa");

        HashMap<String, Integer> count = getCount(file);
        System.out.println(count);

    }

    /*
     * 作用：
     *       统计一个文件夹中每种文件的个数
     * 参数：
     *       要统计的那个文件夹
     * 返回值：
     *       用来统计map集合
     *       键：后缀名 值：次数
     *
     *       a.txt
     *       a.a.txt
     *       aaa（不需要统计的）
     *
     *
     * */
    public static HashMap<String, Integer> getCount(File src) {
        //1.定义集合用来统计
        HashMap<String, Integer> hm = new HashMap<>();
        //2.进入src文件夹
        File[] files = src.listFiles();
        //3.遍历数组
        for (File file : files) {
            if (file.isFile()) {
                //如果是文件，统计
                String[] s = file.getName().split("\\.");
                if (s.length >= 2) {
                    String endName = s[s.length - 1];
                    if (hm.containsKey(endName)) {
                        //存在
                        Integer count = hm.get(endName);
                        count++;
                        hm.put(endName, count);
                    } else {
                        //不存在
                        hm.put(endName, 1);
                    }
                }
            } else {
                //5.判断，如果是文件夹，递归
                //sonMap里面是子文件中每一种文件的个数
                HashMap<String, Integer> sonMap = getCount(file);
                //遍历sonMap把里面的值累加到hm当中
                Set<Map.Entry<String, Integer>> entries = sonMap.entrySet();
                for (Map.Entry<String, Integer> entry : entries) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (!hm.containsKey(key)) {
                        //不存在
                        hm.put(key, value);
                    } else {
                        //存在
                        Integer count = hm.get(key);
                        count = count + value;
                        hm.put(key, count);
                    }
                }
            }
        }
        return hm;
    }
}
