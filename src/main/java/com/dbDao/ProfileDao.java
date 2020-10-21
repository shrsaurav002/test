package com.dbDao;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.profileDTO.ProfileDTO;

@Repository
public class ProfileDao implements ProfileDaoInt {

	@Autowired

	private JdbcTemplate jdbcTemplate;

	@Override
	public void connect() {

	}

	@Override
	public ProfileDTO login(String pusername, String ppassword) {
		ProfileDTO profileDTO = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl where username=? and password=?";
		Object[] data = { pusername, ppassword };
		List<ProfileDTO> prof = jdbcTemplate.query(sql, data, new BeanPropertyRowMapper<>(ProfileDTO.class));
		if (prof.size() != 0)
			profileDTO = prof.get(0);

		return profileDTO;
	}

	@Override
	public int delete(String email) {
		int i = 0;
		String sql = "delete from user_login_tbl where email=?";
		Object[] data = { email };
		i = jdbcTemplate.update(sql, data);
		return i;
	}

	@Override
	public ArrayList<ProfileDTO> profile() {
		List<ProfileDTO> users = new ArrayList<ProfileDTO>();
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl";
		users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileDTO.class));
		return (ArrayList<ProfileDTO>) users;
	}

	@Override
	public List<String> qual() {

		String sql = "select distinct qualification from user_login_tbl";
		List<String> qualifications = jdbcTemplate.queryForList(sql, String.class);
		return qualifications;

	}

	@Override
	public ProfileDTO edit(String pemail) {

		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl where email=?";
		Object[] data = { pemail };
		List<ProfileDTO> user = jdbcTemplate.query(sql, data, new BeanPropertyRowMapper<>(ProfileDTO.class));

		return user.get(0);

	}

	@Override
	public ArrayList<ProfileDTO> qualfilter(String qual) {

		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl where qualification=?";
		Object[] data = { qual };
		List<ProfileDTO> users = jdbcTemplate.query(sql, data, new BeanPropertyRowMapper<>(ProfileDTO.class));
		return (ArrayList<ProfileDTO>) users;
	}

	@Override
	public String resetpw(String usernameEmail) {
		String pw = "";
		String sql = "select password from user_login_tbl where username=? or email=?";
		Object[] data = { usernameEmail, usernameEmail };
		try {
			pw = jdbcTemplate.queryForObject(sql, data, String.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pw;
	}

	@Override
	public ArrayList<ProfileDTO> dbsearch(String search) {
		List<ProfileDTO> users = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl where name like '%"
				+ search + "%' or qualification like '%" + search + "%'";
		users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileDTO.class));

		return (ArrayList<ProfileDTO>) users;

	}

	@Override
	public int signup(ProfileDTO profileDTO) {
		int i = 0;

		String sql = "insert into user_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createddate) values(?,?,?,?,?,?,?,?,?)";
		Timestamp createddate = new Timestamp(System.currentTimeMillis());
		Object[] data = { profileDTO.getUsername(), profileDTO.getPassword(), profileDTO.getName(),
				profileDTO.getEmail(), profileDTO.getQualification(), profileDTO.getMobile(), profileDTO.getPhoto(),
				profileDTO.getGender(), createddate };
		i = jdbcTemplate.update(sql, data);
		return i;
	}

	@Override

	public ArrayList<ProfileDTO> sort(String order) {
		List<ProfileDTO> users = new ArrayList<ProfileDTO>();
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl order by email "
				+ order;

		users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProfileDTO.class));
		;
		return (ArrayList<ProfileDTO>) users;
	}

	@Override
	public int update(ProfileDTO profileDTO) {
		int i = 0;
		String sql = "update user_login_tbl set password=?, name=?,email=?,qualification=?,mobile=?,photo=?,gender=? where username=?";
		Object[] data = { profileDTO.getPassword(), profileDTO.getName(), profileDTO.getEmail(),
				profileDTO.getQualification(), profileDTO.getMobile(), profileDTO.getPhoto(), profileDTO.getGender(),
				profileDTO.getUsername() };
		i = jdbcTemplate.update(sql, data);

		return i;
	}

	@Override
	public ProfileDTO srchUser(String pemail) {
		ProfileDTO profileDTO = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createddate from user_login_tbl where email=?";
		String[] user = { pemail };
		List<ProfileDTO> users = jdbcTemplate.query(sql, user, new BeanPropertyRowMapper<>(ProfileDTO.class));
		if (users.size() != 0) {
			profileDTO = users.get(0);
		}
		return profileDTO;

	}

	public int registerNew(ProfileDTO profileDTO) {
		int i = 0;
		byte[] image = null;
		try {
			image = profileDTO.getImage().getBytes();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		LobHandler lobHandler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = new SqlLobValue(image, lobHandler);
		String sql = "insert into user_login_tbl1(username,password,name,email,qualification,mobile,photo,gender,createddate) values(?,?,?,?,?,?,?,?,?)";
		Timestamp createddate = new Timestamp(System.currentTimeMillis());
		Object[] data = { profileDTO.getUsername(), profileDTO.getPassword(), profileDTO.getName(),
				profileDTO.getEmail(), profileDTO.getQualification(), profileDTO.getMobile(), sqlLobValue,
				profileDTO.getGender(), createddate };
		int[] dataType = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR,

				Types.BLOB, Types.VARCHAR, Types.TIMESTAMP };

		i = jdbcTemplate.update(sql, data, dataType);
		return i;

	}

}
