package ly.study.cy.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper2 {
	@Update("update account set  money = #{money} where id = #{id}")
	int update(@Param("money") double money, @Param("id") int id);
}
