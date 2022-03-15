package com.team.two.mitrais_carrot.service.Image;

import java.util.List;

import com.team.two.mitrais_carrot.entity.image.Image;
import com.team.two.mitrais_carrot.entity.image.ImageResponse;

public interface ImageService {

    public Image save(Image image);

    public Image findByFileName(String fileName);

    public Image findByUuid(String uuid);

    public List<ImageResponse> findAllImageResponse();

}
