package ly.study.cy.controller;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ly.study.cy.bean.Student;
import ly.study.cy.dao.cache.BookRepository;
import ly.study.cy.service.GitHubLookupService;

@Component
public class AppRunner implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final BookRepository bookRepository;

	public AppRunner(BookRepository bookRepository, GitHubLookupService gitHubLookupService) {
		this.bookRepository = bookRepository;
		this.gitHubLookupService = gitHubLookupService;
	}

	private final GitHubLookupService gitHubLookupService;

	@Override
	public void run(String... args) throws Exception {
		logger.info(".... Fetching books");
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		// Start the clock
		long start = System.currentTimeMillis();

		// Kick of multiple, asynchronous lookups
		Future<Student> page1 = gitHubLookupService.findUser("PivotalSoftware");
		Future<Student> page2 = gitHubLookupService.findUser("CloudFoundry");
		Future<Student> page3 = gitHubLookupService.findUser("Spring-Projects");

		// Wait until they are all done
		while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
			Thread.sleep(10); // 10-millisecond pause between each check
		}

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + page1.get());
		logger.info("--> " + page2.get());
		logger.info("--> " + page3.get());
	}

}
