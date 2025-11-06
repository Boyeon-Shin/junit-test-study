package ch08.user.v1;

public class SpecialApp {
    public boolean loginUser(String key, String password) {
        IUserCache cache = UserCache.getInstance();
        IUserDetails foundUser = cache.getUser(key);
        if (foundUser != null && foundUser.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
