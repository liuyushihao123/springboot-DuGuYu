package ly.study.cy.dao.beatlsql;

import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;

import ly.study.cy.bean.Account;

/**
 * 接口继承BaseMapper，就能获取单表查询的一些性质，当你需要自定义sql的时候，只需要在resouses/sql/account.
 * md文件下书写文件：
 * 
 * @author liuyu
 *
 */
public interface AccountBeatlDao extends BaseMapper<Account> {
	@SqlStatement(params = "name")
	Account selectAccountByName(String name);
}
