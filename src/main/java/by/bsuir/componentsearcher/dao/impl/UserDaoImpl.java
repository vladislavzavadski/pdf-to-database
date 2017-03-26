package by.bsuir.componentsearcher.dao.impl;

import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.dao.UserDao;
import by.bsuir.componentsearcher.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by vladislav on 21.03.17.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final String GET_USER_QUERY = "SELECT username, password, first_name, last_name from user " +
            "where username=?";
    private static final String GET_AUTHORITY_QUERY = "SELECT authority_name from authority where username=?";

    private static final RowMapper<User> rowMapper = (rs, rowNum) ->{
      User user = new User();

      user.setUsername(rs.getString(ColumnName.USERNAME));
      user.setPassword(rs.getString(ColumnName.PASSWORD));
      user.setFirsName(rs.getString(ColumnName.FIRST_NAME));
      user.setLastName(rs.getString(ColumnName.LAST_NAME));

      return user;
    };

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getByUsername(String username) {
        User user = jdbcTemplate.queryForObject(GET_USER_QUERY, new Object[]{username}, rowMapper);

        user.setAuthorities(getAuthorities(username));
        return user;
    }

    private List<String> getAuthorities(String username){
        return jdbcTemplate.queryForList(GET_AUTHORITY_QUERY, new Object[]{username}, String.class);
    }

}
