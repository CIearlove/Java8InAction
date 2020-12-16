package Chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chapter02 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155,"green"),
                new Apple(120,"red"));
        /*
        //筛选出绿苹果
        List<Apple> greenApples = filterApples(inventory, Chapter02::isGreenApple);
        System.out.println(greenApples);
        //筛选出重苹果
        List<Apple> heavyApples = filterApples(inventory, Chapter02::isHeavyApple);
        System.out.println(heavyApples);
        //筛选出绿苹果
        List<Apple> greenApples1 = filterApples(inventory, (Apple a)->"green".equals(a.getColor()));
        System.out.println(greenApples1);
        //筛选出重苹果
        List<Apple> heavyApples1 = filterApples(inventory, (Apple a)->a.getWeight()>150);
        System.out.println(heavyApples1);

        //2.1 应对不断变化的需求
        //2.1.1 初试牛刀：筛选绿苹果
        //缺陷：产生大量重复代码，打破DRY(Don't Repeat Yourself)的软件工程原则
        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

        //2.1.2 再展身手：把颜色作为参数、把重量作为参数
        //缺陷：1.这个解决方案不能很好地应对变化的需求，如果这位农民要求你对苹果的不同属性做筛选，比如：大小、形状、产地等
        //2.产生大量重复代码，打破DRY(Don't Repeat Yourself)的软件工程原则
        List<Apple> greenApples = filterColorfulApples(inventory,"green");
        System.out.println(greenApples);
        List<Apple> heavyApples = filterHeavyApplesByWeight(inventory, 80);
        System.out.println(heavyApples);

         */
        //2.2 行为参数化

	}

    /**
     * 传统的方式筛选出绿苹果
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
     * 传统的方式筛选出有颜色的苹果
     * 2.1.2 把颜色作为参数
     * @param inventory
     * @return
     */

    public static List<Apple> filterColorfulApples(List<Apple> inventory,String color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 传统的方式筛选出重苹果
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

    /**
     * 传统的方式筛选出某一重量的苹果
     * 2.1.2 把重量作为参数
     * @param inventory
     * @return
     */

    public static List<Apple> filterHeavyApplesByWeight(List<Apple> inventory,int weight){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(apple.getWeight() > weight){
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

    /**
     * @MethodName
     * @Description
     * @param null
     * @Return
     * @Throw
     * @Author chenjr <chenjr@si-tech.com.cn>
     * @Version V1.0.0
     * @Since 2020/12/14
     */
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public interface  ApplePredicate{

    }
}
