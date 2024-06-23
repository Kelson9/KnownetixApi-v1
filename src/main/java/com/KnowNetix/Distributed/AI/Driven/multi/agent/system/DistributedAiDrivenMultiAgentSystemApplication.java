package com.KnowNetix.Distributed.AI.Driven.multi.agent.system;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.config.JadeAgentManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DistributedAiDrivenMultiAgentSystemApplication {
	public static void main(String[] args) {
		// Start the JADE platform with the GUI option
//		 startJadePlatformWithGui();

		// Start the Spring Boot application
		ConfigurableApplicationContext context = SpringApplication.run(DistributedAiDrivenMultiAgentSystemApplication.class, args);

		// Initialize the JADE agents
		JadeAgentManager jadeAgentManager = context.getBean(JadeAgentManager.class);
		jadeAgentManager.startAgents();
	}

//	private static void startJadePlatformWithGui() {
//		try {
//			// Get the JADE runtime instance
//			jade.core.Runtime runtime = jade.core.Runtime.instance();
//
//			// Create a main container with the GUI option
//			jade.core.Profile profile = new jade.core.ProfileImpl(true);
//			jade.wrapper.AgentContainer mainContainer = runtime.createMainContainer(profile);
//
//			// Wait for a while to allow the JADE platform to start
//			Thread.sleep(5000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
