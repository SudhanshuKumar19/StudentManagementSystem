package com.StudentManagementSystem.service;

import java.util.List;

import com.StudentManagementSystem.entity.Student;

public interface StudentService {
	public List<Student> getAllStudents(); // Abstract method
	public Student saveStudent(Student student); // Abstract method
	public Student getById(int id); // Abstract method
	public void deleteById(int id); // Abstract method with void and void means it returns no value 
}
