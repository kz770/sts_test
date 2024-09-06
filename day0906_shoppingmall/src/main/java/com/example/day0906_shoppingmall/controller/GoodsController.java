package com.example.day0906_shoppingmall.controller;

import com.example.day0906_shoppingmall.entity.Goods;
import com.example.day0906_shoppingmall.service.GoodsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods/insert")
    public void insertForm(){
    }

    @PostMapping("/goods/insert")
    public String insertSubmit(Goods g, HttpServletRequest request){
        String view = "redirect:/goods/list";
        String path = request.getServletContext().getRealPath("/resources/images");
        System.out.println("path:"+path);
        String fname = null;
        MultipartFile uploadFile = g.getUploadFile();
        fname = uploadFile.getOriginalFilename();
        if(fname != null && !fname.equals("")){
            try{
                FileOutputStream fos = new FileOutputStream(path+"/"+fname);
                FileCopyUtils.copy(uploadFile.getInputStream(), fos);
                fos.close();
                g.setFname(fname);
            }catch (Exception e){
                System.out.println("예외발생:"+e.getMessage());
            }
        }
        goodsService.insert(g);
        return view;
    }
}










