package ua.com.goit.gojava7.kickstarter;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.goit.gojava7.kickstarter.Level.CategoryLevel;
import ua.com.goit.gojava7.kickstarter.Level.Level;
import ua.com.goit.gojava7.kickstarter.Level.MenuLevel;
import ua.com.goit.gojava7.kickstarter.Level.PaymentLevel;
import ua.com.goit.gojava7.kickstarter.Level.PledgeLevel;
import ua.com.goit.gojava7.kickstarter.Level.ProjectLevel;
import ua.com.goit.gojava7.kickstarter.console.ConsolePrinter;
import ua.com.goit.gojava7.kickstarter.console.ConsoleScanner;
import ua.com.goit.gojava7.kickstarter.dao.CategoryDao;
import ua.com.goit.gojava7.kickstarter.dao.PaymentDao;
import ua.com.goit.gojava7.kickstarter.dao.ProjectDao;
import ua.com.goit.gojava7.kickstarter.dao.QuestionDao;
import ua.com.goit.gojava7.kickstarter.dao.QuoteDao;
import ua.com.goit.gojava7.kickstarter.dao.RewardDao;
import ua.com.goit.gojava7.kickstarter.domain.Category;
import ua.com.goit.gojava7.kickstarter.domain.Project;

public class Kickstarter {
	private ConsolePrinter consolePrinter;
	private ConsoleScanner consoleScanner;

	//private DaoProvider daoProvider;
	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private RewardDao rewardDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private ProjectDao projectDao;
	
	private List<Level> levels;

	public Kickstarter(ConsolePrinter consolePrinter,
			ConsoleScanner consoleScanner) {	
		this.consolePrinter = consolePrinter;
		this.consoleScanner = consoleScanner;	

		//this.daoProvider = daoProvider;
		//ProjectDao projectDao = daoProvider.getProjectReader();
		//RewardDao rewardDao = daoProvider.getRewardsReader();
		//QuestionDao questionDao = daoProvider.getQuestionReader();
		//PaymentDao paymentDao = daoProvider.getPaymentReader();
		
		ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        
		ApplicationContext context = new ClassPathXmlApplicationContext("././src/main/webapp/WEB-INF/applicationContext.xml");
		quoteDao = context.getBean(QuoteDao.class);
		categoryDao = context.getBean(CategoryDao.class);
		projectDao = context.getBean(ProjectDao.class);
		paymentDao = context.getBean(PaymentDao.class);
		questionDao = context.getBean(QuestionDao.class);
		rewardDao = context.getBean(RewardDao.class);
		
		levels = new LinkedList<>(Arrays.asList(
				new MenuLevel(categoryDao),
				new CategoryLevel(projectDao, paymentDao),
				new ProjectLevel(paymentDao, questionDao),
				new PaymentLevel(questionDao, rewardDao),
				new PledgeLevel(paymentDao, rewardDao)));
	}

	public void runKickstarter() {

		//QuoteDao quoteDao = daoProvider.getQuoteReader();
		consolePrinter.print(quoteDao.getRandomQuote());

		ListIterator<Level> levelsIterator = levels.listIterator();
		
		Level userPositionLevel = levelsIterator.next();
		int userChoise = 0;
		Category selectedCategory = null;
		Project selectedProject = null;
		String answer;

		answer = userPositionLevel.generateAnswer(userChoise,
				selectedCategory, selectedProject);

		consolePrinter.print(answer);

		boolean notExit = true;
		while (notExit) {

			userChoise = consoleScanner.scan();

			if (userChoise == 0) {
				if (levelsIterator.nextIndex() == 1) {
					notExit = false;
					continue;
				}
			}

			answer = userPositionLevel.validateUserChoise(userChoise,
					selectedCategory, selectedProject);
			if (!answer.equals("")) {
				consolePrinter.print(answer);
				continue;
			}
			
			selectedCategory = userPositionLevel.findSelectedCategory(
					userChoise, selectedCategory);
			selectedProject = userPositionLevel.findSelectedProject(
					userChoise, selectedCategory, selectedProject);

			userPositionLevel = findNewUserPositionLevel(levelsIterator,
					userChoise);
			userChoise--;

			answer = userPositionLevel.generateAnswer(userChoise,
					selectedCategory, selectedProject);
			if (!answer.equals("")) {
				consolePrinter.print(answer);
			}

			answer = userPositionLevel.fillOutForm(selectedProject,
					userChoise + 1, consoleScanner);
			if (!answer.equals("")) {
				consolePrinter.print(answer);
			}

		}
		consolePrinter.print("Goodbye!");
	}

	public void shutdown() {
		consoleScanner.close();
	}

	private Level findNewUserPositionLevel(ListIterator<Level> listIterator,
			int userChoise) {

		if (userChoise == 0) {
			listIterator.hasPrevious();
			listIterator.previous();
			listIterator.previous();

		}
		return listIterator.next();
	}
}
