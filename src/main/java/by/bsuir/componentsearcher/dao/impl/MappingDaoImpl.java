package by.bsuir.componentsearcher.dao.impl;

import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.dao.MappingDao;
import by.bsuir.componentsearcher.domain.FieldMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by vladislav on 12.03.17.
 */
@Repository
public class MappingDaoImpl implements MappingDao {

    private static final String INSERT_MAPPING_QUERY = "INSERT INTO field_mapping (fm_code, fm_name, fm_manufacturer, " +
            "fm_price, fm_file_name) " +
            "VALUES (?, ?, ?, ?, ?) on duplicate key UPDATE fm_code=VALUES(fm_code), fm_name=VALUES(fm_name), " +
            "fm_manufacturer=VALUES(fm_manufacturer), fm_price=VALUES(fm_price)";

    private static final String GET_FIELD_MAPPING_QUERY = "SELECT code, manufacturer, name, price from field_mapping" +
            " WHERE fm_file_name=?";


    private static final RowMapper<FieldMapping> rowMapper = (resultSet, i) -> {
        FieldMapping fieldMapping = new FieldMapping();

        fieldMapping.setManufacturer(resultSet.getString(ColumnName.MANUFACTURER));
        fieldMapping.setCode(resultSet.getString(ColumnName.CODE));
        fieldMapping.setName(resultSet.getString(ColumnName.NAME));
        fieldMapping.setPrice(resultSet.getString(ColumnName.PRICE));

        return fieldMapping;
    };

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MappingDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addMapping(FieldMapping fieldMapping, String fileName) {
        jdbcTemplate.update(INSERT_MAPPING_QUERY, fieldMapping.getCode(),
                fieldMapping.getName(), fieldMapping.getManufacturer(), fieldMapping.getPrice(), fileName);
    }

    @Override
    public FieldMapping getFileFieldMapping(String fileName) {
        return jdbcTemplate.queryForObject(GET_FIELD_MAPPING_QUERY, rowMapper, fileName);
    }
}
