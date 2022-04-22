package com.joaoborges.test;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projection", types = { UserGroup.class })
public interface UserGroupProjection {

    Long getId();

    String getName();

    UserProjection getOwner();
}
