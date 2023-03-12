package com.ppmtool.repositories;

import com.ppmtool.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectRepository implements CrudRepository<Project, Long> {

    @Override
    public <S extends Project> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Project> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Project> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Project> findAll() {
        return null;
    }

    @Override
    public Iterable<Project> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Project entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Project> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
