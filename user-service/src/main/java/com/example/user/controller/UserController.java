package com.example.user.controller;

import com.example.user.VO.DepartmentVO;
import com.example.user.VO.ResponseTemplateVO;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_DEPARTMENT = "http://DEPARTMENT-SERVICE/departments/";

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Save user in UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getResponseTemplateByUserId(@PathVariable("id") Long userId) {
        log.info("Get ResponseTemplate by userId in UserController");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();

        User user = userService.findUserByUserId(userId);
        DepartmentVO department = restTemplate.getForObject(URL_DEPARTMENT + user.getDepartmentId(),
                DepartmentVO.class);

        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);

        return responseTemplateVO;
    }
}
