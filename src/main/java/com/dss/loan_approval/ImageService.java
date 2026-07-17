package com.dss.loan_approval;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class ImageService {
    private final Cloudinary cloudinary;

    public Map<String, String> uploadImage(MultipartFile file, String folder) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", folder));

            return Map.of(
                    "url", uploadResult.get("secure_url").toString(),
                    "publicId", uploadResult.get("public_id").toString()
            );
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    public String attachImage(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return uploadImage(file, folder).get("url");
    }

    public void deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Image deletion failed", e);
        }
    }
}
