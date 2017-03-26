package by.bsuir.componentsearcher.dao;

import by.bsuir.componentsearcher.domain.User;

/**
 * Created by vladislav on 21.03.17.
 */

public interface UserDao {
    User getByUsername(String username);
}
