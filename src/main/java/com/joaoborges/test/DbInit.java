package com.joaoborges.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void run(final String... args) {
        User user = transactionTemplate.execute(tc -> userRepository.save(User.builder().id(1L).name("user").build()));
        transactionTemplate.execute(tc -> userGroupRepository.save(UserGroup.builder().id(2L).name("Group").owner(user).build()));
    }
}
