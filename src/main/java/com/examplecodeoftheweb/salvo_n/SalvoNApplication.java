package com.examplecodeoftheweb.salvo_n;


import com.examplecodeoftheweb.salvo_n.model.*;
import com.examplecodeoftheweb.salvo_n.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class SalvoNApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoNApplication.class, args);





	}



	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository Grepository, GamePlayerRepository GPrepository, ShipRepository shipRepository, SalvoRepository salvoRepository) {
		return (args) -> {


			//Players
			Player p1 = new Player("Jack Bauer ", "j.bauer@ctu.gov ");
			Player p2 = new Player("Chloe O'Brian ", "c.obrian@ctu.gov");
			Player p3 = new Player("Kim bauer ", "kim_bauer@gmail.com");
			Player p4 = new Player("David Palmer ", "t.almeida@ctu.gov");
			Player p5 = new Player("Franco", "franco.rap@hotmail.com");
			Player p6 = new Player("Lautaro", "launicolopez@hotmail.com");
			repository.save(p1);
			repository.save(p2);
			repository.save(p3);
			repository.save(p4);
			repository.save(p5);
			repository.save(p6);

			//Games
			Game g1 = new Game();
			Game g2 = new Game(LocalDateTime.now().plusHours(1));
			Game g3 = new Game(LocalDateTime.now().plusHours(2));
			Grepository.save(g1);
			Grepository.save(g2);
			Grepository.save(g3);

			//GamePlayer
			GamePlayer gamePlayer1 = new GamePlayer(g1, p1);
			GamePlayer gamePlayer2 = new GamePlayer(g1, p2);
			GamePlayer gamePlayer3 = new GamePlayer(g2, p3);
			GamePlayer gamePlayer4 = new GamePlayer(g2, p4);
			GamePlayer gamePlayer5 = new GamePlayer(g3, p5);
			GamePlayer gamePlayer6 = new GamePlayer(g3, p6);
			GPrepository.save(gamePlayer1);
			GPrepository.save(gamePlayer2);
			GPrepository.save(gamePlayer3);
			GPrepository.save(gamePlayer4);
			GPrepository.save(gamePlayer5);
			GPrepository.save(gamePlayer6);


			Ship ship1 = new Ship("Carrier", List.of("A1", "A2", "A3", "A4", "A5"), gamePlayer1);
			Ship ship2 = new Ship("BattleShip", List.of("E1", "E2", "E3", "E4"), gamePlayer1);
			Ship ship3 = new Ship("Submarine", List.of("C1", "C2", "C3"), gamePlayer1);
			Ship ship4 = new Ship("Destroyer", List.of("A7", "B7", "C7"), gamePlayer1);
			Ship ship5 = new Ship("Patrol Boat", List.of("F1", "F2"), gamePlayer1);

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);

			Ship ship6 = new Ship("Carrier", List.of("A1", "B1", "C1", "D1", "E1"), gamePlayer2);
			Ship ship7 = new Ship("BattleShip", List.of("B6", "B3", "B4", "B5"), gamePlayer2);
			Ship ship8 = new Ship("Submarine", List.of("H3", "H4", "H5"), gamePlayer2);
			Ship ship9 = new Ship("Destroyer", List.of("D7", "E7", "F7"), gamePlayer2);
			Ship ship10 = new Ship("Patrol Boat", List.of("J9", "J10"), gamePlayer2);


			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);


			//Salvoes
			//prueba, creo que no se deberia hacer asi
			Salvo salvo_1 = new Salvo(1, List.of("A3","B3","C3"), gamePlayer1);
			Salvo salvo_2 = new Salvo(1, List.of("F1","D7","B9"), gamePlayer2);
			Salvo salvo_3 = new Salvo(1, List.of("B5", "B6", "B7"), gamePlayer3);
			Salvo salvo_4 = new Salvo(1, List.of("A5", "B9", "E3"), gamePlayer4);
			salvoRepository.save(salvo_1);
			salvoRepository.save(salvo_2);
			salvoRepository.save(salvo_3);
			salvoRepository.save(salvo_4);

		};
	}




}
