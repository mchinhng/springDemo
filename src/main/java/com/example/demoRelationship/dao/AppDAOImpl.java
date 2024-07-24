package com.example.demoRelationship.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demoRelationship.entity.Course;
import com.example.demoRelationship.entity.Instructor;
import com.example.demoRelationship.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;

	// inject entity manager using constructor injection
	@Autowired
	public AppDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		entityManager.persist(theInstructor);

	}

	@Override
	public Instructor findInstructorById(int theId) {
		return entityManager.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int theId) {
		// retrieve the instructor
		Instructor tempInstructor = entityManager.find(Instructor.class, theId);

		// get course
		List<Course> courses = tempInstructor.getCourses();

		for (Course tempCourse : courses) {
			tempCourse.setInstructor(null);
		}

		// delete instructor
		entityManager.remove(tempInstructor);

	}

	@Override
	public InstructorDetail findInstructorDetailById(int theId) {
		return entityManager.find(InstructorDetail.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int theId) {
		// retrieve the instructorDetail
		InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

		// change cascade in instructor detail class by not include DELETE
		// delete just instructor detail
		tempInstructorDetail.getInstructor().setInstructorDetail(null);

		// delete instructorDetail
		entityManager.remove(tempInstructorDetail);
	}

	@Override
	public List<Course> findCoursesByInstructorId(int theId) {
		// create query
		TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

		query.setParameter("data", theId);

		// execute query
		List<Course> courses = query.getResultList();

		return courses;
	}

	@Override
	@Transactional
	public void update(Instructor tempInstructor) {
		entityManager.merge(tempInstructor);
	}

	@Override
	public void updateCourse(Course tempCourse) {
		entityManager.merge(tempCourse);

	}

	@Override
	@Transactional
	public void saveCourse(Course theCourse) {
		entityManager.persist(theCourse);
	}

}
