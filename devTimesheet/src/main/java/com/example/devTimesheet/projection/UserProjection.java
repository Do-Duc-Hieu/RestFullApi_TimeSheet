package com.example.devTimesheet.projection;

public interface UserProjection {
    String getId();

    String getUsername();

    String getSex();

    String getAddress();

    BranchProjection getBranch();

    RoleProjection getRole();
}
