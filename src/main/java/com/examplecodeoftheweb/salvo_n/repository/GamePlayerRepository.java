package com.examplecodeoftheweb.salvo_n.repository;


import com.examplecodeoftheweb.salvo_n.model.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {


}
