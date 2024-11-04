package com.example.employee_api.dao;

import com.example.employee_api.model.Department;
import com.example.employee_api.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    private final NamedParameterJdbcTemplate template;

    public DepartmentDaoImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    //부서 추가
    @Override
    public int createDepartment(String dname, int dnumber, String mgr_ssn, Date mgr_start_date) {
        String sql = "insert into department(dname, dnumber, mgr_ssn, mgr_start_date) values (:dname, :dnumber, :mgr_ssn, :mgr_start_date)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dname", dname);
        params.addValue("dnumber", dnumber);
        params.addValue("mgr_ssn", mgr_ssn);
        params.addValue("mgr_start_date", mgr_start_date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        return template.update(sql, params, keyHolder);
    }

    //부서 정보 업데이트
    @Override
    public int updateDepartment(String dname, int dnumber, String mgr_ssn, Date mgr_start_date) {
        String sql = "update department set dname = :dname, mgr_ssn = :mgr_ssn, mgr_start_date = :mgr_start_date where dnumber = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dname", dname);
        params.addValue("mgr_ssn", mgr_ssn);
        params.addValue("mgr_start_date", mgr_start_date);
        params.addValue("dnumber", dnumber);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        return template.update(sql, params, keyHolder);
    }

    // 생성된 부서에 Mgr정보 입력
    @Override
    public int insertManager(int dnumber, String mgr_ssn, Date mgr_start_date) {
        String sql = "update department set mgr_ssn = :mgr_ssn, mgr_start_date = :mgr_start_date where dnumber = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("mgr_ssn", mgr_ssn);
        params.addValue("mgr_start_date", mgr_start_date);
        params.addValue("dnumber", dnumber);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        return template.update(sql, params, keyHolder);
    }

    // 부서 번호에 의한 부서 정보 조회
    @Override
    public Department getDepartmentByDnumber(int dnumber) {
        String sql = "select * from department where dnumber = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dnumber", dnumber);

        return template.queryForObject(sql, params, new BeanPropertyRowMapper<>(Department.class));
    }

    //전체 부서정보 조회
    @Override
    public List<Department> getAllDepartments() {
        String sql = "select * from department";

        return template.query(sql, (rs, rowNum) -> {
            Department department = new Department();
            department.setDname(rs.getString("dname"));
            department.setDnumber(rs.getInt("dnumber"));
            department.setMgr_ssn(rs.getString("mgr_ssn"));
            department.setMgr_start_date(rs.getDate("mgr_start_date"));
            return department;
        });
    }
    
    // 부서 번호에 의한 부서 내 인원 조회
    @Override
    public List<Employee> getEmployeesByDnumber(int dnumber) {
        String sql = "SELECT fname, minit, lname, ssn, bdate, address, sex, salary, super_ssn, dno, created, modified " +
                "FROM employee " +
                "WHERE dno = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dnumber", dnumber);

        return template.query(sql, params, (rs, rowNum) ->
                new Employee(
                        rs.getString("fname"),
                        rs.getString("minit"),
                        rs.getString("lname"),
                        rs.getString("ssn"),
                        rs.getDate("bdate"),
                        rs.getString("address"),
                        rs.getString("sex"),
                        rs.getDouble("salary"),
                        rs.getString("super_ssn"),
                        rs.getInt("dno"),
                        rs.getTimestamp("created"),
                        rs.getTimestamp("modified")
                ));
    }

    // 부서 별 평균 급여 정보 조회
    @Override
    public Double getAverageSalaryByDepartment(int dnumber) {
        String sql = "select avg(salary) from employee where dno = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dnumber", dnumber);

        return template.queryForObject(sql, params, Double.class);
    }

    // 부서 별 최고 연봉 인원 조회
    @Override
    public Double getMaxSalaryByDepartment(int dnumber) {
        String sql = "select max(salary) from employee where dno = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dnumber", dnumber);

        return template.queryForObject(sql, params, Double.class);
    }

    // 부서 내 최저 연봉 인원 조회
    @Override
    public Double getMinSalaryByDepartment(int dnumber) {
        String sql = "select min(salary) from employee where dno = :dnumber";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("dnumber", dnumber);

        return template.queryForObject(sql, params, Double.class);
    }
}
