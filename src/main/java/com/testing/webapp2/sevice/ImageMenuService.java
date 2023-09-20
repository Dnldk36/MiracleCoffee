package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.ImageMenu;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ImageMenuService {
    public ImageMenu create(ImageMenu image);
    public List<ImageMenu> viewAll();
    public ImageMenu viewById(long id);
}
