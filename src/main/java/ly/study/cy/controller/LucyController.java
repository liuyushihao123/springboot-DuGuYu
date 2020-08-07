package ly.study.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ly.study.cy.bean.ConfigBean;
import ly.study.cy.bean.User;

@RestController
public class LucyController {
	@Autowired
	ConfigBean configBean;

	@RequestMapping(value = "/lucy")
	public String miya() {
		return configBean.getGreeting() + " >>>>" + configBean.getName() + " >>>>" + configBean.getUuid() + " >>>>" + configBean.getMax();
	}

	@Autowired
	User user;

	@RequestMapping(value = "/user")
	public String user() {
		return user.getName() + user.getAge();
	}
}
