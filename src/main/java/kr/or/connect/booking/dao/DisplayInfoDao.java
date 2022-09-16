package kr.or.connect.booking.dao;



import static kr.or.connect.booking.dao.DisplayInfoDaoSqls.*;

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

import kr.or.connect.booking.dto.DisplayInfo;


@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
	}
	

	
	
	public List<DisplayInfo> getDisplayInfo(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		
		return jdbc.query(GET_DIPLAY_INFO, params, rowMapper);
	}
	
	public List<DisplayInfo> getDetailInfo(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		
		return jdbc.query(GET_DETAIL_INFO, params, rowMapper);
	}
	
	public List<DisplayInfo> getLocation(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		
		return jdbc.query(GET_LOCATION, params, rowMapper);
	}
	

	

	
	
}
