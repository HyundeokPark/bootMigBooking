package kr.or.connect.booking.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;
import static kr.or.connect.booking.dao.CategoryDaoSqls.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.booking.dto.Category;



@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
	}
	
	public List<Category> selectCategory(){
		return jdbc.query(SELECT_CATEGORY, Collections.emptyMap(), rowMapper);
	}
	
	
	
	
}
