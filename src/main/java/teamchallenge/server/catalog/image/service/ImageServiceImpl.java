package teamchallenge.server.catalog.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.book.service.BookService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    @Value("${aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3 amazonS3;

    @Override
    public String saveImageToS3(MultipartFile photo) throws IOException {
        String fileName = "images/" + System.currentTimeMillis() + "_" + photo.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/webp");
        metadata.setContentLength(photo.getSize());
        amazonS3.putObject(bucketName, fileName, photo.getInputStream(), metadata);
        return fileName;
    }

    @Override
    public void deleteImageFromS3(String imageKey) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, imageKey));
    }

    @Override
    public String getImageUrl(String imageKey){
        return amazonS3.getUrl(bucketName, imageKey).toString();
    }
}
