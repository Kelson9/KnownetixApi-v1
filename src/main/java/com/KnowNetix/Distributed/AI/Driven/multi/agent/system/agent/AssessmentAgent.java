package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.FuzzyNumber;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import jade.core.AID;
import jade.core.Agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AssessmentAgent extends Agent {
    private final UserRepository userRepository;

    protected void setup() {
        System.out.println("Assessment Agent is ready.");

        // Add behaviors to the agent
        addBehaviour(new MessageReceiver());

//        User user = retrieveUserFromDatabase();
//        updateCognitiveState(user);
    }

//    private User retrieveUserFromDatabase() {
////         Database query to retrieve the user
//        Optional<User> user = userRepository.findById(1l);
//        if(!user.isPresent()){
//            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
//        }
//        return user.get();
//    }

    private void updateCognitiveState(User user) {
        // Evaluate the user's cognitive state and update the fuzzy number
        FuzzyNumber cognitiveState = evaluateCognitiveState(user);
        user.setCognitiveLevel(cognitiveState.getMembershipDegree(0.5));
        userRepository.save(user);
    }

    private FuzzyNumber evaluateCognitiveState(User user) {
        // Implement cognitive state evaluation logic based on user data
        return new FuzzyNumber(/* membership function data */);
    }

    protected void takeDown() {
        System.out.println("Assessment Agent is shutting down.");
    }

    private class MessageReceiver extends CyclicBehaviour {
        public void action() {
            ACLMessage message = receive();
            if (message != null) {
                String content = message.getContent();
                System.out.println("Assessment Agent received message: " + content);
                // Implement your custom message handling logic here
            } else {
                block();
            }
        }
    }

    public void sendMessageToAssessmentAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("userAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToRecommenderAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("recommenderAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToTutoringAgentAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("tutoringAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToResourceManagementAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("resourceManagementAgent", AID.ISLOCALNAME));
        send(message);
    }
}