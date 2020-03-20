package com.amigoscode.spring.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.amigoscode.spring.security.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser ("anna",
                         passwordEncoder.encode("pass"),
                         STUDENT.getGrantedAuthorities()),
                new ApplicationUser ("steve",
                         passwordEncoder.encode("pass"),
                         ADMIN.getGrantedAuthorities()),
                new ApplicationUser ("tom",
                         passwordEncoder.encode("pass"),
                         ADMINTRAINEE.getGrantedAuthorities())
                );
    }
}
