package top.lmqstudy.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.pet.domain.Pet;
import top.lmqstudy.pet.query.PetQuery;
import top.lmqstudy.pet.service.IPetService;
import top.lmqstudy.product.domain.Product;
import top.lmqstudy.product.query.ProductQuery;
import top.lmqstudy.product.service.IProductService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/18/12:36
 * @Description:
 */
@RestController
@RequestMapping("/front")
public class FrontController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IPetService petService;

    @PostMapping("/product")
    public PageList<Product> queryData(@RequestBody ProductQuery query){
        return productService.queryPage(query);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id")Long id){
        return productService.getById(id);
    }


    @PostMapping("/pet")
    public PageList<Pet> queryData(@RequestBody PetQuery query){
        return petService.queryPage(query);
    }

    @GetMapping("/pet/{id}")
    public Pet getPetById(@PathVariable("id")Long id){
        return petService.getById(id);
    }
}
