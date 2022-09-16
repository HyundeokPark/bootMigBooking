package kr.or.connect.booking.dao;

import static kr.or.connect.booking.dao.CommentImgDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.booking.dto.CommentImg;
import kr.or.connect.booking.dto.ReservationInfo;



@Repository
public class CommentImgDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<CommentImg> rowMapper = BeanPropertyRowMapper.newInstance(CommentImg.class);
	
	public CommentImgDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment_image")
                .usingGeneratedKeyColumns("id");
	}
	
	public int insert(CommentImg cmntImg) {
//		SqlParameterSource params = new BeanPropertySqlParameterSource(cmntImg);
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", cmntImg.getImageId());
		params.addValue("reservation_info_id", cmntImg.getReservationInfoId());
		params.addValue("reservation_user_comment_id", cmntImg.getReservationUserCommentId());
		params.addValue("file_id", cmntImg.getFileId());
		
		return insertAction.executeAndReturnKey(params).intValue();
	}

	
	
	public List<CommentImg> getCommentImg(){
		return  jdbc.query(GET_CMNT_IMG, Collections.emptyMap(), rowMapper);
	}
	
	public List<CommentImg> downloadCommentImg(int imgId){
		Map<String,Integer> params = new HashMap<>();
		params.put("imgId" , imgId);
		return  jdbc.query(DOWNLOAD_CMNT_IMG, params, rowMapper);
	}
	
}
