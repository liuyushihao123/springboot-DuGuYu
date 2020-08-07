package ly.study.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动springboot工程，控制台没过5s就打印出了当前的时间。
 * 
 * @author liuyu
 *
 */
@SpringBootApplication
// @EnableScheduling
public class SpringbootSchedulingTasksApplication {
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SpringbootSchedulingTasksApplication.class, args);
	}
}
