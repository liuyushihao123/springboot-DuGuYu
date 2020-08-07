package ly.study.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ly.study.cy.service.mybatis.AccountServiceImpl2;

@RestController
@RequestMapping("/transactional")
public class AccountTransactionalController {

	@Autowired
	AccountServiceImpl2 accountService;

	@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
	public void updateAccount() {
		accountService.transfer();
	}
}
