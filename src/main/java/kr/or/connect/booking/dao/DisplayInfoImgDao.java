package kr.or.connect.booking.dao;

import static kr.or.connect.booking.dao.DisplayInfoImageDaoSqls.*;



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

import kr.or.connect.booking.dto.DisplayInfoImage;


@Repository
public class DisplayInfoImgDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImgDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
	}
	

	public List<DisplayInfoImage> getDisplyImg(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
	
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(GET_DISPLAYIMG, params, rowMapper);
	}
	

	

	
	
}
