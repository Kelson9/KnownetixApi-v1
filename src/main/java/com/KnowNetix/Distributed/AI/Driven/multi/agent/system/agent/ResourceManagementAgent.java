package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ResourceManagementAgent extends Agent {

    protected void setup() {
        System.out.println("Resource Management Agent is ready.");

        // Add behaviors to the agent
        addBehaviour(new MessageReceiver());
    }

    protected void takeDown() {
        System.out.println("Resource Management Agent is shutting down.");
    }

    private class MessageReceiver extends CyclicBehaviour {
        public void action() {
            ACLMessage message = receive();
            if (message != null) {
                String content = message.getContent();
                System.out.println("Resource Management Agent received message: " + content);
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

    public void sendMessageToUserAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("userAgent", AID.ISLOCALNAME));
        send(message);
    }
}
