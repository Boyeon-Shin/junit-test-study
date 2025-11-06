package ch08.user.v1;

public interface IUserCache {
    void addUser(IUserDetails user);
    IUserDetails getUser(String key);
    void reset();
}
