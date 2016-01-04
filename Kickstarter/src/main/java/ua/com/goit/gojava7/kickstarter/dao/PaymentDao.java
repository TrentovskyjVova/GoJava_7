package ua.com.goit.gojava7.kickstarter.dao;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.domain.Payment;
import ua.com.goit.gojava7.kickstarter.domain.Project;

public interface PaymentDao extends Dao{
	List<Payment> getPayments(int projectId);
	
	void addPayment(Payment payment);

	int getPledged(int projectId);
}
