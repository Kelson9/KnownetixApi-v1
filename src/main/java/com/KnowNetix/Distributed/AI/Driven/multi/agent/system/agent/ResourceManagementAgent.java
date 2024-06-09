package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent;

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
}
