package com.joaoborges.test;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projection", types = { User.class })
public interface UserProjection {

    Long getId();

    String getName();

}
