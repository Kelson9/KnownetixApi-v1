package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
}
