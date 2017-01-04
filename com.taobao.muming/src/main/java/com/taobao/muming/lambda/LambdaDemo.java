package com.taobao.muming.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description: too much code, for too little to do
 * Lambda expression rocks !!
 * <p>
 * predicate:
 * (params) -> expression
 * (params) -> statement
 * (params) -> { statements }
 * </p>
 * @author: gubing.gb
 * @date: 2017/1/4.
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //demo1();
        //demo2();
        //demo3();
        //demo4();
        //demo5();
        //demo6();
        //demo7();
        //demo8();
        //demo9();
        //demo10();
        demo11();
    }

    private static void demo11() {
    }

    private static void demo10() {
        //Get count, min, max, sum, and average for numbers
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    private static void demo9() {
        // Create List of square of all distinct numbers
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct()
                .collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    private static void demo8() {
        // Convert String to Uppercase and join them using coma
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany",
                "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    private static void demo7() {
        List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // Create a List with String more than 2 characters
        List<String> filtered = strList.stream().filter(x -> x.length() > 3)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n",
                strList, filtered);
    }

    private static void demo6() {
        // Applying 12% VAT on each purchase
        // Old way:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // New way:
        //List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost)
                .reduce((sum, cost) -> sum + cost)
                .get();
        System.out.println("Total : " + bill);
    }

    private static void demo5() {
        // applying 12% VAT on each purchase
        // Without lambda expressions:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        // With Lambda expression:
        //List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12 * cost)
                .forEach(System.out::println);
    }

    private static void demo4() {
        // We can even combine Predicate using and(), or() And xor() logical functions
        // for example to find names, which starts with J and four letters long, you
        // can pass combination of two Predicate
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("\nName, which starts with 'J' and four letter long is : " + n));
    }

    private static void demo3() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//        String str1 = "";
//        str1.startsWith("J");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }

    //必须指明类型。不指名类型，lambda会报错
    //public static void filter(List names, Predicate condition) {
    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    private static void demo2() {
        //Prior Java 8 :
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (Object feature : features) {
            System.out.println(feature);
        }
        //Consumer action;
        //features.forEach(action);
        //In Java 8:
        //List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // Even better use Method reference feature of Java 8
        // method reference is denoted by :: (double colon) operator
        // looks similar to score resolution operator of C++
        // 方法引用是使用两个冒号::这个操作符号。
        features.forEach(System.out::println);
    }

    private static void demo1() {
        //before JAVA8
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Before JAVA8");
            }
        }).start();

        //Java8
        new Thread(() -> System.out.println("In Java8")).start();
    }
}
