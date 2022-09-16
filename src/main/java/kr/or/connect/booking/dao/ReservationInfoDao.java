package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import static kr.or.connect.booking.dao.ReservationInfoDaoSqls.*;
import kr.or.connect.booking.dto.ReservationInfo;;



@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
	}
	
	
	public int insert(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public List<ReservationInfo> getReservation(String email){
		Map<String,String> params= new HashMap<>();
		params.put("email", email);
		return jdbc.query(GET_RESERVATION, params, rowMapper);
	}
	
	
	public List<ReservationInfo> getCncldReservation(int reservationInfoId){
		Map<String,Integer> params= new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.query(GET_CNCLD_RESERVATION, params, rowMapper);
	}
	
	
	public int cancelReservation(int reservationInfoId){
		Map<String,Integer> params= new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.update(CANCEL_RESERVATION, params);
	}
	
	
	
	
	
}
