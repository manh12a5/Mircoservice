package com.example.department.controller;

import com.example.department.entity.Department;
import com.example.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Find by departmentId in DepartmentController");
        return departmentService.findDepartmentById(departmentId);
    }

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("Save department in DepartmentController");
        return departmentService.saveDepartment(department);
    }
}
