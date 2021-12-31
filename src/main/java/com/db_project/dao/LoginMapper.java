package com.db_project.dao;

import com.db_project.model.Employee;

public interface LoginMapper {
    Employee login(String uid);
}
