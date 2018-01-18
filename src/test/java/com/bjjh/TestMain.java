package com.bjjh;

import org.junit.Test;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TestMain {
    @Test
    public void TestMain(){
        String s1 = "(签名)(智能手机)";

        String s2 = "url7.cc/abce09Mx签名:发自我的OPPO智能手机";

        Pattern p = Pattern.compile(s1);
        Matcher m = p.matcher(s2);

        if (m.matches()){
            System.out.println(m.group(0));
        }

    }
    @Test
    public void TestMain2(){
        String sp = "(不错|很好)(.*)(但是|就是|但|只是)";

        // QRY#姓名#身份证号#亲友号码#身份证住址
        String s1 = "课程很不错，礼仪是我们大家日常生活、工作不可或缺的事情。但是感觉学生上课不积极";
        Pattern pa = Pattern.compile(sp);
        Matcher ma = pa.matcher(s1);
        if (ma.matches()) {
            System.out.println(ma.group(1));
            System.out.println(ma.group(2));
            System.out.println(ma.group(3));
            System.out.println(ma.group(4));
        }

        System.out.println();
    }
}
