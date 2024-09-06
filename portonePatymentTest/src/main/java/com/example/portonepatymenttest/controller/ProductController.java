package com.example.portonepatymenttest.controller;

import com.example.portonepatymenttest.entity.Customer;
import com.example.portonepatymenttest.entity.Product;
import com.example.portonepatymenttest.service.CustomerService;
import com.example.portonepatymenttest.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;

    @GetMapping("/product/list")
    public void list(Model m, HttpSession session, HttpServletRequest request) {
        m.addAttribute("productLists",productService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User)authentication.getPrincipal();
        String id = user.getUsername();
        Customer c = customerService.findCustomerById(id);
        session.setAttribute("loginUSER", c);
        System.out.println("login user 저장 : "+session.getAttribute("loginUSER"));
    }

    @GetMapping("/product/detail/{no}")
    public String productDetail(@PathVariable("no") int no, Model m) {
        Product product = productService.findById(no);
        if (product==null){
            return "redirect:/product/list";
        }
        m.addAttribute("product",product);
        return "/product/detail";
    }

    @Transactional
    @GetMapping("/admin/delete/{no}")
    public String deleteProduct(@PathVariable("no") int no) {
        productService.delete(no);
        return "redirect:/product/list";
    }

    @GetMapping("/admin/update/{no}")
    public String updateProduct(@PathVariable("no") int no, Model m) {
        Product product = productService.findById(no);
        m.addAttribute("product",product);
        return "/admin/update";
    }

    @PostMapping("/admin/update")
    public String updateProduct(Product product) {
        productService.save(product);
        return "redirect:/product/list";
    }
}
