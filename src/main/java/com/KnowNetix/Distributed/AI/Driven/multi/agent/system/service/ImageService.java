package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.ImageModel;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ImageService {
    ResponseEntity<Map> uploadImage(ImageModel imageModel);
    ResponseEntity<Map> compressImage(ImageModel imageModel);
}
