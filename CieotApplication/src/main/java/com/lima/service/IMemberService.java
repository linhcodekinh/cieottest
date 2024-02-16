package com.lima.service;

import com.lima.entity.Member;

public interface IMemberService {
	void addNewMember(String name, String firstName, String lastName, String dateOfBirth, Integer gender, String phone,
			String image, String address, Boolean deleteFlag, Integer accountId);

	Member findByAccountIdAndDeleteFlag(Integer id, Boolean b);

	Boolean existsById(Integer id);

	void deleteByAccountId(Integer id);

	void updateMember(String name, String firstName, String lastName, String dateOfBirth, Integer gender, String phone,
			String image, String address, boolean delete_flag, Integer accountId);
}
