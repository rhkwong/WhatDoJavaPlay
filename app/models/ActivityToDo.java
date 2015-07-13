package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class ActivityToDo extends Model{
	
	@Id
	public Long id;
	public String name;
	public  String description;
	public int numPeople;
	public ArrayList <String> tags;
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<User> members = new ArrayList<User>();
	
	public ActivityToDo(String name, String description, int numPeople, ArrayList <String> tags, User owner){
		this.name = name;
		this.description = description;
		this.numPeople = numPeople;
		this.tags = tags;
		members.add(owner);
	}
	
	public static Model.Finder<Long,ActivityToDo> find = new Model.Finder(Long.class, ActivityToDo.class);
	
	
	public static ActivityToDo create(String name, String description, int numPeople, ArrayList <String> tags, String owner){
		ActivityToDo activity = new ActivityToDo(name, description, numPeople, tags, User.find.ref(owner));
		activity.save();
		activity.saveManyToManyAssociations("members");
        return activity;
	}
	
	public static List<ActivityToDo> findInvolving(String user) {
        return find.where()
            .eq("members.email", user)
            .findList();
    }
}
