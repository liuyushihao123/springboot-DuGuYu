package ly.study.cy;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
// @EnableConfigurationProperties({ ConfigBean.class, User.class })
@PropertySource(value = "classpath:test.properties")
@EnableCaching
@EnableAsync
public class SpringbootFirstApplication  extends AsyncConfigurerSupport {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootFirstApplication.class);

	public static void main(String[] args) throws Exception {
		System.setProperty("spring.devtools.restart.enabled", "false");
		// ConfigurableApplicationContext ctx =
		SpringApplication.run(SpringbootFirstApplication.class, args);
		// StringRedisTemplate template =
		// ctx.getBean(StringRedisTemplate.class);
		// CountDownLatch latch = ctx.getBean(CountDownLatch.class);
		// LOGGER.info("Sending message...");
		// template.convertAndSend("chat", "Hello from Redis!");
		// latch.await();
		// System.exit(0);
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	// return args -> {
	// System.out.println("Let's inspect the beans provided by Spring Boot:");
	// String[] beanNames = ctx.getBeanDefinitionNames();
	// Arrays.sort(beanNames);
	// for (String beanName : beanNames) {
	// System.out.println(beanName);
	// }
	// };
	// }

	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			// WebAppResourceLoader 配置root路径是关键
			WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource("classpath:/templates").getFile().getPath());
			beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 读取配置文件信息
		return beetlGroupUtilConfiguration;
	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}

	// 配置包扫描
	@Bean(name = "beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage("ly.study.cy.dao.beatlsql");
		conf.setDaoSuffix("Dao");
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return conf;
	}

	@Bean(name = "sqlManagerFactoryBean")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setInterceptors(new Interceptor[] { new DebugInterceptor() });
		factory.setNc(new UnderlinedNameConversion());// 开启驼峰
		factory.setSqlLoader(new ClasspathLoader("/sql"));// sql文件路径
		return factory;
	}

	// 配置数据库
	@Bean(name = "datasource")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().url("jdbc:mysql://39.96.0.220:3306/test").username("root").password("root").build();
	}

	// 开启事务
	@Bean(name = "txManager")
	public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DataSource datasource) {
		DataSourceTransactionManager dsm = new DataSourceTransactionManager();
		dsm.setDataSource(datasource);
		return dsm;
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}
}
