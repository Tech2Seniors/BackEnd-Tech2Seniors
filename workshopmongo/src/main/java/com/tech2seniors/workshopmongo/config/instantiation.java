package com.tech2seniors.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.tech2seniors.workshopmongo.domain.Sujestao;
import com.tech2seniors.workshopmongo.domain.User;
import com.tech2seniors.workshopmongo.dto.AuthorDTO;
import com.tech2seniors.workshopmongo.repository.SujestaoRepository;
import com.tech2seniors.workshopmongo.repository.UserRepository;

@Configuration
public class instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SujestaoRepository sujestaoRepository;

	
	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		sujestaoRepository.deleteAll();
		
		User bob = new User(null, "bob@gmail.com");
		User alex = new User(null, "alex@gmail.com");
		User maria = new User(null,  "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Sujestao sujestao1 = new Sujestao(null, sdf.parse("21/03/2014"), "Eu quero que tenha no app blablabla", new AuthorDTO(bob));
		
		
		
		sujestaoRepository.saveAll(Arrays.asList(sujestao1));
		
		bob.getSujestao().addAll(Arrays.asList(sujestao1));		
		userRepository.save(bob);
	}

}
