package models;

import java.util.ArrayList;
import java.util.List;

import models.*;

import org.junit.*;

import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    @Test
    public void createAndRetrieveUser() {
        new User("bob@gmail.com", "Bob", "secret").save();
        User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }
    
    @Test
    public void tryAuthenticateUser() {
        new User("bob@gmail.com", "Bob", "secret").save();
        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }
    
    @Test
    public void findProjectsInvolving() {
        new User("bob@gmail.com", "Bob", "secret").save();
        new User("jane@gmail.com", "Jane", "secret").save();
        ActivityToDo.create("Fun Activity", "play around", 4,new ArrayList<String>(), "bob@gmail.com");
        ActivityToDo.create("Fun Activity 2", "play around", 4,new ArrayList<String>(), "jane@gmail.com");

        List<ActivityToDo> results = ActivityToDo.findInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Fun Activity", results.get(0).name);
    }
}