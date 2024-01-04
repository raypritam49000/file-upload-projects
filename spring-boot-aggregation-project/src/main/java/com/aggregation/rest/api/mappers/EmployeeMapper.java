package com.aggregation.rest.api.mappers;

import com.aggregation.rest.api.dto.EmployeeDTO;
import com.aggregation.rest.api.mappers.base.BaseMapper;
import com.aggregation.rest.api.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeeDTO, Employee> {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
}
