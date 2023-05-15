package com.example.alabhiber.db.repository;

import com.example.alabhiber.db.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    List<Person> findByLastName(String lastname);

    @Query(nativeQuery = true,
        value = "select * from person where id in (select person_id from document where doc_number like %?1%)"
    )
    List<Person> findAllByDocNumLike(String docNumPart);    // could be pageable

    @Query(nativeQuery = true,
            value = "select * from person where id in (select person_id from document where archived = false and doc_number like %?1%)"
    )
    List<Person> findAllByActiveDocNumLike(String docNumPart);

}
