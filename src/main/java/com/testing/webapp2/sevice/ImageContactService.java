package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.ImageContact;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ImageContactService {
    public ImageContact create(ImageContact image);
    public List<ImageContact> viewAll();
    public ImageContact viewById(long id);
}
