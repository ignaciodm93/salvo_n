package com.examplecodeoftheweb.salvo_n.repository;

import com.examplecodeoftheweb.salvo_n.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShipRepository extends JpaRepository<Ship, Long> {

//List<Ship> findByGamePlayerId(long game);


}
