package ua.com.goit.gojava7.kickstarter.dao;

import ua.com.goit.gojava7.kickstarter.domain.Quote;

public interface QuoteDao extends Dao {
	
	Quote getRandomQuote();
	
}
