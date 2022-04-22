package com.joaoborges.test;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource
public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup, Long> {
}
