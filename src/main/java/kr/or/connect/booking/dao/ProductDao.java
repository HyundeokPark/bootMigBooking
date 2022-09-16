package kr.or.connect.booking.dao;


import static kr.or.connect.booking.dao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.booking.dto.Product;


@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
	}
	
	public List<Product> selectAll(Integer start, Integer limit){
		Map<String,Integer> params = new HashMap<>();
		params.put("start",start);
		params.put("limit",limit);
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
	
	public List<Product> selectProducts(Integer start,  Integer limit, Integer category){
		Map<String,Integer> params = new HashMap<>();
		params.put("category_id",category);
		params.put("start",start);
		params.put("limit",limit);
		return jdbc.query(SELECT_PRODUCTS, params, rowMapper);
		
	}
	
	public int getCountApi() {
		return jdbc.queryForObject(GET_COUNT_API, Collections.emptyMap(), Integer.class);
	}
	
	public int getCount(Integer category) {
		Map<String,Integer> params = new HashMap<>();
		params.put("category_id",category);
		return jdbc.queryForObject(GET_COUNT, params, Integer.class);
	}
	
	public int getTotalCount() {
		return jdbc.queryForObject(GET_TOTALCOUNT, Collections.emptyMap(), Integer.class);
	}
	
}
