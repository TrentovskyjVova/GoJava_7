package ua.com.goit.gojava7.kickstarter.domain;

public class Project {
	private String name;
	private int id;
	private int categoryId;
	private int daysToGo;
	private String description;
	private String owner;
	private int goal;
	private String videoUrl;
	private int amountPledge;
	
	public Project() {
		
	}
	
	public Project(String name, int id) {
		setName(name);
		this.id = id;
		setCategoryId(0);
		setDaysToGo(40);
		setDescription("");
		setOwner("");
		setGoal(0);
		setVideoUrl("");
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDaysToGo() {
		return daysToGo;
	}

	public void setDaysToGo(int daysToGo) {
		this.daysToGo = daysToGo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getFunded() {
		int goal = getGoal();
		return goal == 0 ? 0 : getAmountPledge() * 100 / goal;
	}

	public int getAmountPledge() {
		return amountPledge;
	}

	public void setAmountPledge(int amountPledge) {
		this.amountPledge = amountPledge;
	}
}
