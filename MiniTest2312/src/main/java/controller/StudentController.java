package controller;

import model.Clazz;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IClazzService;
import service.IStudentService;

import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClazzService clazzService;

    @ModelAttribute("classList")
    public Iterable <Clazz> classList() {
        return clazzService.findAll();
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/find")
    public ModelAndView findByName(@RequestParam("name") Optional<String> name, Pageable pageable) {
        Page<Student> studentList;
        ModelAndView modelAndView = new ModelAndView("student/list");
        ;
        if (!name.isPresent()) {
            studentList = studentService.findAll(pageable);
        } else {
            studentList = studentService.findAllByNameContaining(pageable, name.get());
            modelAndView.addObject("name", name.get());
        }
        modelAndView.addObject("studentList", studentList);
        modelAndView.addObject("listName", "Student list sorted by Score");
        return modelAndView;
    }

    @GetMapping("/list-greater-8")
    public ModelAndView findList8(Pageable pageable) {
        Page<Student> studentList;
        ModelAndView modelAndView = new ModelAndView("student/list");
        studentList = studentService.findAllByScoreGreaterThan8(pageable, 8.0);
        modelAndView.addObject("studentList", studentList);
        modelAndView.addObject("listName", "Students with score greater than 8");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("student", studentService.findById(id).get());
        return "student/edit";
    }

    @PostMapping("/edit")
    public String edit(Student student) {
        studentService.save(student);
        return "redirect:/find";

    }


}
