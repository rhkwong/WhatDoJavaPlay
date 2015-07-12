package models;

public class ActivityToDo {
	private String description;
	private int numPeople;
	private String[] tags;
	
	public ActivityToDo(String description, int numPeople, String[] tags){
		this.description = description;
		this.numPeople = numPeople;
		this.tags = tags;
	}
	
	
	public String getDescription(){
		return description;
	}
	
	public int getNumPeople(){
		return numPeople;
				
	}
	
	public String[] getTags(){
		return tags;
	}
}
