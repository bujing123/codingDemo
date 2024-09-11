package com.jd.ljy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class QuoteTest {
    public static void main(String[] args) {
        //System.out.println(ATest.aFinal);

//        String abc="ac";
//        String c = null;
//        System.out.println(abc.contains(c));


        String dd = "1";
        double a = 3;
        double b = 7;
        double c = a/b;
        System.out.println(c);


        double ddL = dd.length();
        double dddd = 10;
        double ddd = ddL / dddd;
        System.out.println(ddd);

        String substring = String.valueOf(ddd).substring(0, 3);
        System.out.println(substring);
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(ddd);
        System.out.println(r);

    }
}


class ATest {
    public static String a = "ATest";
    public static final String aFinal = "aTestFinal";
    static{
        System.out.println("ATest类正在被初始化");
    }
}
class BTest extends ATest{
    public static String b = "BTest";
    static{
        System.out.println("BTest类正在被初始化");
    }
}
