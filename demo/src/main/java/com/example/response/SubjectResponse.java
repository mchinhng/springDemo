package com.example.response;

import com.example.entity.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {
	private Long id;
	private String subjectName;
	private Double marks;

	public SubjectResponse(Subject subject) {
		this.id = subject.getId();
		this.subjectName = subject.getSubjectName();
		this.marks = subject.getMarks();
	}
}
