package com.lima.service;

import com.lima.entity.Member;

public interface MemberService {
	void addNewMember(String name, String dateOfBirth, String gender, String phone, String address, String email,
			Long accountId, Boolean deleteFlag);

	Member findByAccountIdAndDeleteFlag(Long id, Boolean b);
}
