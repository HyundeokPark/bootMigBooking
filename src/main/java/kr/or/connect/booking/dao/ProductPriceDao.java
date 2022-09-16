package kr.or.connect.booking.dao;

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
 
import kr.or.connect.booking.dto.ProductPrice;




import static kr.or.connect.booking.dao.ProductPriceDaoSqls.*;

@Repository
public class ProductPriceDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	public ProductPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
	}
	

	public List<ProductPrice> getPriceInfo(int displayInfoId) {
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		return jdbc.query(GET_PRICE_INFO, params, rowMapper);
	}

}
