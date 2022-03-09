package com.kpakula.trainingrepository.repository;

import com.kpakula.trainingrepository.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
