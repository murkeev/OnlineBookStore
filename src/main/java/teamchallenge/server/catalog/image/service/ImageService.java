package teamchallenge.server.catalog.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveImageToS3(MultipartFile photo) throws IOException;

    void deleteImageFromS3(String imageKey);

    String getImageUrl(String imageKey);
}
