package com.lima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.entity.Member;
import com.lima.repository.MemberRepository;
import com.lima.service.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void addNewMember(String name, String dateOfBirth, String gender, String phone, String address,
			Long accountId, Boolean deleteFlag) {
		// TODO Auto-generated method stub
		memberRepository.saveMemberToRegister(name, dateOfBirth, gender, phone, address, accountId, deleteFlag);
	}

	@Override
	public Member findByAccountIdAndDeleteFlag(Long id, Boolean b) {
		return memberRepository.findByAccountIdAndDeleteFlag(id, b);
	}

}
