package com.examplecodeoftheweb.salvo_n.repository;


import com.examplecodeoftheweb.salvo_n.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long>{



}
