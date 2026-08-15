package org.example;

import java.util.List;

public interface UserRepo {
    User create(User user);
    User update(User user);
    void delete(Long id);
    User findId(Long id);
    List<User> findAll();
}
