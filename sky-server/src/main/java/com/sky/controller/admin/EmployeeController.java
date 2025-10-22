package com.sky.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api("员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("员工登录接口")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出接口")
    public Result<String> logout()
    {
        log.info("员工退出");
        return Result.success();
    }

    @PostMapping()
    @ApiOperation("新增员工接口")
    public Result add(@RequestBody EmployeeDTO empDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(empDto, employee);
        employeeService.add(employee);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工接口")
    public Result<Employee> getById(@PathVariable Integer id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @PutMapping
    @ApiOperation("编辑员工接口")
    public Result update(@RequestBody EmployeeDTO empDto) {
        employeeService.update(empDto);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("员工分页查询接口")
    public Result page(EmployeePageQueryDTO empPageDto) {
        Page<Employee> page = employeeService.page(empPageDto);
        Long total = page.getTotal();
        List<Employee> records = page.getResult();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("records", records);
        return Result.success(data);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("员工删除接口")
    public Result delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return Result.success();
    }

}
