package com.springboot.dbtool.controller;

import com.springboot.dbtool.dao.hibernate.UserRepo;
import com.springboot.dbtool.entity.hibernate.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/jpaHibernate")
@Api(tags = {"jpa、hibernate 查询接口"})
public class JpaHibernateController {

    @Autowired
    UserRepo userRepo;

    @ApiOperation(value = "查询一个user",httpMethod = "GET")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "id",value = "user id",dataTypeClass = Long.class)
    }))
    @RequestMapping(value = "/getOneUser/{id}",method = RequestMethod.GET)
    public String getOneUser(@PathVariable Long id){
        User user = userRepo.getOne(id);
        return user.toString();
    }

    @ApiOperation(value = "根据userName查询一个user",httpMethod = "GET")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "userName",value = "user 名字",dataTypeClass = String.class)
    }))
    @RequestMapping(value = "/getByUserName/{userName}",method = RequestMethod.GET)
    public String getByUserName(@PathVariable String userName){
        Optional user = userRepo.findByUserName(userName);
        return user.isPresent() ? user.get().toString() : null;
    }

    @ApiOperation(value = "根据id和userName查询一个user",httpMethod = "GET")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "id",value = "user id",dataTypeClass = Long.class),
            @DynamicParameter(name = "userName",value = "user 名字",dataTypeClass = String.class)
    }))
    @RequestMapping(value = "/getByUserName/{id}/{userName}",method = RequestMethod.GET)
    public String getByIdAndUserName(@PathVariable Long id,@PathVariable String userName){
        Optional user = userRepo.findByIdAndUserName(id,userName);
        return user.isPresent() ? user.get().toString() : null;
    }
}
