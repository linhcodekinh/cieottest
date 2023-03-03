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
			Integer accountId, Boolean deleteFlag) {
		// TODO Auto-generated method stub
		memberRepository.saveMemberToRegister(name, dateOfBirth, gender, phone, address, accountId, deleteFlag);
	}

	@Override
	public Member findByAccountIdAndDeleteFlag(Integer id, Boolean b) {
		return memberRepository.findByAccountIdAndDeleteFlag(id, b);
	}

	@Override
	public Boolean existsById(Integer id) {
		return memberRepository.existsById(id);
	}

	@Override
	public void deleteByAccountId(Integer id) {
		memberRepository.deleteByAccountId(id);
	}

	@Override
	public void updateMember(String name, String dateOfBirth, String gender, String phone, String address,
			Integer accountId, boolean delete_flag) {
		memberRepository.updateMember(name, dateOfBirth, gender, phone, address, accountId, delete_flag);
	}

}
