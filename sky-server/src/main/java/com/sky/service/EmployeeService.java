package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employee
     */
    void add(Employee employee);


    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 根据id来更新员工
     * @param empDto
     */
    void update(EmployeeDTO empDto);

    Page<Employee> page(EmployeePageQueryDTO empPageDto);

    void delete(Integer id);

    void startOrStop(Integer status, Integer id);
}
