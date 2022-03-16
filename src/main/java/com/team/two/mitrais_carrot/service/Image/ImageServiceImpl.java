package com.team.two.mitrais_carrot.service.Image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.two.mitrais_carrot.entity.image.Image;
import com.team.two.mitrais_carrot.entity.image.ImageResponse;
import com.team.two.mitrais_carrot.repository.ImageRepository;
import com.team.two.mitrais_carrot.service.Image.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image save(Image image) throws NullPointerException {
        if (image == null)
            throw new NullPointerException("Image Data NULL");
        return imageRepository.save(image);
    }

    @Override
    public Image findByFileName(String fileName) {
        return this.imageRepository.findByFileName(fileName);
    }

    @Override
    public Image findByUuid(String uuid) {
        return this.imageRepository.findByUuid(uuid);
    }

    @Override
    public List<ImageResponse> findAllImageResponse() {
        return this.imageRepository.findAllImageResponse();
    }

}