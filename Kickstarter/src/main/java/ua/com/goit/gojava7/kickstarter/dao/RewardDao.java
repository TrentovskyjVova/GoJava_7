package ua.com.goit.gojava7.kickstarter.dao;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.domain.Reward;

public interface RewardDao extends Dao {
	List<Reward> getRewards(int projectId);
	
	Reward getReward(int userChoise, int projectId);

	int size(int projectId);

	Reward getReward(int rewardId);

}
