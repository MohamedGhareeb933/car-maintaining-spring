package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    RestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        exposeId(config);
    }

    /**
     * get entities and push them into class list
     * map them into array and expose id for that array
     *
     * @param config
     */
    private void exposeId(RepositoryRestConfiguration config) {
        Set<EntityType<?> > entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClass = new ArrayList();

        for(EntityType entity : entities) {
            entityClass.add(entity.getJavaType());
        }

        Class [] domainType = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainType);
    }

}
