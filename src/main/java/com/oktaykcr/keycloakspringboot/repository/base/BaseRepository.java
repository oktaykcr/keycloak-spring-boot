package com.oktaykcr.keycloakspringboot.repository.base;

import com.oktaykcr.keycloakspringboot.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String> {
    Page<T> findAll(Pageable pageable);
}
