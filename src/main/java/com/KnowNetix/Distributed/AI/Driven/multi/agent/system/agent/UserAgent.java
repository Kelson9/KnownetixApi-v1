package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAgent extends Agent {

    @Autowired
    private UserService userService;

    @Autowired
    private  UserRepository userRepository;

    public UserAgent(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public UserAgent() {

    }

    protected void setup() {
        System.out.println("User Agent is ready.");

        addBehaviour(new MessageReceiver());
    }

    protected void takeDown() {
        System.out.println("User Agent is shutting down.");
    }

    private class MessageReceiver extends CyclicBehaviour {
        public void action() {
            ACLMessage message = receive();
            if (message != null) {
                String content = message.getContent();
                System.out.println("User Agent received message: " + content);

                // Implement your custom message handling logic here
            } else {
                block();
            }
        }
    }

    public void sendMessageToAssessmentAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("assessmentAgent", AID.ISLOCALNAME));
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

    public String getUserInterest(){
        UserDetailsImpl userDetails = userService.getUserDetails();
        Optional<User> userOptional = userRepository.findById(userDetails.getId());
        return userOptional.get().getInterest();

    }

    public String getUserLevel(){
        UserDetailsImpl userDetails = userService.getUserDetails();
        Optional<User> userOptional = userRepository.findById(userDetails.getId());
        return userOptional.get().getLevelOfEducation();
    }

    public String getUserCognitiveState(){
        UserDetailsImpl userDetails = userService.getUserDetails();
        Optional<User> userOptional = userRepository.findById(userDetails.getId());
        return userOptional.get().getInterest();
    }

    public String sendUserInterestToRecommenderAgent() {
        String message1 = getUserInterest();
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(message1);
        message.addReceiver(new AID("recommenderAgent", AID.ISLOCALNAME));
        send(message);
        return  message1;
    }

    public String sendUserLevelToRecommenderAgent() {
        String message1 = getUserLevel();
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(message1);
        message.addReceiver(new AID("recommenderAgent", AID.ISLOCALNAME));
        send(message);
        return  message1;
    }
}
