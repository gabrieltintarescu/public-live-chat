package com.gabrieltintarescu.ChatboxServer;

import com.gabrieltintarescu.ChatboxServer.model.Role;
import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ChatboxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatboxServerApplication.class, args);
	}


}