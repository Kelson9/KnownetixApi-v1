package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String updatedBy = "";
    private Boolean deleted;
    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdAt = localDateTime;
        this.updatedAt = localDateTime;
        this.deleted = false;
        addUpdatedByUser();
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        addUpdatedByUser();
    }
    void addUpdatedByUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetailsImpl user = (UserDetailsImpl) principal;
            this.updatedBy = user.getUsername();
        } catch (Exception ex) {
            this.updatedBy = "Default";
        }
    }
}