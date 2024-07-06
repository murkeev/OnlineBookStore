package teamchallenge.server.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image saveImage(MultipartFile image);

    ResponseImageDto getImageDto(Image images);
}
