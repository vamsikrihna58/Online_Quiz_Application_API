package com.Online_Quiz_Application_API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Online_Quiz_Application_API.Entity.OptionsEntity;
@Repository
public interface Oprtions extends JpaRepository<OptionsEntity, Integer> {

}
