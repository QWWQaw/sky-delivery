package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    Integer insert(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);

    void update(Employee emp);

    Page<Employee> page(EmployeePageQueryDTO empPageDto);

    @Delete("delete from employee where id = #{id}")
    void deleteById(Integer id);

    void startOrStop(Integer status, Integer id);

    void editPassword(PasswordEditDTO passwordEditDTO);

}
