package ly.study.cy.controller;

import java.util.List;

import org.beetl.sql.core.db.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ly.study.cy.bean.Account;
import ly.study.cy.dao.beatlsql.AccountBeatlDao;

@RestController
@RequestMapping("/account3")
public class AccountBeatlController {
	@Autowired
	AccountBeatlDao accountBeatlDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Account> getAccounts() {
		return accountBeatlDao.all();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable("id") int id) {
		return accountBeatlDao.unique(id);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Account getAccountById(@RequestParam("name") String name) {
		return accountBeatlDao.selectAccountByName(name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "money", required = true) double money) {
		Account account = new Account();
		account.setMoney(money);
		account.setName(name);
		account.setId(id);
		int t = accountBeatlDao.updateById(account);
		if (t == 1) {
			return account.toString();
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postAccount(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {
		Account account = new Account();
		account.setMoney(money);
		account.setName(name);
		KeyHolder t = accountBeatlDao.insertReturnKey(account);
		if (t.getInt() > 0) {
			return account.toString();
		} else {
			return "fail";
		}
	}
}
