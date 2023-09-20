package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.ImageMenu;
import com.testing.webapp2.Repositories.ImageMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageMenuServiceImpl implements ImageMenuService{

    @Autowired
    private ImageMenuRepository imageMenuRepository;

    @Override
    public ImageMenu create(ImageMenu image) {
        return imageMenuRepository.save(image);
    }

    @Override
    public List<ImageMenu> viewAll() {
        return (List<ImageMenu>)imageMenuRepository.findAll();
    }

    @Override
    public ImageMenu viewById(long id) {
        return imageMenuRepository.findById(id).get();
    }
}
