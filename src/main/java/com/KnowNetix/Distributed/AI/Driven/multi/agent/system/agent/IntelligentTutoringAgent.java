package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class IntelligentTutoringAgent extends Agent {

    protected void setup() {
        System.out.println("Intelligent Tutoring Agent is ready.");

        // Add behaviors to the agent
        addBehaviour(new MessageReceiver());
    }

    protected void takeDown() {
        System.out.println("Intelligent Tutoring Agent is shutting down.");
    }

    private class MessageReceiver extends CyclicBehaviour {
        public void action() {
            ACLMessage message = receive();
            if (message != null) {
                System.out.println("Received message: " + message.getContent());
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
        message.addReceiver(new AID("userAgent", AID.ISLOCALNAME));
        send(message);
    }

    public void sendMessageToResourceManagementAgent(String content) {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent(content);
        message.addReceiver(new AID("resourceManagementAgent", AID.ISLOCALNAME));
        send(message);
    }
}