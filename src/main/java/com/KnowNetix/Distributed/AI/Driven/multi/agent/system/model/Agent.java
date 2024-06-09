package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Agent  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String capabilities;

    private String communicationProtocol;

    private String interactionHistory;

    private String goals;

    private String state;
}
