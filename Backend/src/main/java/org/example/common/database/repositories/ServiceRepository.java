package org.example.common.database.repositories;

import org.example.common.database.entities.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public interface ServiceRepository<E extends BaseEntity>  extends JpaRepository<E, String> {
    Optional<E> findByOidAndIsDeleted(String oid, String isDeleted);
    List<E> findAllByIsDeleted(String isDeleted);
    List<E> findAllByOidInAndIsDeleted(Set<String> oids, String isDeleted);
}
