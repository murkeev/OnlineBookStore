package teamchallenge.server.catalog.image.service;

import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.image.dto.ResponseImageDto;
import teamchallenge.server.catalog.image.entity.Image;

public interface ImageService {

    Image saveImage(MultipartFile image);

    ResponseImageDto getImageDto(Image images);
}
