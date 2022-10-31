package fr.family.bernadet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.family.bernadet.entity.PresentEntity;

@Repository
public interface PresentRepository extends CrudRepository<PresentEntity, Long> {

    @Query("select count(e)>0 from PresentEntity e where e.fromUser = :id")
    boolean existsFrom(String id);
}
