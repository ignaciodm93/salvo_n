package com.examplecodeoftheweb.salvo_n.repository;


import com.examplecodeoftheweb.salvo_n.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long>{
}
