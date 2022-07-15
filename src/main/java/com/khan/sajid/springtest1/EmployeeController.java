package com.khan.sajid.springtest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository eRepo;

    @GetMapping({"/showEmployees", "/", "/list"})
    public ModelAndView showEmployees(){
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> list = eRepo.findAll();
        mav.addObject("employees", list);
        return mav;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        mav.addObject("employee", newEmployee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        eRepo.save(employee);
        return "redirect:/list";
    }

//    @GetMapping("/saveEmployee")
//    public String saveEmployeeGet(@ModelAttribute Employee employee){
//        eRepo.save(employee);
//        return "redirect:/list";
//    }


    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee = eRepo.findById(employeeId).get();
        mav.addObject("employee", employee);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam long employeeId){
        eRepo.deleteById(employeeId);
        return "redirect:/list";
    }

}
