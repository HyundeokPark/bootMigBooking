package kr.or.connect.booking.dao;

import static kr.or.connect.booking.dao.ImgFileDaoSqls.SELECT_IMG_BY_ID;

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

import kr.or.connect.booking.dto.ImgFile;

@Repository
public class ImgFileDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ImgFile> rowMapper = BeanPropertyRowMapper.newInstance(ImgFile.class);

	public ImgFileDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
	}

	public int insert(ImgFile file) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(file);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	public List<ImgFile> getImgById(int imgId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("imgId", imgId);
		return jdbc.query(SELECT_IMG_BY_ID, params, rowMapper);
	}
}
