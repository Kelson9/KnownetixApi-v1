package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.config;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent.*;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.springframework.stereotype.Service;

@Service
public class JadeAgentManager{

    private AgentContainer mainContainer;
    private IntelligentTutoringAgent tutoringAgent;

    private RecommenderAgent recommenderAgent;

    private ResourceManagementAgent resourceManagementAgent;

    private UserAgent userAgent;

    private AssessmentAgent assessmentAgent;

    private UserRepository userRepository;

    public void startAgents() {
        try {
            // Get the JADE runtime instance
            Runtime runtime = Runtime.instance();

            // Create a main container
            Profile profile = new ProfileImpl(true);
            mainContainer = runtime.createMainContainer(profile);

            // Create the Intelligent Tutoring Agent
            tutoringAgent = new IntelligentTutoringAgent();
            AgentController tutoringAgentController = mainContainer.acceptNewAgent("tutoringAgent", tutoringAgent);
            tutoringAgentController.start();

            recommenderAgent = new RecommenderAgent();
            AgentController recommenderAgentController = mainContainer.acceptNewAgent("recommenderAgent", recommenderAgent);
            recommenderAgentController.start();

            resourceManagementAgent = new ResourceManagementAgent();
            AgentController resourceManagementAgentController = mainContainer.acceptNewAgent("resourceManagementAgent", resourceManagementAgent);
            resourceManagementAgentController.start();

            userAgent = new UserAgent();
            AgentController userAgentController = mainContainer.acceptNewAgent("userAgent", userAgent);
            userAgentController.start();

            // Create the Assessment Agent
            assessmentAgent = new AssessmentAgent(userRepository);
            AgentController assessmentAgentController = mainContainer.acceptNewAgent("assessmentAgent", assessmentAgent);
            assessmentAgentController.start();

            // Wait for a while to allow agents to start and communicate
            Thread.sleep(5000);

//             Send a message from the Intelligent Tutoring Agent to the Assessment Agent
            userAgent.sendMessageToAssessmentAgent("This is a message from the User Agent.");
            userAgent.sendMessageToRecommenderAgent("This is a message from the User Agent.");
            userAgent.sendMessageToTutoringAgentAgent("This is a message from the User Agent.");
            userAgent.sendMessageToResourceManagementAgent("This is a message from the User Agent.");
            assessmentAgent.sendMessageToRecommenderAgent("This is a message from the Assessment Agent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Intelligent Tutoring Agent
    public void createIntelligentTutoringAgent() {
        try {
            // Get the JADE runtime instance
            Runtime runtime = Runtime.instance();

            // Create a main container
            Profile profile = new ProfileImpl(true);
            mainContainer = runtime.createMainContainer(profile);

            // Create the Intelligent Tutoring Agent
            tutoringAgent = new IntelligentTutoringAgent();
            AgentController tutoringAgentController = mainContainer.acceptNewAgent("tutoringAgent", tutoringAgent);
            tutoringAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recommender Agent
    public void createRecommenderAgent() {
        try {
            // Get the JADE runtime instance
            Runtime runtime = Runtime.instance();

            // Create a main container
            Profile profile = new ProfileImpl(true);
            mainContainer = runtime.createMainContainer(profile);

            // Create the Recommender Agent
            recommenderAgent = new RecommenderAgent();
            AgentController recommenderAgentController = mainContainer.acceptNewAgent("recommenderAgent", recommenderAgent);
            recommenderAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Resource Management Agent
    public void createResourceManagementAgent() {
        try {
            // Get the JADE runtime instance
            Runtime runtime = Runtime.instance();

            // Create a main container
            Profile profile = new ProfileImpl(true);
            mainContainer = runtime.createMainContainer(profile);

            // Create the Resource Management Agent
            resourceManagementAgent = new ResourceManagementAgent();
            AgentController resourceManagementAgentController = mainContainer.acceptNewAgent("resourceManagementAgent", resourceManagementAgent);
            resourceManagementAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // User Agent
    public void createUserAgent() {
        try {
            // Get the JADE runtime instance
            Runtime runtime = Runtime.instance();

            // Create a main container
            Profile profile = new ProfileImpl(true);
            mainContainer = runtime.createMainContainer(profile);

            // Create the User Agent
            userAgent = new UserAgent();
            AgentController userAgentController = mainContainer.acceptNewAgent("userAgent", userAgent);
            userAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Assessment Agent
    public void createAssessmentAgent() {
        try {
            // Get the JADE runtime instance
            Runtime runtime = Runtime.instance();

            // Create a main container
            Profile profile = new ProfileImpl(true);
            mainContainer = runtime.createMainContainer(profile);

            // Create the Assessment Agent
            assessmentAgent = new AssessmentAgent(userRepository);
            AgentController assessmentAgentController = mainContainer.acceptNewAgent("assessmentAgent", assessmentAgent);
            assessmentAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
