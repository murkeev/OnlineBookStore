package teamchallenge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
