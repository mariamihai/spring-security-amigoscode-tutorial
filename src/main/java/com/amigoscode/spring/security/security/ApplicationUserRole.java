package com.amigoscode.spring.security.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.amigoscode.spring.security.security.ApplicationUserAuthority.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet()),
    STUDENT(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserAuthority> permissions;

    ApplicationUserRole(Set<ApplicationUserAuthority> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserAuthority> getPermissions() {
        return permissions;
    }
}
