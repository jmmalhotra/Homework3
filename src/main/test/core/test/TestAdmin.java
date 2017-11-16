package core.test;

import core.api.IAdmin;
import core.api.IStudent;
import core.api.impl.Admin;
import core.api.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestAdmin {

    private IAdmin admin;
    private IStudent student; 

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void baseTest() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void baseTest1() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
        /* Testing for invalid year */
    }
    
    @Test 
    public void instructorLimitOnCourses() {
        this.admin.createClass("Course", 2017, "Instructor", 15);
        this.admin.createClass("Course2", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Course2", 2016));
    }
    
    @Test
    public void instructorLimitOnCourses1() {
        this.admin.createClass("Course", 2017, "Instructor", 15);
        this.admin.createClass("Course2", 2017, "Instructor", 15);
        this.admin.createClass("Course3", 2017, "Instructor", 15);
        assertFalse(this.admin.classExists("Course3", 2016));
    }
    
    @Test 
    public void createClassGreaterThanZero() {
    		this.admin.createClass("Test",2017,"Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
        /* The capacity of the class createClass must be greater than zero
         * but if it's 0 or less then it must throw error (assertFalse) */
    }
    
    @Test
    public void CreateClassNegative() { 
        this.admin.createClass("Test",2017,"Instructor", -1);
        assertFalse(this.admin.classExists("Test", 2017));
        /* The capacity of the class createClass must never be negative but if it's negative
         * then it must throw error (assertFalse) */
    }
    
    @Test
    public void changeCapacityBaseTest() { 
        this.admin.createClass("Test", 2017, "Instructor", 4);
        this.student.registerForClass("Student", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 5);
        assertEquals(5, this.admin.getClassCapacity("Test", 2017));
        /* Base test to see if change capacity works */
    }

    @Test
    public void checksForSameNumberCapacity() { 
        this.admin.createClass("Test", 2017, "Instructor", 1);
        this.admin.changeCapacity("Test", 2017, 1);
        assertEquals(1, this.admin.getClassCapacity("Test", 2017));
        /*  New capacity of this class, must be at least equal to 
         * the number of students enrolled */
    }
    
    @Test
    public void checksForLessThanNoOfStudentsEnrolled() { 
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("Student", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 1);
        assertEquals(2, this.admin.getClassCapacity("Test", 2017));
        /* It should throw an error when the capacity is less than the 
         * number of students enrolled */
    }
    
    @Test
    public void checksForCapacityZero() { 
        this.admin.createClass("Test", 2017, "Instructor", 1);
        this.admin.changeCapacity("Test", 2017, 0);
        assertEquals(1, this.admin.getClassCapacity("Test", 2017));
        /* Class capacity can never be equal to zero*/
    }
    
    @Test
    public void classDoesNotExistCapacity() { 
        this.admin.changeCapacity("Test", 2017, 10);
        assertEquals(-1, this.admin.getClassCapacity("Test",2017));
        /* The capacity of the class should not be changed if that particular 
         * class is not registered */
    }

}
