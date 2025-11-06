package ch08.user.v1;

import java.util.HashMap;
import java.util.Map;

public class UserCache implements IUserCache {

    private Map<String, IUserDetails> users = new HashMap<>();

    private static IUserCache instance;

    private UserCache() {
    }

    public static IUserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
        }
        return instance;
    }

    @Override
    public void addUser(final IUserDetails user) {
        if (users.containsKey(user.getKey())) {
            throw new IllegalStateException("user already exists");
        }
        users.put(user.getKey(), user);
    }

    @Override
    public IUserDetails getUser(final String key) {
        return users.get(key);
    }

    @Override
    public void reset() {
        users.clear();
    }
}
