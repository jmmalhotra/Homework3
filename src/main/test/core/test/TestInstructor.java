package core.test;

import core.api.IAdmin;
import core.api.IInstructor;
import core.api.impl.Admin;
import core.api.IStudent;
import core.api.impl.Student;
import core.api.impl.Instructor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestInstructor {

    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor = new Instructor();
        this.student = new Student();
        
    }
    
    @Test
    public void baseTestForAddHomework() {     
    		this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
        assertTrue(instructor.homeworkExists("Test", 2017, "HW1"));
        /* Base test to check AddHomework */
    }
    @Test
    public void checksForHomework() { 
        this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
        assertFalse(instructor.homeworkExists("Test", 2017, "HW1"));
        /* cannot add homework if not in class */
    }

    @Test
    public void checksForClassThatMustExists() { 
        this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
        assertFalse(instructor.homeworkExists("Test", 2017, "HW1"));
        /* class must exist */
        
    }
    
    @Test
    public void checksForValidYear() { 
        instructor.addHomework("Instructor", "Test", 2016, "HW1");
        assertFalse(instructor.homeworkExists("Test", 2016, "HW1"));
        /* must be a valid year */
    }
    
    @Test
    public void baseCaseForAssignGradeClass() { 
        this.student.registerForClass("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "HW1");
        student.submitHomework("Student", "HW1", "Anything", "Test", 2017);
        instructor.assignGrade("Instructor", "Test", 2017, "HW1", "Student", 15);
        assertEquals(new Integer(15), instructor.getGrade("Test", 2017, "HW1","Student"));       
        /* base test case for assign grade class */
    }
    

}
