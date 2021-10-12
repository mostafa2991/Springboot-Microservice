package com.dailycodebuffer.user.service;

import com.dailycodebuffer.user.VO.Department;
import com.dailycodebuffer.user.VO.ResponseTemplateVO;
import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//@FeignClient(name="DEPARTMENT-SERVICE" , url = "localhost:9191")
@FeignClient(name="DEPARTMENT-SERVICE")
public interface DocumentProxy {

   

    @GetMapping("/departments/{id}")
    public Department getUserWithDepartment(@PathVariable("id") Long userId);
    
    
    
   
}
