package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Module;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ModuleRepository extends JpaRepository<Module,Long> {
    List<Module> findAllByCourseId(Long courseId);
    List<Module> findAllByCreatedBy(User user);
}
