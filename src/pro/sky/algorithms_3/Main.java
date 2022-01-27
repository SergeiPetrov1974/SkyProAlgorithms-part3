package pro.sky.algorithms_3;

import pro.sky.algorithms_3.integerlist.IntegerList;
import pro.sky.algorithms_3.integerlist.IntegerListImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(15);
        integerList.add(5);
        integerList.add(86);
        integerList.add(1);
        integerList.add(19);
        integerList.add(34);
        System.out.println(Arrays.toString(integerList.toArray()));
        integerList.remove(1);
        System.out.println(Arrays.toString(integerList.toArray()));
        integerList.clear();
        integerList.add(19);
        integerList.add(34);
        System.out.println(Arrays.toString(integerList.toArray()));
        integerList.clear();
        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.contains(190));
        System.out.println(Arrays.toString(integerList.toArray()));
        //System.out.println(integerList.getSize());
        System.out.println(integerList.contains(1));
        integerList.clear();
        integerList.add(100);
        integerList.add(0, 100);
        System.out.println(integerList.getSize());
        System.out.println(Arrays.toString(integerList.toArray()));
    }
}
