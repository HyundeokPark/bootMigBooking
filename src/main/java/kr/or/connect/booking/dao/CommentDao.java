package kr.or.connect.booking.dao;

import static kr.or.connect.booking.dao.CommentDaoSqls.GET_AVG_SCORE_ALL;
import static kr.or.connect.booking.dao.CommentDaoSqls.GET_AVG_SCORE_TRUNCATE;
import static kr.or.connect.booking.dao.CommentDaoSqls.GET_CMNT;
import static kr.or.connect.booking.dao.CommentDaoSqls.GET_CMNT_ALL;
import static kr.or.connect.booking.dao.CommentDaoSqls.GET_CMNT_API;
import static kr.or.connect.booking.dao.CommentDaoSqls.GET_COUNT;
import static kr.or.connect.booking.dao.CommentDaoSqls.GET_COUNT_API;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.booking.dto.Comment;


@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment")
                .usingGeneratedKeyColumns("id");
	}
	
	public int insert(Comment cmnt) {
//		SqlParameterSource params = new BeanPropertySqlParameterSource(cmnt);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("comment", cmnt.getComment());
		params.addValue("id", cmnt.getCommentId());
		params.addValue("product_id", cmnt.getProductId());
		params.addValue("reservation_info_id", cmnt.getReservationInfoId());
		params.addValue("score", cmnt.getScore());
		params.addValue("create_date", cmnt.getCreateDate());
		params.addValue("modify_date", cmnt.getModifyDate());
		
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	
	
	
	public List<Comment> getComment(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);		
		return  jdbc.query(GET_CMNT, params, rowMapper);
	}
	
	public List<Comment> getCommentAll(int displayInfoId){		
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		return  jdbc.query(GET_CMNT_ALL, params, rowMapper);
	}
	
	
	public List<Comment> getCommentApi(int cmntId){		
		Map<String,Integer> params = new HashMap<>();
		params.put("cmntId" , cmntId);
		return  jdbc.query(GET_CMNT_API, params, rowMapper);
	}
	
	
	
	
	
	public Double getAvg(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		return jdbc.queryForObject(GET_AVG_SCORE_TRUNCATE, params, Double.class);
	}
	
	
	
	public int getAvgAll(){
		return jdbc.queryForObject(GET_AVG_SCORE_ALL, Collections.emptyMap(), Integer.class);
	}
	
	
	public int getCountApi(){
		return  jdbc.queryForObject(GET_COUNT_API, Collections.emptyMap(), Integer.class);
	}
	
	public int getCount(int displayInfoId){
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId" , displayInfoId);
		return  jdbc.queryForObject(GET_COUNT, params, Integer.class);
	}
	

	

	
	
}
