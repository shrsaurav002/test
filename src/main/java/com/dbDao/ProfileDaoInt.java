package com.dbDao;

import java.util.ArrayList;
import java.util.List;

import com.profileDTO.ProfileDTO;

public interface ProfileDaoInt {

	ProfileDTO login(String pusername, String ppassword);

	int delete(String email);

	ArrayList<ProfileDTO> profile();

	List<String> qual();

	ProfileDTO edit(String pemail);

	ArrayList<ProfileDTO> qualfilter(String qual);

	String resetpw(String username);

	ArrayList<ProfileDTO> dbsearch(String search);

	int signup(ProfileDTO profileDTO);

	ArrayList<ProfileDTO> sort(String order);

	int update(ProfileDTO profileDTO);

	ProfileDTO srchUser(String pemail);

	void connect();

}
