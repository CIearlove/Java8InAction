package Internet;

import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Description 理解、学习与使用 JAVA 中的 OPTIONAL
 * 从 Java 8 引入的一个很有趣的特性是 Optional  类。Optional 类主要解决的问题是臭名昭著的空指针异常（NullPointerException） —— 每个 Java 程序员都非常了解的异常。
 * @Author chenjr <chenjr@si-tech.com.cn>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/12/16
 */
public class LearningOptionalClass {

    public static void main(String[] args) {
        //whenCreateEmptyOptional_thenNull();
        //whenCreateOfEmptyOptional_thenNullPointerException();
        //whenCreateOfNullableOptional_thenOk();
        //whenCheckIfPresent_thenOk();
        //whenEmptyValue_thenReturnDefault();
        //whenValueNotNull_thenIgnoreDefault();
        //whenThrowException_thenOk();
        //whenMap_thenOk();
        whenFlatMap_thenOk();
    }
    //创建 Optional实例
    @Test(expected = NoSuchElementException.class)
    public static void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    @Test(expected = NullPointerException.class)
    public static void whenCreateOfEmptyOptional_thenNullPointerException() {
        //Optional<User> opt = Optional.of(null);
        Optional<User> opt = Optional.ofNullable(null);
    }

    //访问 Optional对象的值
    @Test
    public static void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("John", opt.get());
    }

    @Test
    public static void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        //assertTrue(opt.isPresent());
        //assertEquals(user.getEmail(), opt.get().getEmail());
        //该方法除了执行检查，还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行传入的 Lambda 表达式：
        opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));
    }

    //返回默认值
    //Optional 类提供了 API 用以返回对象值，或者在对象为空的时候返回默认值。
    //这里你可以使用的第一个方法是 orElse()，它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值：
    @Test
    public static void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getEmail(), result.getEmail());
    }

    //如果对象的初始值不是 null，那么默认值会被忽略：
    @Test
    public static void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com","1234");
        User user2 = new User("anna@gmail.com", "1234");
        //User result = Optional.ofNullable(user).orElse(user2);
        //第二个同类型的 API 是 orElseGet() —— 其行为略有不同。这个方法会在有值的时候返回值，如果没有值，它会执行作为参数传入的 Supplier(供应者) 函数式接口，并将返回其执行结果：
        User result = Optional.ofNullable(user).orElseGet( () -> user2);
        assertEquals("john@gmail.com", result.getEmail());
    }

    //返回异常
    //除了 orElse() 和 orElseGet() 方法，Optional 还定义了 orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值
    @Test(expected = IllegalArgumentException.class)
    public static void whenThrowException_thenOk() {
        User user = null;
        User result = Optional.ofNullable(user).orElseThrow( () -> new IllegalArgumentException());
    }

    //转换值
    //有很多种方法可以转换 Optional  的值。我们从 map() 和 flatMap() 方法开始。
    //map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中。--稍微有点不理解
    @Test
    public static void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        //String email = Optional.ofNullable(user).map(u -> u.getEmail()).orElse("default@gmail.com");
        String email = Optional.ofNullable(user).map(u -> u.getEmail()).get();
        assertEquals(email, user.getEmail());
    }

    //相比这下，flatMap() 也需要函数作为参数，并对值调用这个函数，然后直接返回结果。
    @Test
    public static void whenFlatMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");

        assertEquals(position, user.getPosition().get());
    }

    //过滤值
    //filter() 接受一个 Predicate 参数，返回测试结果为 true 的值。
    // 如果测试结果为 false，会返回一个空的 Optional。
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

        assertTrue(result.isPresent());
    }

    @Test
    public void whenEmptyOptional_thenGetValueFromOr() {
        User result = (User) Optional.ofNullable(null)
                .or( () -> Optional.of(new User("default","1234"))).get();

        assertEquals(result.getEmail(), "default");
    }

    //通过把实例转换为 Stream 对象，让你从广大的 Stream API 中受益
    @Test
    public void whenGetStream_thenOk() {
        User user = new User("john@gmail.com", "1234");
        List<String> emails = Optional.ofNullable(user)
                .stream()
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"))
                .map( u -> u.getEmail())
                .collect(Collectors.toList());

        assertTrue(emails.size() == 1);
        assertEquals(emails.get(0), user.getEmail());
    }

    /*
    注意：
    在使用 Optional 的时候需要考虑一些事情，以决定什么时候怎样使用它。
    重要的一点是 Optional 不是 Serializable。因此，它不应该用作类的字段。
    Optional 主要用作返回类型。
     */

}
