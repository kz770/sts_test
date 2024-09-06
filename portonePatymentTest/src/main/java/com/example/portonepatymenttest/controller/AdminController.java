package com.example.portonepatymenttest.controller;

import com.example.portonepatymenttest.entity.Product;
import com.example.portonepatymenttest.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;

    @GetMapping("/admin/insert")
    public void insertSubmit() {

    }

    @PostMapping("/admin/insert")
    public String insertProduct(Product product, HttpServletRequest req) {
        product.setNo(productService.getNextNo());
        System.out.println(product);
        Product p=productService.save(product);
        //C:\Users\WD\AppData\Local\Temp\tomcat-docbase.8080.7113836209021578615\resources\images\
        //C:\sts_test\portonePatymentTest\src\main\resources\webapp\resources\images
        String path=req.getServletContext().getRealPath("/resources/upload/");
//        System.out.println(path);
        MultipartFile uploadFile=product.getMultipartFile();
        String fname=uploadFile.getOriginalFilename();

        product.setFname("");

        if (fname != null && !fname.equals("")){
            try {
                byte []data = uploadFile.getBytes();
                product.setFname(fname);
                FileOutputStream fos=new FileOutputStream(path+"/"+fname);
                fos.write(data);
                fos.close();
            }catch (IOException e){

            }
        }

        if(p==null){
            System.out.println("상품 등록 실패");
            return "redirect:/admin/insert";
        }
        return "redirect:/product/list";
    }
}
