package Chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155,"green"),
                new Apple(120,"red"));
/*
        //方法引用
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);
        //方法引用
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);
*/
        //匿名函数
        List<Apple> greenApples1 = filterApples(inventory, (Apple a)->"green".equals(a.getColor()));
        System.out.println(greenApples1);
        //匿名函数
        List<Apple> heavyApples1 = filterApples(inventory, (Apple a)->a.getWeight()>150);
        System.out.println(heavyApples1);
	}

    /**
     * 筛选出所有的绿苹果，并返回一个列表
     * @param inventory
     * @return
     */

	public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 选出超过150克的苹果
     * @param inventory
     * @return
     */

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(apple.getWeight() > 150){
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public  static boolean isHeavyApple(Apple apple){
        return apple.getWeight() > 150;
    }

    public interface Predicate<T>{
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


	public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }
}
