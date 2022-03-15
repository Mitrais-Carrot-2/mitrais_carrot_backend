package com.team.two.mitrais_carrot.repository;

import java.util.List;

import com.team.two.mitrais_carrot.entity.image.Image;
import com.team.two.mitrais_carrot.entity.image.ImageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByFileName(String fileName);

    Image findByUuid(String uuid);

    @Query(value = "select new com.team.two.mitrais_carrot.entity.image.ImageResponse(im.uuid, im.fileName, im.fileType, im.size) from com.team.two.mitrais_carrot.entity.image.Image im where im.status=true", nativeQuery = false)
    List<ImageResponse> findAllImageResponse();

}