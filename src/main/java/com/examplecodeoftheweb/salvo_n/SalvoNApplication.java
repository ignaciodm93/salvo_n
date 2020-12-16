package com.examplecodeoftheweb.salvo_n;


import com.examplecodeoftheweb.salvo_n.model.*;
import com.examplecodeoftheweb.salvo_n.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class SalvoNApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SalvoNApplication.class, args);

	}

	//Para encriptar las contraseñas antes de guardarlas
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository Grepository, GamePlayerRepository GPrepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
		return (args) -> {


			/*//Players
			Player p1 = new Player("Jack Bauer", "j.bauer@ctu.gov", passwordEncoder().encode("123"));
			Player p2 = new Player("Chloe O'Brian", "c.obrian@ctu.gov", passwordEncoder().encode("123"));
			Player p3 = new Player("Kim bauer", "kim_bauer@gmail.com", passwordEncoder().encode("123"));
			Player p4 = new Player("David Palmer", "t.almeida@ctu.gov", passwordEncoder().encode("123"));
			Player p5 = new Player("Franco", "franco.rap@hotmail.com", passwordEncoder().encode("123"));
			Player p6 = new Player("Lautaro", "launicolopez@hotmail.com", passwordEncoder().encode("123"));

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
			Game g4 = new Game(LocalDateTime.now().plusHours(5));
			Game g5 = new Game(LocalDateTime.now().plusHours(6));

			Grepository.save(g1);
			Grepository.save(g2);
			Grepository.save(g3);
			Grepository.save(g4);
			Grepository.save(g5);

			//GamePlayer
			GamePlayer gamePlayer1 = new GamePlayer(g1, p1);
			GamePlayer gamePlayer2 = new GamePlayer(g1, p2);

			GamePlayer gamePlayer3 = new GamePlayer(g2, p3);
			GamePlayer gamePlayer4 = new GamePlayer(g2, p4);

			GamePlayer gamePlayer5 = new GamePlayer(g3, p5);
			GamePlayer gamePlayer6 = new GamePlayer(g3, p6);

			GamePlayer gamePlayer7 = new GamePlayer(g4, p1);
			GamePlayer gamePlayer8 = new GamePlayer(g4, p6);

			GamePlayer gamePlayer9 = new GamePlayer(g5, p1);
			GamePlayer gamePlayer10 = new GamePlayer(g5, p6);

			GPrepository.save(gamePlayer1);
			GPrepository.save(gamePlayer2);
			GPrepository.save(gamePlayer3);
			GPrepository.save(gamePlayer4);
			GPrepository.save(gamePlayer5);
			GPrepository.save(gamePlayer6);
			GPrepository.save(gamePlayer7);
			GPrepository.save(gamePlayer8);
			GPrepository.save(gamePlayer9);
			GPrepository.save(gamePlayer10);

			*//*Score score_4 = new Score(1.0, LocalDateTime.now(), p1, g1);
			Score score_5 = new Score(0.0, LocalDateTime.now(), p2, g1);

			Score score_6 = new Score(0.5, Date.from(Instant.now()), p3, g2);
			Score score_7 = new Score(0.5, Date.from(Instant.now()), p4, g2);

			Score score_8 = new Score(1.0, Date.from(Instant.now()), p5, g3);
			Score score_9 = new Score(0.0, Date.from(Instant.now()), p6, g3);

			Score score_10 = new Score(1.0, Date.from(Instant.now()), p1, g4);
			Score score_11 = new Score(0.0, Date.from(Instant.now()), p6, g4);

			Score score_12 = new Score(1.0, Date.from(Instant.now()), p1, g5);
			Score score_13 = new Score(0.0, Date.from(Instant.now()), p6, g5);*//*

			*//*scoreRepository.save(score_4);
			scoreRepository.save(score_5);
			scoreRepository.save(score_6);
			scoreRepository.save(score_7);
			scoreRepository.save(score_8);
			scoreRepository.save(score_9);
			scoreRepository.save(score_10);
			scoreRepository.save(score_11);
			scoreRepository.save(score_12);
			scoreRepository.save(score_13);*//*

			Ship ship1 = new Ship("carrier", List.of("A1", "A2", "A3", "A4", "A5"), gamePlayer1);
			Ship ship2 = new Ship("battleship", List.of("E1", "E2", "E3", "E4"), gamePlayer1);
			Ship ship3 = new Ship("submarine", List.of("C1", "C2", "C3"), gamePlayer1);
			Ship ship4 = new Ship("destroyer", List.of("A7", "B7", "C7"), gamePlayer1);
			Ship ship5 = new Ship("patrolboat", List.of("F1", "F2"), gamePlayer1);

			Ship ship6 = new Ship("carrier", List.of("A1", "B1", "C1", "D1", "E1"), gamePlayer2);
			Ship ship7 = new Ship("battleship", List.of("B3", "B4", "B5", "B6"), gamePlayer2);
			Ship ship8 = new Ship("submarine", List.of("H3", "H4", "H5"), gamePlayer2);
			Ship ship9 = new Ship("destroyer", List.of("D7", "E7", "F7"), gamePlayer2);
			Ship ship10 = new Ship("patrolboat", List.of("J9", "J10"), gamePlayer2);

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);

			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);


			//Salvoes
			//prueba, creo que no se deberia hacer asi
			Salvo salvo_1 = new Salvo(1, List.of("A1", "B1", "C1", "D1", "E1"), gamePlayer1);
			Salvo salvo_2 = new Salvo(2, List.of("B3", "B4", "B5", "B6", "G10"), gamePlayer1);
			Salvo salvo_3 = new Salvo(3, List.of("H3", "H4", "H5", "B8", "B9"), gamePlayer1);
			Salvo salvo_4 = new Salvo(4, List.of("D7", "E7", "F7", "J9", "C5"), gamePlayer1);

			salvoRepository.save(salvo_1);
			salvoRepository.save(salvo_2);
			salvoRepository.save(salvo_3);
			salvoRepository.save(salvo_4);

			repository.save(p1);*/

		};
	}

}

	//Con esto setteamos a la base de datos como fuente para la autenticacion
	@Configuration
	class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		PlayerRepository playerRepository;

		//Toma un parametro pasado por el front y lo chequea con la base de datos usando
			//el metodo declarado en el repositorio de player:
		//Si lo encuentra crea y retorna un objeto org.springframework.security.core.userdetails.User,
			//suministrando asi los nombres, contraseñas y roles que dicho usuario posee.
		//El encargado de chequear la contraseña es Spring, que lo ahce de forma interna.
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(inputName-> {
				Player player = playerRepository.findByEmail(inputName);
				if (player != null) {
					return new User(player.getEmail(), player.getPassword(),
							AuthorityUtils.createAuthorityList("USER"));
				} else {
					throw new UsernameNotFoundException("Unknown user: " + inputName);
				}
			});
		}



	@Configuration
	@EnableWebSecurity
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/web/**").permitAll()
					.antMatchers("/api/game_view/*").hasAuthority("USER")
					.antMatchers("/h2-console/**").permitAll()
					.antMatchers("/api/games").permitAll();


			http.formLogin()
					.usernameParameter("name")
					.passwordParameter("pwd")
					.loginPage("/api/login");

			http.logout().logoutUrl("/api/logout");

			// turn off checking for CSRF tokens
			http.csrf().disable();
			http.headers().frameOptions().disable();


			// if user is not authenticated, just send an authentication failure response
			http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

			// if login is successful, just clear the flags asking for authentication
			http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

			// if login fails, just send an authentication failure response
			http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

			// if logout is successful, just send a success response
			http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
		}

		//defined to remove the flag Spring sets when an unauthenticated user attempts to access some resource.
		private void clearAuthenticationAttributes(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			}
		}
	}



}


