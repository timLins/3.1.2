package kata.project.dao;

import kata.project.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUser(int id);
    void update(int id, User user);
    void delete(int id);
}
