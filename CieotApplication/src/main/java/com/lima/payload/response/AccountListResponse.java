package com.lima.payload.response;

import java.util.List;

import com.lima.dto.AccountDTO;

public class AccountListResponse {
	private List<AccountDTO> listAccount;
	private Integer totalItem;
	
	public AccountListResponse(List<AccountDTO> listAccountDTO, Integer totalItem) {
		super();
		this.listAccount = listAccountDTO;
		this.totalItem = totalItem;
	}
	public List<AccountDTO> getListAccountDTO() {
		return listAccount;
	}
	public void setListAccountDTO(List<AccountDTO> listAccountDTO) {
		this.listAccount = listAccountDTO;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	
	

}
