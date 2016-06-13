package kpi.study.epam;

import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Predicate<Integer> low = (i)-> i > 10;
        System.out.println(low.test(5));

        TreeSet<Double> set = new TreeSet<>();
        set.add(2d);
        set.add(3d);
        set.add(5d);
        System.out.println(set.size());

    }
}
