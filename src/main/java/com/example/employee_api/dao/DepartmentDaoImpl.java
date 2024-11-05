package com.example.employee_api.dao;

import com.example.employee_api.model.Department;
import com.example.employee_api.service.DepartmentService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    private final NamedParameterJdbcTemplate template;

    public DepartmentDaoImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getAllDepartments(boolean flag) {
        String sql = "select * from department where trash = " + Boolean.toString(flag);

        return template.query(sql, (rs, rowNum) -> {
            Department department = new Department();
            department.setDname(rs.getString("dname"));
            department.setDnumber(rs.getInt("dnumber"));
            department.setMgr_ssn(rs.getString("mgr_ssn"));
            department.setMgr_start_date(rs.getDate("mgr_start_date"));
            return department;
        });
    }

    @Override
    public Department getDepartmentByDnumber(int dnumber) {
        String sql = "select * from department where dnumber = :dnumber and trash = false";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dnumber", dnumber);

        return template.queryForObject(sql, params, new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public List<Department> getDepartmentByAttr(List<String> searchAttr, List<Object> departmentValue) {
        StringBuilder queryBuilder = new StringBuilder("select * from department where trash = false and ");

        MapSqlParameterSource params = new MapSqlParameterSource();

        for (int i = 0; i < searchAttr.size(); i++) {
            queryBuilder.append(searchAttr.get(i)).append(" = :param").append(i);
            params.addValue("param" + i, departmentValue.get(i));
            if (i < searchAttr.size() - 1) {
                queryBuilder.append(" AND ");
            }
        }

        String sql = queryBuilder.toString();

        return template.query(sql, params, new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public boolean deleteDepartmentByDnumber(int dnumber, boolean flag) {
        String sql = !flag ? "UPDATE DEPARTMENT SET trash = true WHERE dnumber = :dnumber" : "DELETE FROM DEPARTMENT WHERE dnumber = :dnumber AND trash = true";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dnumber", dnumber);
        try {
            int result = template.update(sql, params);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Department updateDepartmentByDnumber(int dnumber, Map<String, Object> updateValue) {
        StringBuilder sql = new StringBuilder("UPDATE DEPARTMENT SET ");

        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        updateValue.put("modified", currentTimestamp);

        MapSqlParameterSource params = new MapSqlParameterSource();

        for (Map.Entry<String, Object> entry: updateValue.entrySet()) {
            sql.append(entry.getKey()).append(" = :").append(entry.getKey()).append(", ");
            params.addValue(entry.getKey(), entry.getValue());
        }

        sql.setLength(sql.length() -2);
        sql.append(" WHERE Dnumber = :dnumber AND trash = false");
        params.addValue("dnumber", dnumber);

        try {
            int result = template.update(sql.toString(), params);
            if (result > 0) {
                return getDepartmentByDnumber(dnumber);
            }
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Department addDepartment(List<Object> addingValue) {
        if (addingValue.size() < 10) {
            throw new IllegalArgumentException("Invalid changeValue list");
        }

        String sql = "INSERT INTO DEPARTMENT (Dname, Dnumber, Mgr_ssn, Mgr_start_date, created, modified, trash) VALUES (:Dname, :Dnumber, :Mgr_ssn, :Mgr_start_date, :created, :modified, false)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("Dname", addingValue.get(0));
        params.addValue("Dnumber", addingValue.get(1));
        params.addValue("Mgr_ssn", addingValue.get(2));
        params.addValue("Mgr_start_date", addingValue.get(3));
        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        params.addValue("created", currentTimestamp);
        params.addValue("modified", currentTimestamp);

        try {
            int result = template.update(sql, params);
            if (result > 0) {
                return getDepartmentByDnumber(Integer.parseInt(addingValue.get(3).toString()));
            }
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double getDepartmentInfo(int dnumber, DepartmentService.OperationType operationType) {
        String sql = "SELECT " + operationType + "(Salary) FROM DEPARTMENT WHERE Dnumber = :dnumber AND trash = false";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dnumber", dnumber);

        try {
            return template.queryForObject(sql, params, Double.class);
        } catch (Exception e) {
            return null;
        }
    }
}
