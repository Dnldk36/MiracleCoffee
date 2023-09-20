package com.testing.webapp2.Controllers;


import com.testing.webapp2.Models.*;
import com.testing.webapp2.Repositories.*;
import com.testing.webapp2.sevice.ImageCoffeeShopService;
import com.testing.webapp2.sevice.ImageContactService;
import com.testing.webapp2.sevice.ImageMenuService;
import com.testing.webapp2.sevice.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@org.springframework.stereotype.Controller
@RequestMapping("/miracleCoffee")
public class Controller {

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageCoffeeShopService imageCoffeeShopService;
    @Autowired
    private ImageContactService imageContactService;
    @Autowired
    private ImageMenuService imageMenuService;
    @Autowired
    private SummaryRepository summaryRepository;
    @Autowired
    private CoffeeShopRepository coffeeShopRepository;
    @Autowired
    private ContactsRepository contactsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    // display image
    @Transactional
    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") long id) throws IOException, SQLException {
        Image image = imageService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
    @Transactional
    @GetMapping("/displayCoffeeShop")
    public ResponseEntity<byte[]> displayImageCoffeeShop(@RequestParam("id") long id) throws IOException, SQLException {
        ImageCoffeeShop image = imageCoffeeShopService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
    @Transactional
    @GetMapping("/displayContact")
    public ResponseEntity<byte[]> displayImageContact(@RequestParam("id") long id) throws IOException, SQLException {
        ImageContact image = imageContactService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
    @Transactional
    @GetMapping("/displayProduct")
    public ResponseEntity<byte[]> displayProductImg(@RequestParam("id") long id) throws SQLException {
        ImageMenu imageMenu = imageMenuService.viewById(id);
        byte[] imageBytes = null;
        imageBytes = imageMenu.getImage().getBytes(1,(int)imageMenu.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("")
    public String getHome() {
        return "miracleCoffe/home";
    }

    @GetMapping("/about")
    public String getAbout(Model model) {
        model.addAttribute("title", "About");
        model.addAttribute("nav", "about");
        return "miracleCoffe/about";
    }

    @GetMapping("/contacts")
    public String getContact(Model model) {
        model.addAttribute("title", "Contacts");
        model.addAttribute("nav", "contacts");

        //IMAGE
        ModelAndView mv = new ModelAndView("index");
        List<ImageContact> imageContactList = imageContactService.viewAll();
        mv.addObject("imageContactList", imageContactList);
        new ModelAndView("addimage");
        //IMAGE


        Iterable<Contacts> contacts = contactsRepository.findAll();
        model.addAttribute("contacts", contacts);

        return "miracleCoffe/contacts";
    }

    @Transactional
    @PostMapping("/contacts")
    public String workAdd(@RequestParam String jobTitle,
                          @RequestParam String nameContact,
                          @RequestParam String description,
                          @RequestParam long phoneNumber,
                          @RequestParam MultipartFile file2) throws SQLException, IOException {
        Contacts contacts = new Contacts(jobTitle, nameContact, description,phoneNumber);
        contactsRepository.save(contacts);
        //IMAGE
        byte[] bytes = file2.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        ImageContact imageContact = new ImageContact();
        imageContact.setImage(blob);
        imageContactService.create(imageContact);
        //IMAGE
        return "redirect:/miracleCoffee";
    }


    @GetMapping("/contact/{id}")
    public String getContact(Model model,
                          @PathVariable(value = "id") long id) {

        if (!contactsRepository.existsById(id))
            return "redirect:/miracleCoffee/contacts";



        Optional<Contacts> optionalContacts = contactsRepository.findById(id);
        List<Contacts> res = new ArrayList<>();
        optionalContacts.ifPresent(res::add);
        model.addAttribute("optionalContact", res);

        return "miracleCoffe/contact";
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("title", "Menu");
        model.addAttribute("nav", "menu");

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("Product", new Product());
        model.addAttribute("productList", products);

        Iterable<Categories> categories = categoryRepository.findAll();
        model.addAttribute("categoryList", categories);

        //IMAGE
        ModelAndView mv = new ModelAndView("index");
        List<ImageMenu> imageMenuList = imageMenuService.viewAll();
        mv.addObject("imageMenuList", imageMenuList);

        new ModelAndView("addimage");
        //IMAGE

        return "miracleCoffe/menu";
    }

    @GetMapping("/shop/{id}")
    public String getShop(Model model, @PathVariable(value = "id") long id) {

        if (!coffeeShopRepository.existsById(id))
            return "redirect:/miracleCoffee/shops";



        Optional<CoffeShop> optionalCoffeShop = coffeeShopRepository.findById(id);
        List<CoffeShop> res = new ArrayList<>();
        optionalCoffeShop.ifPresent(res::add);
        model.addAttribute("optionalCoffeeShop", res);

        return "miracleCoffe/shop";
    }

    @Transactional
    @PostMapping("/menu")
    public String workAdd(@RequestParam(required = false) String menuTitle,
                          @RequestParam(required = false, defaultValue = "0.0") double menuPrice,
                          @RequestParam(required = false) Categories menuCategories,
                          @RequestParam(required = false) String menuDescription,
                          @RequestParam(required = false) String categoryTitle,
                          @RequestParam(required = false) MultipartFile file3) throws IOException, SQLException {


        if (file3 != null){
            //MENU
            //Product product = new Product(menuTitle, menuDescription, menuPrice, menuCategories.getId());
            Product product = new Product(menuTitle, menuDescription, menuPrice, menuCategories);

            System.out.println(product);
            productRepository.save(product);
            //MENU

            //IMAGE
            byte[] bytes2 = file3.getBytes();
            Blob blob2 = new javax.sql.rowset.serial.SerialBlob(bytes2);
            ImageMenu imageMenu = new ImageMenu();
            imageMenu.setImage(blob2);
            imageMenuService.create(imageMenu);
            //IMAGE
        }

        if (categoryTitle != null){
            Categories categories = new Categories(categoryTitle);
            categoryRepository.save(categories);
        }

        return "redirect:/miracleCoffee";
    }

    @GetMapping("/shops")
    public String getShops(Model model) {
        model.addAttribute("title", "Shops");
        model.addAttribute("nav", "shops");



        ModelAndView mv = new ModelAndView("index");
        List<ImageCoffeeShop> imageCoffeeShops = imageCoffeeShopService.viewAll();
        mv.addObject("imageCoffeeShopList", imageCoffeeShops);

        new ModelAndView("addimage");


        Iterable<CoffeShop> coffeeShops = coffeeShopRepository.findAll();
        model.addAttribute("coffeeShops", coffeeShops);

        return "miracleCoffe/shops";
    }

    @Transactional
    @PostMapping("/shops")
    public String shopsAdd(@RequestParam String street,
                          @RequestParam String descriptionShop,
                          @RequestParam String workTime,
                          @RequestParam MultipartFile file1) throws SQLException, IOException {

        CoffeShop coffeShop = new CoffeShop(street,descriptionShop,workTime);
        coffeeShopRepository.save(coffeShop);

        //IMAGE
        byte[] bytes = file1.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        ImageCoffeeShop imageCoffeeShop = new ImageCoffeeShop();
        imageCoffeeShop.setImage(blob);
        imageCoffeeShopService.create(imageCoffeeShop);
        //IMAGE

        return "redirect:/miracleCoffee";
    }


    @GetMapping("/vacancy/{id}")
    public String getVacancy(@PathVariable(value = "id") long id, Model model) {
        if (!workerRepository.existsById(id))
            return "redirect:/miracleCoffee/work";

        Optional<Worker> optionalWorker = workerRepository.findById(id);
        List<Worker> res = new ArrayList<>();
        optionalWorker.ifPresent(res::add);
        model.addAttribute("workerVac", res);

        Worker workerView = workerRepository.findById(id).orElse(null);
        workerView.setViews(workerView.getViews() + 1);
        workerRepository.save(workerView);

        Iterable<Summary> summary = summaryRepository.findAll();
        model.addAttribute("Summary", new Summary());
        model.addAttribute("summaries", summary);

        Iterable<CoffeShop> coffeeShops = coffeeShopRepository.findAll();
        model.addAttribute("coffeeShop", coffeeShops);



        return "miracleCoffe/vacancy";
    }

    @PostMapping("/vacancy/{id}")
    public String getVacancyAdd(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam int age,
                                @RequestParam Summary.Gender gender,
                                Model model) {

        Summary summary = new Summary(name, age, description, gender);
        summaryRepository.save(summary);

        return "redirect:/miracleCoffee/work";
    }

    @Transactional
    @PostMapping("/work")
    public String workAdd(@RequestParam String title,
                          @RequestParam String Description,
                          @RequestParam int Salary,
                          @RequestParam int PostDay,
                          @RequestParam CoffeShop workShop,
                          @RequestParam MultipartFile file) throws SQLException, IOException {

        //Worker worker = new Worker(title, Description, Salary, PostDay, workShop);
        Worker worker = new Worker(title, Description, Salary, PostDay, workShop);
        workerRepository.save(worker);

        //IMAGE
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Image image = new Image();
        image.setImage(blob);
        imageService.create(image);
        //IMAGE

        return "redirect:/miracleCoffee";
    }

    @Transactional
    @GetMapping("/work")
    public String getWorkByPlace(Model model,
        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
        @RequestParam(value = "size", required = false, defaultValue = "3") int size,
        @RequestParam(required = false, defaultValue = "0") long id
    ) {

        if (id != 0){
            if (!workerRepository.existsById(id))
                return "redirect:/miracleCoffee/work";

            Optional<Worker> optionalWorker = workerRepository.findById(id);
            List<Worker> res = new ArrayList<>();
            optionalWorker.ifPresent(res::add);
            model.addAttribute("workerVac", res);
        }

        //SOMETHING
        model.addAttribute("imageId", 1);
        model.addAttribute("title", "Works");
        model.addAttribute("nav", "works");
        //SOMETHING

        //IMAGE
        ModelAndView mv = new ModelAndView("index");
        List<Image> imageList = imageService.viewAll();
        mv.addObject("imageList", imageList);

        new ModelAndView("addimage");
        //IMAGE

        //PAGINATION
        Page<Worker> pageWorkers = workerRepository.
                findAll(PageRequest.of(page, size,Sort.Direction.ASC, "id"));
        model.addAttribute("workers", pageWorkers);
        model.addAttribute("numbers", IntStream.range(0,pageWorkers.getTotalPages()).toArray());
        //model.addAttribute("pageWorkers", pageWorkers);
        //PAGINATION

        Iterable<CoffeShop> shopList = coffeeShopRepository.findAll();
        model.addAttribute("shopList", shopList);

        return "miracleCoffe/work-by-place";
    }


    // view All images
    @Transactional
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        List<Image> imageList = imageService.viewAll();
        List<ImageCoffeeShop> imageCoffeeShops = imageCoffeeShopService.viewAll();
        mv.addObject("imageList", imageList);
        mv.addObject("imageCoffeeShopList", imageCoffeeShops);
        return mv;
    }

    @GetMapping("/work-place")
    public String getWorkPlace(Model model) {

        Iterable<CoffeShop> coffeeShops = coffeeShopRepository.findAll();
        model.addAttribute("coffeeShops", coffeeShops);
        return "miracleCoffe/work-place";
    }

    @GetMapping("/admin")
    public String adminPane(Model model){

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("Product", new Product());
        model.addAttribute("products", products);

        Iterable<Categories> categories = categoryRepository.findAll();
        model.addAttribute("Category", new Categories());
        model.addAttribute("categoryList", categories);
        System.out.println(categories);

        Iterable<CoffeShop> shopList = coffeeShopRepository.findAll();
        model.addAttribute("shopList", shopList);

        return "miracleCoffe/adminPane";
    }

    @Transactional
    @PostMapping("/admin")
    public String adminPaneAdd(Model model,@RequestParam String title,
                               @RequestParam String Description,
                               @RequestParam  int Salary,
                               @RequestParam  int PostDay,
                               @RequestParam  MultipartFile file,
                               @RequestParam CoffeShop workShop,

                               @RequestParam String street,
                               @RequestParam String descriptionShop,
                               @RequestParam String workTime,
                               @RequestParam MultipartFile file1,

                               @RequestParam String categoryTitle,

                               @RequestParam String menuTitle,
                               @RequestParam String menuDescription,
                               @RequestParam double menuPrice,
                               @RequestParam Categories menuCategories,
                               @RequestParam MultipartFile file3) throws IOException, SQLException {
        //WORKER
        Worker worker = new Worker(title, Description, Salary, PostDay, workShop);
        workerRepository.save(worker);
        //IMAGE
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Image image = new Image();
        image.setImage(blob);
        imageService.create(image);
        //IMAGE

        //CoffeeShop
        CoffeShop coffeShop = new CoffeShop(street, descriptionShop, workTime);
        coffeeShopRepository.save(coffeShop);
        //IMAGE
        byte[] bytes1 = file1.getBytes();
        Blob blob1 = new javax.sql.rowset.serial.SerialBlob(bytes1);
        ImageCoffeeShop image1 = new ImageCoffeeShop();
        image1.setImage(blob1);
        imageCoffeeShopService.create(image1);
        //IMAGE


        //CATEGORY
        Categories categories = new Categories(categoryTitle);
        categoryRepository.save(categories);
        //CATEGORY

        //MENU
        Product product = new Product(menuTitle, menuDescription, menuPrice, menuCategories);
        System.out.println(product);
        productRepository.save(product);
        //MENU



        //IMAGE
        byte[] bytes2 = file3.getBytes();
        Blob blob2 = new javax.sql.rowset.serial.SerialBlob(bytes2);
        ImageMenu imageMenu = new ImageMenu();
        imageMenu.setImage(blob2);
        imageMenuService.create(imageMenu);
        //IMAGE



        return "redirect:/miracleCoffee";

    }
















    @Transactional
    @PostMapping("/work/{street}")
    public String workThisAdd(@RequestParam String title,
                          @RequestParam String Description,
                          @RequestParam int Salary,
                          @RequestParam int PostDay,
                          @RequestParam CoffeShop workShop,
                          @RequestParam MultipartFile file) throws SQLException, IOException {

        Worker worker = new Worker(title, Description, Salary, PostDay, workShop);
        workerRepository.save(worker);

        //IMAGE
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Image image = new Image();
        image.setImage(blob);
        imageService.create(image);
        //IMAGE

        return "redirect:/miracleCoffee";
    }

    @Transactional
    @GetMapping("/work/{street}")
    public String getThisWorkByPlace(Model model,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "3") int size,
                                 @PathVariable String street
    ) {

        if (!workerRepository.existsByCoffeShopStreet(street))
            return "redirect:/miracleCoffee/work";

        Iterable<Worker> workers = workerRepository.findAll();
        model.addAttribute("Worker", new Worker());
        model.addAttribute("workerList", workers);

        Iterable<CoffeShop> coffeShops = coffeeShopRepository.findAll();
        model.addAttribute("coffeeShopList", coffeShops);


        model.addAttribute("streetF", street);


        //SOMETHING
        model.addAttribute("imageId", 1);
        model.addAttribute("title", "Works");
        model.addAttribute("nav", "works");
        //SOMETHING

        //IMAGE
        ModelAndView mv = new ModelAndView("index");
        List<Image> imageList = imageService.viewAll();
        mv.addObject("imageList", imageList);

        new ModelAndView("addimage");
        //IMAGE

        //PAGINATION
        Page<Worker> pageWorkers = workerRepository.
                findAll(PageRequest.of(page, size,Sort.Direction.ASC, "id"));
        model.addAttribute("workers", pageWorkers);
        model.addAttribute("numbers", IntStream.range(0,pageWorkers.getTotalPages()).toArray());
        //model.addAttribute("pageWorkers", pageWorkers);
        //PAGINATION

        Iterable<CoffeShop> shopList = coffeeShopRepository.findAll();
        model.addAttribute("shopList", shopList);

        return "miracleCoffe/this-work-by-place";
        }

}

















