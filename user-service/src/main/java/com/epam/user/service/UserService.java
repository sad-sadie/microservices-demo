package com.epam.user.service;

import com.epam.user.VO.Department;
import com.epam.user.VO.ResponseTemplateVO;
import com.epam.user.entity.UserEntity;
import com.epam.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public UserEntity saveUser(UserEntity userEntity) {
        log.info("Inside of saveUser of UserService");
        return userRepository.save(userEntity);
   }

    public ResponseTemplateVO getUserWithDeparment(long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        UserEntity userEntity = userRepository.findByUserId(userId);
        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + userEntity.getDepartmentId(),
                        Department.class);
        vo.setUserEntity(userEntity);
        vo.setDepartment(department);

        return vo;
    }
}
