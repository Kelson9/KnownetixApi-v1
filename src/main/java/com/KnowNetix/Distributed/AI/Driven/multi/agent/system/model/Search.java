package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Search extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String keywords;

    private int count;
}
