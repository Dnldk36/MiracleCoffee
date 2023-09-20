package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.ImageCoffeeShop;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ImageCoffeeShopService {
    public ImageCoffeeShop create(ImageCoffeeShop image);
    public List<ImageCoffeeShop> viewAll();
    public ImageCoffeeShop viewById(long id);
}
