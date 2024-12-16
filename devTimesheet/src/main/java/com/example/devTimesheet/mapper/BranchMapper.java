package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.BranchRequest;
import com.example.devTimesheet.dto.respon.BranchRespon;
import com.example.devTimesheet.entity.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    Branch toBranch(BranchRequest request);

    void updateBranch(@MappingTarget Branch branch, BranchRequest request);

    BranchRespon toBranchRespon(Branch branch);
}
