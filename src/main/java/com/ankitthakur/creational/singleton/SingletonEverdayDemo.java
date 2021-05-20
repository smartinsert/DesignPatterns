package com.ankitthakur.creational.singleton;

public class SingletonEverdayDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        System.out.println(runtime);

        Runtime anotherInstance = Runtime.getRuntime();
        System.out.println(anotherInstance);

        if (runtime == anotherInstance) {
            System.out.println("They are the same !!");
        }
    }
}
