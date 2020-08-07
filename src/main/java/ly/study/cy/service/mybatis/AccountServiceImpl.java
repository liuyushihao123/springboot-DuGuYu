package ly.study.cy.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ly.study.cy.bean.Account;
import ly.study.cy.dao.mybatis.AccountMapper;

@Service("mybatisaccountService")
public class AccountServiceImpl {
	@Autowired
	private AccountMapper accountMapper;

	public int add(String name, double money) {
		return accountMapper.add(name, money);
	}

	public int update(String name, double money, int id) {
		return accountMapper.update(name, money, id);
	}

	public int delete(int id) {
		return accountMapper.delete(id);
	}

	public Account findAccount(int id) {
		return accountMapper.findAccount(id);
	}

	public List<Account> findAccountList() {
		return accountMapper.findAccountList();
	}
}
