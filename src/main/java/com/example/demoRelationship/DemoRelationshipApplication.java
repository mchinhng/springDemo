package com.example.demoRelationship;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demoRelationship.dao.AppDAO;
import com.example.demoRelationship.entity.Course;
import com.example.demoRelationship.entity.Instructor;
import com.example.demoRelationship.entity.InstructorDetail;
import com.example.demoRelationship.entity.Review;
import com.example.demoRelationship.entity.Student;

@SpringBootApplication
public class DemoRelationshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRelationshipApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			 createInstructor(appDAO);

//			 findInstructor(appDAO);

//			 deleteInstructor(appDAO);

//			 findInstructorDetail(appDAO);

//			deleteInstructorDetail(appDAO);

//			createInstructorWithCourse(appDAO);

//			findInstructorWithCourses(appDAO);

//			findCoursesWithInstructor(appDAO);

//			updateInstructor(appDAO);

			createCourseAndReviews(appDAO);

//			createCourseAndStudent(appDAO);
		};
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("yaya1");

		// create students
		Student tempStudent1 = new Student("ok", "ng", "pending");
//		Student tempStudent2 = new Student("ok2", "ng2", "pending2");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
//		tempCourse.addStudent(tempStudent2);

		// save the course and student
		appDAO.saveCourse(tempCourse);
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("yaya1111");

		// add some reviews
		tempCourse.addReview(new Review("Great111"));
		tempCourse.addReview(new Review("Cool111"));
		tempCourse.addReview(new Review("idiot!111"));

		appDAO.saveCourse(tempCourse);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		tempInstructor.setFirstName("xinh xan");

		appDAO.update(tempInstructor);
	}

	private void findCoursesWithInstructor(AppDAO appDAO) {
		int theId = 3;

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// find coursed for instructor LAZY
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		System.out.println("all " + courses);

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 3;

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println(tempInstructor);
		System.out.println("\n-------");
		System.out.println(tempInstructor.getCourses());

	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("chinh111222", "chinh111122", "chinh211122");
		InstructorDetail tempInstructorDetail = new InstructorDetail("lala11122", "swim111122");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create new courses
		Course tempCourse1 = new Course("kaka22");
		Course tempCourse2 = new Course("hihi222");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		appDAO.save(tempInstructor);

	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 3;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("chinh", "chinh1", "chinh2");
		InstructorDetail tempInstructorDetail = new InstructorDetail("lala", "swim");

		tempInstructor.setInstructorDetail(tempInstructorDetail);
		appDAO.save(tempInstructor);

	}

}
