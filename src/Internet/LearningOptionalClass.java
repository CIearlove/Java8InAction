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
 * @Description ��⡢ѧϰ��ʹ�� JAVA �е� OPTIONAL
 * �� Java 8 �����һ������Ȥ�������� Optional  �ࡣOptional ����Ҫ����������ǳ��������Ŀ�ָ���쳣��NullPointerException�� ���� ÿ�� Java ����Ա���ǳ��˽���쳣��
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
    //���� Optionalʵ��
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

    //���� Optional�����ֵ
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
        //�÷�������ִ�м�飬������һ��Consumer(������) ��������������ǿյģ��Ͷ�ִ�д���� Lambda ���ʽ��
        opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));
    }

    //����Ĭ��ֵ
    //Optional ���ṩ�� API ���Է��ض���ֵ�������ڶ���Ϊ�յ�ʱ�򷵻�Ĭ��ֵ��
    //���������ʹ�õĵ�һ�������� orElse()�����Ĺ�����ʽ�ǳ�ֱ�ӣ������ֵ�򷵻ظ�ֵ�����򷵻ش��ݸ����Ĳ���ֵ��
    @Test
    public static void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getEmail(), result.getEmail());
    }

    //�������ĳ�ʼֵ���� null����ôĬ��ֵ�ᱻ���ԣ�
    @Test
    public static void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com","1234");
        User user2 = new User("anna@gmail.com", "1234");
        //User result = Optional.ofNullable(user).orElse(user2);
        //�ڶ���ͬ���͵� API �� orElseGet() ���� ����Ϊ���в�ͬ���������������ֵ��ʱ�򷵻�ֵ�����û��ֵ������ִ����Ϊ��������� Supplier(��Ӧ��) ����ʽ�ӿڣ�����������ִ�н����
        User result = Optional.ofNullable(user).orElseGet( () -> user2);
        assertEquals("john@gmail.com", result.getEmail());
    }

    //�����쳣
    //���� orElse() �� orElseGet() ������Optional �������� orElseThrow() API ���� �����ڶ���Ϊ�յ�ʱ���׳��쳣�������Ƿ��ر�ѡ��ֵ
    @Test(expected = IllegalArgumentException.class)
    public static void whenThrowException_thenOk() {
        User user = null;
        User result = Optional.ofNullable(user).orElseThrow( () -> new IllegalArgumentException());
    }

    //ת��ֵ
    //�кܶ��ַ�������ת�� Optional  ��ֵ�����Ǵ� map() �� flatMap() ������ʼ��
    //map() ��ֵӦ��(����)��Ϊ�����ĺ�����Ȼ�󽫷��ص�ֵ��װ�� Optional �С�--��΢�е㲻���
    @Test
    public static void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        //String email = Optional.ofNullable(user).map(u -> u.getEmail()).orElse("default@gmail.com");
        String email = Optional.ofNullable(user).map(u -> u.getEmail()).get();
        assertEquals(email, user.getEmail());
    }

    //������£�flatMap() Ҳ��Ҫ������Ϊ����������ֵ�������������Ȼ��ֱ�ӷ��ؽ����
    @Test
    public static void whenFlatMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");

        assertEquals(position, user.getPosition().get());
    }

    //����ֵ
    //filter() ����һ�� Predicate ���������ز��Խ��Ϊ true ��ֵ��
    // ������Խ��Ϊ false���᷵��һ���յ� Optional��
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

    //ͨ����ʵ��ת��Ϊ Stream ��������ӹ��� Stream API ������
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
    ע�⣺
    ��ʹ�� Optional ��ʱ����Ҫ����һЩ���飬�Ծ���ʲôʱ������ʹ������
    ��Ҫ��һ���� Optional ���� Serializable����ˣ�����Ӧ����������ֶΡ�
    Optional ��Ҫ�����������͡�
     */

}
