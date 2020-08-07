package ly.study.cy.service;

import java.util.List;

import ly.study.cy.bean.Account;

public interface IAccountService {
	int add(Account account);

	int update(Account account);

	int delete(int id);

	Account findAccountById(int id);

	List<Account> findAccountList();
}
