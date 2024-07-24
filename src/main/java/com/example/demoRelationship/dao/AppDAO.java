package com.example.demoRelationship.dao;

import java.util.List;

import com.example.demoRelationship.entity.Course;
import com.example.demoRelationship.entity.Instructor;
import com.example.demoRelationship.entity.InstructorDetail;

public interface AppDAO {
	void save(Instructor theInstructor);

	Instructor findInstructorById(int theId);

	void deleteInstructorById(int theId);

	InstructorDetail findInstructorDetailById(int theId);

	void deleteInstructorDetailById(int theId);

	List<Course> findCoursesByInstructorId(int theId);

	void update(Instructor tempInstructor);

	void updateCourse(Course tempCourse);

	void saveCourse(Course theCourse);
}
