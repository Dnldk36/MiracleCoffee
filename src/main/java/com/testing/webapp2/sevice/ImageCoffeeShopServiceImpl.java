package com.testing.webapp2.sevice;

import com.testing.webapp2.Models.ImageCoffeeShop;
import com.testing.webapp2.Repositories.ImageCoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageCoffeeShopServiceImpl implements ImageCoffeeShopService{

    @Autowired
    private ImageCoffeeShopRepository imageCoffeeShopRepository;

    @Override
    public ImageCoffeeShop create(ImageCoffeeShop image) {
        return imageCoffeeShopRepository.save(image);
    }
    @Override
    public List<ImageCoffeeShop> viewAll() {
        return (List<ImageCoffeeShop>) imageCoffeeShopRepository.findAll();
    }
    @Override
    public ImageCoffeeShop viewById(long id) {
        return imageCoffeeShopRepository.findById(id).get();
    }
}
