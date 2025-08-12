package com.StudentManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.StudentManagementSystem.entity.Student;
import com.StudentManagementSystem.service.StudentService;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private StudentService service;
	
	@GetMapping("/home")
	public String home() {
		return "home"; // home in this case is a file i.e. home.html and it's not a simple message. It's a View Page (html file) 
	}
	
	@GetMapping("/students")
	public String getAllStudents(Model model) {
		model.addAttribute("students", service.getAllStudents());
		return "students"; // Here students is View page i.e. students.html file
	}
	
	@GetMapping("/students/new")
	public String newStudentForm(Model model) {
		Student student = new Student(); // Created a new Student Object and assigns it to the student variable
		model.addAttribute("newstudent", student);
		return "create-student"; // View Page with create-student.html file
	} 
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("newstudent") Student student) {
		service.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String updateStudent(@PathVariable int id, Model model) {
		model.addAttribute("upstudent", service.getById(id));  
		return "update-student"; // View Page i.e. update-student.html file
	} 
	
	@PostMapping("/students/edit/{id}")
	public String updatedDetails(@PathVariable int id, @ModelAttribute("upstudent") Student student) {
		Student existingStudent = service.getById(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		service.saveStudent(existingStudent);
		return "redirect:/students";	
	} 
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/students";
	}
}  
