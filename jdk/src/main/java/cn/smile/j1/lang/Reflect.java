package cn.smile.j1.lang;

/**
 * Created by Smile on 2018/7/25.
 */
public class Reflect {

    interface Vehicle {
        int drive();
        int stop();
    }

    private static class Car implements Vehicle {
        @Override
        public int drive() {
            return 0;
        }
        @Override
        public int stop() {
            return 0;
        }
    }

    public static void main(String[] args) {

        Vehicle car = new Car();
        Class<?>[] interfaces = car.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            System.out.println(clazz);
        }
    }
}
