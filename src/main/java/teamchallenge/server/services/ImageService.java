package teamchallenge.server.services;

import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.dto.ResponseImageDto;
import teamchallenge.server.entities.Image;

public interface ImageService {

    Image saveImage(MultipartFile image);

    ResponseImageDto getImageDto(Image images);
}
