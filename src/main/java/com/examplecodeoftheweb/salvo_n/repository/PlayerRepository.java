package com.examplecodeoftheweb.salvo_n.repository;


import com.examplecodeoftheweb.salvo_n.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long>{


//    Player findByEmail(@Param("name") String name);
    Player findByEmail(@Param("email") String email);


    //Prueba para ver si esta loggeado, devuelve true
    //Boolean isLogged(@Param("email") String email);

    //Metodo para guardar,no se si es necesario
    //Void save(Player player);

}
