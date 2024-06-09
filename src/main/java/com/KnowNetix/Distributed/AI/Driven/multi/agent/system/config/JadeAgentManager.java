package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.config;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent.AssessmentAgent;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent.IntelligentTutoringAgent;
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
    private AssessmentAgent assessmentAgent;

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

            // Create the Assessment Agent
            assessmentAgent = new AssessmentAgent();
            AgentController assessmentAgentController = mainContainer.acceptNewAgent("assessmentAgent", assessmentAgent);
            assessmentAgentController.start();

            // Wait for a while to allow agents to start and communicate
            Thread.sleep(5000);

            // Send a message from the Intelligent Tutoring Agent to the Assessment Agent
            tutoringAgent.sendMessageToAssessmentAgent("This is a message from the Intelligent Tutoring Agent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IntelligentTutoringAgent getTutoringAgent() {
        return tutoringAgent;
    }

    public AssessmentAgent getAssessmentAgent() {
        return assessmentAgent;
    }
}
