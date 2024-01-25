package org.sid;

import java.util.List;

import org.sid.dao.IProduitRespository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MyCatalogue1Application implements CommandLineRunner{

	@Autowired
	private IProduitRespository iProduitRespository;
	
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogue1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		/*
		 * iProduitRespository.save(new Produit(null,"Iphone 12",3450,45));
		 * iProduitRespository.save(new Produit(null,"Iphone 11 max",2500,50));
		 * iProduitRespository.save(new Produit(null,"Iphone 13 max pro",5000,100));
		 * 
		 * iProduitRespository.findAll().forEach(p->{ System.out.println(p.toString());
		 * });
		 */
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
