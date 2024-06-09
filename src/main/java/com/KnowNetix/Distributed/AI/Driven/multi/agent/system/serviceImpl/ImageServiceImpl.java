package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.ImageModel;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Image;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.ImageRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.CloudinaryService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ImageService;
import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.KnowNetix.Distributed.AI.Driven.multi.agent.system.config.Constants.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    private static final String CLOUDINARY_BASE_URL = "http://res.cloudinary.com";
    private static final String CLOUD_NAME = "dvjvgbl8n";
    private static final String VERSION = "v1";
    private static final String IMAGES_FOLDER = "images";

    @Resource
    private Cloudinary cloudinary;


    @Override
    public ResponseEntity<Map> uploadImage(ImageModel imageModel) {
        try {
            if (imageModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Image image = new Image();
            image.setName(imageModel.getName());
            image.setUrl(cloudinaryService.uploadFile(imageModel.getFile(), "folder_1"));
            if(image.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<Map> compressImage(ImageModel imageModel) {
        try {
            if (imageModel.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", "images");
            options.put("quality", "auto");
            options.put("resource_type", "auto");

            Map uploadedFile;
            String compressedImageUrl;
            String resourceType = getResourceTypeFromFile(imageModel.getFile());

            if(resourceType.equals("video")){
                options.put("resource_type", "video");
                uploadedFile = cloudinary.uploader().uploadLarge(imageModel.getFile().getBytes(), options);
                String publicId = (String) uploadedFile.get("public_id");
                compressedImageUrl = getCompressedVideoURL(publicId);
                System.out.println("upload video" + uploadedFile);

                return ResponseEntity.ok().body(Map.of("url", compressedImageUrl));
            }

            else if (resourceType.equals("image")) {
                uploadedFile = cloudinary.uploader().upload(imageModel.getFile().getBytes(), options);
                System.out.println("upload image" + uploadedFile);
                String publicId = (String) uploadedFile.get("public_id");
                compressedImageUrl = getCompressedImageURL(publicId);

                return ResponseEntity.ok().body(Map.of("url", compressedImageUrl));
            }

            else if (resourceType.equals("raw")) {
                uploadedFile = cloudinary.uploader().upload(imageModel.getFile().getBytes(), options);
                System.out.println("upload pdf" + uploadedFile);
                String publicId = (String) uploadedFile.get("public_id");
                compressedImageUrl = getCompressedPdfURL(publicId);

                return ResponseEntity.ok().body(Map.of("url", compressedImageUrl));
            }

            else{
                System.out.println("upload error");
                return null;
            }

//            String publicId = (String) uploadedFile.get("public_id");
//
//            Transformation transformation = new Transformation()
//                    .width(1000).crop("scale")
//                    .quality("auto").fetchFormat("auto");
//
//            String compressedImageUrl = cloudinary.url()
//                    .transformation(transformation)
//                    .generate(publicId);
//
//            System.out.println("compressed fileurl" + compressedImageUrl);
//
//
//            cloudinary.uploader().upload(compressedImageUrl, getUploadOptions());

//            return ResponseEntity.ok().body(Map.of("url", compressedImageUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HashMap<Object, Object> getUploadOptions() {
        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", "compressedImages");
        return options;
    }

    private String getResourceTypeFromFile(MultipartFile file) {
        String filename = Optional.ofNullable(file.getOriginalFilename()).orElseThrow();
        String fileExtension = getFileExtension(filename).toLowerCase();
        if (ALLOWED_IMAGE_EXTENSIONS.contains(fileExtension)) {
            return "image";
        }
        if (ALLOWED_VIDEO_EXTENSIONS.contains(fileExtension)) {
            return "video";
        }
        if (ALLOWED_DOCUMENTS_EXTENSIONS.contains(fileExtension)) {
            return "raw";
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String getCompressedImageURL(String publicId) {
        StringBuilder url = new StringBuilder();
        url.append(CLOUDINARY_BASE_URL)
                .append("/")
                .append(CLOUD_NAME)
                .append("/image/upload/c_scale,f_auto,q_50/")
                .append(VERSION)
                .append("/")
//                .append(IMAGES_FOLDER)
//                .append("/")
                .append(publicId)
                .append(".jpg");
        return url.toString();
    }

 private String getCompressedVideoURL(String publicId) {
        StringBuilder url = new StringBuilder();
        url.append(CLOUDINARY_BASE_URL)
                .append("/")
                .append(CLOUD_NAME)
                .append("/video/upload/q_50,vc_auto/")
                .append(VERSION)
                .append("/")
                .append(publicId)
                .append(".m3u8");
        return url.toString();
    }

 //   private String getCompressedVideoURL(String publicId) {
   //     StringBuilder url = new StringBuilder();
     //   url.append(CLOUDINARY_BASE_URL)
       //         .append("/")
         //       .append(CLOUD_NAME)
           //     .append("/video/upload/q_50/,vc_auto/fl_streaming_profile.hd")
             //   .append(VERSION)
               // .append("/")
               // .append(publicId)
               // .append(".m3u8");
       // return url.toString();
    //}

    private String getCompressedPdfURL(String publicId) {
        StringBuilder url = new StringBuilder();
        url.append(CLOUDINARY_BASE_URL)
                .append("/")
                .append(CLOUD_NAME)
                .append("/image/upload/q_50/")
                .append(VERSION)
                .append("/")
                .append(publicId)
                .append(".pdf");
        return url.toString();
    }
}