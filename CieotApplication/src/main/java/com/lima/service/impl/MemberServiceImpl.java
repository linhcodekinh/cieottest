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
	public void addNewMember(String name, String firstName, String lastName, String dateOfBirth, Integer gender,
			String phone,String image, String address, Boolean deleteFlag, Integer accountId) {
		// TODO Auto-generated method stub
		memberRepository.saveMemberToRegister(name, firstName, lastName, dateOfBirth, gender, phone, image, address,
				deleteFlag, accountId);
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
	public void updateMember(String name, String firstName, String lastName, String dateOfBirth, Integer gender,
			String phone, String image, String address, boolean delete_flag, Integer accountId) {
		memberRepository.updateMember(name, firstName, lastName, dateOfBirth, gender, phone, image, address, delete_flag,
				accountId);
	}

}
