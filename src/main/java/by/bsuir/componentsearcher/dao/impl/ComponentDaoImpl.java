package by.bsuir.componentsearcher.dao.impl;

import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.domain.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
@Repository
public class ComponentDaoImpl implements ComponentDao {

    private static final String SEARCH_COMPONENT_BY_CODE_QUERY = "SELECT code, manufacturer, name, price from component" +
            " where code = ?;";

    private static final String DELETE_FILE_QUERY = "delete from component where co_file_name = ?;";

    private static final String INSERT_COMPONENT_QUERY = "INSERT INTO component (code, manufacturer, " +
            "name, price, co_file_name) VALUES (?, ?, ?, ?, ?);";

    private static final String CREATE_FILE_QUERY = "INSERT INTO file (fi_file_name) VALUES (?) on duplicate KEY " +
            "update fi_file_name=VALUES(fi_file_name);";

    private static final RowMapper<Component> rowMapper = (rs, rowNum) -> {
        Component component = new Component();

        component.setCode(rs.getString(ColumnName.CODE));
        component.setManufacturer(rs.getString(ColumnName.MANUFACTURER));
        component.setName(rs.getString(ColumnName.NAME));
        component.setPrice(rs.getDouble(ColumnName.PRICE));

        return component;
    };

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ComponentDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Component findByCode(String code) {
        return jdbcTemplate.queryForObject(SEARCH_COMPONENT_BY_CODE_QUERY, new Object[]{code}, rowMapper);
    }

    @Override
    public void deleteFileComponents(String fileName){
        jdbcTemplate.update(DELETE_FILE_QUERY, fileName);
    }

    @Override
    public void createFile(String fileName){
        jdbcTemplate.update(CREATE_FILE_QUERY, fileName);
    }

    @Override
    public void insertComponents(List<Component> components, final String fileName){
        jdbcTemplate.batchUpdate(INSERT_COMPONENT_QUERY, components, components.size(), (ps, argument) -> {
            ps.setString(1, argument.getCode());
            ps.setString(2, argument.getManufacturer());
            ps.setString(3, argument.getName());
            ps.setDouble(4, argument.getPrice());
            ps.setString(5, fileName);
        });
    }

}
