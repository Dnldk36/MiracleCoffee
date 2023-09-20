package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.Image;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ImageService {
    public Image create(Image image);
    public List<Image> viewAll();
    public Image viewById(long id);
}
