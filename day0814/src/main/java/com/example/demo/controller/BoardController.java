package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDAO dao;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/listBoard")
	public void list(HttpServletRequest req, Model model) {
		
		String uri=req.getRequestURI();
		String ip=req.getRemoteAddr();
		System.out.println(uri);
		System.out.println(ip);
		model.addAttribute("list", dao.findAll());
	}
	
//	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
//	public void insertForm() {
//		
//	}
//	
//	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
//	public ModelAndView insertSubmit() {
//		
//	}
	
	@GetMapping("/insertBoard")
	public void insertForm(HttpServletRequest req, @RequestParam(value = "no", defaultValue = "0") int no, Model model) {
		model.addAttribute("no", no);
		String title = "게시물 등록";
		if(no != 0) {
			title = "답글 작성";
		}
		model.addAttribute("title", title);
	}
	
	@PostMapping("/insertBoard")
	public ModelAndView insertSubmit(HttpServletRequest req, BoardVO b) {
		String path = req.getServletContext().getRealPath("upload");
		System.out.println("path:"+path);
		
		String fname = null;
		int fsize = 0;
			
		
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		byte[]data = null;
		try {
			data = uploadFile.getBytes();
			fsize = data.length;
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		b.setFname(fname);
		b.setFsize(fsize);		
		
		String ip = req.getRemoteAddr();
		b.setIp(ip);
		
		
		int pno = b.getNo();
				
		int no = dao.getNextNo();
		
		//새글에 대한 처리
		int b_ref = no;
		int b_level = 0;
		int b_step = 0;
		
		//답글에 대한 처리
		if(pno != 0) {
			BoardVO p = dao.findByNo(pno);
			b_ref = p.getB_ref();
			b_level = p.getB_level();
			b_step = p.getB_step();
			HashMap map = new HashMap();
			map.put("b_ref", b_ref);
			map.put("b_step", b_step);
			dao.updateStep(map);
			b_level++;
			b_step++;
		}
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_level(b_level);
		b.setB_step(b_step);
		
		int re = dao.insert(b);
		
		//insert를 성공했고
		//업로드 파일이 있다면
		//파일을 복사합니다.
		if(re == 1 && fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path + "/"+ fname);
				fos.write(data);
				fos.close();
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		if(re <= 0) {
			mav.addObject("msg", "게시물 등록에 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/detailBoard")
	public void detail(HttpServletRequest req, Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
	@GetMapping("/deleteBoard")
	public void deleteForm(Model model, int no) {
		model.addAttribute("no", no);
	}
	
	@PostMapping("/deleteBoard")
	public ModelAndView deleteSubmit(int no, String pwd, HttpServletRequest request) {
		//게시물 삭제시키기 전에 파일이름을 먼저 알아 놓습니다.
		String oldFname = dao.findByNo(no).getFname();
		String path = request.getServletContext().getRealPath("upload");
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");		
		int re = dao.delete(no, pwd);
		
		// 그다음 어떤 작업을 해야 할까요?
		// 만약, 삭제에 성공했고 파일도 있는 게시물이었다면 해당 파일을 삭제합니다
		if(re >0 && oldFname != null && !oldFname.equals("")) {
			File file = new File(path + "/" + oldFname);
			file.delete();
		}
		
		if(re <= 0) {
			mav.addObject("msg", "게시물 삭제에 실패");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
}



































