package ly.study.cy.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 这个实体类，在2个属性:name,age.它们各自有验证的注解：
 * 
 * @Size(min=2, max=30) name的长度为2-30个字符
 * @NotNull 不为空
 * @Min(18)age不能小于18
 * @author liuyu
 *
 */
public class PersonForm {

	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@NotNull
	@Min(18)
	private Integer age;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String toString() {
		return "Person(Name: " + this.name + ", Age: " + this.age + ")";
	}
}
