package io.simpolor.web.controller;

import io.simpolor.web.model.StudentRequest;
import io.simpolor.web.repository.entity.Student;
import io.simpolor.web.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@RequestMapping(value="/list")
	public ModelAndView list(ModelAndView mav) {

		log.info("/student/list");
		log.debug("/student/list");

		long totalCount = studentService.getTotalCount();
		List<Student> students = studentService.getAll();

		mav.addObject("totalCount", totalCount);
		mav.addObject("list", students);
		mav.setViewName("student_list");

		return mav;
	}

	@RequestMapping(value="/view/{seq}", method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView mav, @PathVariable Long seq) {

		Student student = studentService.get(seq);

		mav.addObject("student", student);
		mav.setViewName("student_view");

		return mav;
	}

	@GetMapping(value="/register/form")
	public ModelAndView registerForm(ModelAndView mav) {

		mav.setViewName("student_register");

		return mav;
	}

	@PostMapping(value="/register")
	public ModelAndView register(ModelAndView mav, StudentRequest request) {

		Student student = studentService.create(request.toStudent());

		mav.addObject("student", student);
		mav.setViewName("redirect:/student/view/"+student.getSeq());

		return mav;
	}

	@GetMapping(value="/modify/form/{seq}")
	public ModelAndView modifyForm(ModelAndView mav, @PathVariable Long seq) {

		Student student = studentService.get(seq);

		mav.addObject("student", student);
		mav.setViewName("student_modify");

		return mav;
	}

	@PostMapping(value="/modify/{seq}")
	public ModelAndView modify(ModelAndView mav, @PathVariable Long seq, StudentRequest request) {

		request.setSeq(seq);
		Student result = studentService.update(request.toStudent());

		mav.addObject("student", result);
		mav.setViewName("redirect:/student/view/"+result.getSeq());

		return mav;
	}

	@PostMapping(value="/delete/{seq}")
	public ModelAndView delete(ModelAndView mav, @PathVariable Long seq) {

		studentService.delete(seq);

		mav.setViewName("redirect:/student/list");

		return mav;
	}

}
