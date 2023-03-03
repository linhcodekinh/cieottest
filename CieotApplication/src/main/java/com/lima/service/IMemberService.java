package com.lima.service;

import com.lima.entity.Member;

public interface IMemberService {
	void addNewMember(String name, String dateOfBirth, String gender, String phone, String address, Integer accountId,
			Boolean deleteFlag);

	Member findByAccountIdAndDeleteFlag(Integer id, Boolean b);

	Boolean existsById(Integer id);

	void deleteByAccountId(Integer id);

	void updateMember(String name, String dateOfBirth, String gender, String phone, String address, Integer accountId,
			boolean b);
}
