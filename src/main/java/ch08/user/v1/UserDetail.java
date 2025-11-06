package ch08.user.v1;

public class UserDetail implements IUserDetails {

    private final String key;
    private final String password;

    public UserDetail(String key, String password) {
        this.key = key;
        this.password = password;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
