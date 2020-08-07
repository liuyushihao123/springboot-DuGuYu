package ly.study.cy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ly.study.cy.bean.Account;
import ly.study.cy.service.mybatis.AccountServiceImpl;

@RestController
@RequestMapping("/account4")
public class AccountMybatisController {
	@Autowired
	AccountServiceImpl accountService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Account> getAccounts() {
		return accountService.findAccountList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable("id") int id) {
		return accountService.findAccount(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "money", required = true) double money) {
		int t = accountService.update(name, money, id);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") int id) {
		int t = accountService.delete(id);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postAccount(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {

		int t = accountService.add(name, money);
		if (t == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

}
