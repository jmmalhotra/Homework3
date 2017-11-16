package core.test;

import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.IInstructor;
import core.api.impl.Instructor;
import core.api.IStudent;
import core.api.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestStudent {

    private IAdmin admin;
    private IStudent student;
    private IInstructor instructor;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.admin.createClass("Test", 2017, "Instructor", 1);
        this.student = new Student();
        this.instructor = new Instructor();
    }

    @Test 
    public void checksForRegisterForClass() { 
        this.student.registerForClass("Student", "Test", 2017);
        assertTrue(this.student.isRegisteredFor("Student", "Test", 2017));
        /* Base Test to check for this particular class */
    }
    
    @Test
    public void checksForFullCapacityOfClass() { 
        this.student.registerForClass("Student", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        assertFalse(student.isRegisteredFor("Student2", "Test", 2017));
        /* It should throw an error when student tries registering for a class 
         * when the class is already at full capacity */  
    }
    
    @Test
    public void checksForNoClassExistsRegister() { 
        this.student.registerForClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
        /* Cannot register for class that does not exist */
    }
    
    @Test
    public void checksDropClass() { 
        this.student.registerForClass("Student", "Test", 2017);
        assertTrue(this.student.isRegisteredFor("Student", "Test", 2017));
        this.student.dropClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
        /* base test case for drop class */
    }
    
    @Test
    public void checksForSubmitHomeworkClass() {
        this.student.registerForClass("stud", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
        this.student.submitHomework("Student", "HW1", "Anything", "Test", 2017);
        assertTrue(this.student.hasSubmitted("Student", "HW1", "Test", 2017));
        
       /* Base test case for submit homework class */
    }
    
    @Test
    public void checksIfHomeworkExists() { 
        this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
        this.student.submitHomework("Student", "HW1", "Anything", "Test", 2017);
        assertFalse(this.student.hasSubmitted("Student", "HW1", "Test", 2017));
        /* Homework exists */
    }
    
    @Test
    public void checksForInvalidHWSubmission() { 
        this.student.submitHomework("Student", "HW1", "Anything", "Test", 2017);
        assertFalse(this.student.hasSubmitted("Student", "HW1", "Test", 2017));
        /* checking for invalid homework submission */
    }

    @Test
    public void checksForInvalidYear() { 
        this.admin.createClass("Test", 2018, "Instructor", 10);
        this.instructor.addHomework("Instructor", "Test", 2018, "HW1");
        this.student.registerForClass("Student", "Test", 2018);
        this.student.submitHomework("Student", "HW1", "Anything", "Test", 2018);
        assertFalse(this.student.hasSubmitted("Student", "HW1", "Test", 2018));
        /* must be checked for different years */
    }
}
