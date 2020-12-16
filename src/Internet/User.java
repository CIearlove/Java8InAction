package Internet;

import java.util.Optional;

/**
 * @Description
 * @Author chenjr <chenjr@si-tech.com.cn>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/12/16
 */
public class User {

    private String email;

    private String name;

    private String position;

    public Optional<String> getPosition() {
        return Optional.ofNullable(position);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
