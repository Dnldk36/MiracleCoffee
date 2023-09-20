package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.ImageContact;
import com.testing.webapp2.Repositories.ImageContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageContactServiceImpl implements ImageContactService{

    @Autowired
    private ImageContactRepository imageContactRepository;

    @Override
    public ImageContact create(ImageContact image) {
        return imageContactRepository.save(image);
    }

    @Override
    public List<ImageContact> viewAll() {
        return (List<ImageContact>) imageContactRepository.findAll();
    }

    @Override
    public ImageContact viewById(long id) {
        return imageContactRepository.findById(id).get();
    }
}
