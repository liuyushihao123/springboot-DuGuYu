package ly.study.cy.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ly.study.cy.dao.mybatis.AccountMapper2;

@Service
public class AccountServiceImpl2 {
	@Autowired
	AccountMapper2 accountMapper2;

	@Transactional
	public void transfer() throws RuntimeException {
		accountMapper2.update(90, 1);// 用户1减10块 用户2加10块
		int i = 1 / 0;
		accountMapper2.update(110, 2);
	}
}
