package com.example.demo.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DAO.BoardDAO;
import com.example.demo.VO.BoardVO;

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
	public void list(Model model) {
		model.addAttribute("list",dao.findAll());
	}
	
	@GetMapping("/insertBoard")
	public void insertForm() {
	}
	
	@PostMapping("/insertBoard")
	public ModelAndView insertSubmit(BoardVO vo, HttpServletRequest request) {	// 매개변수의 순서는 상관없다
		
		// 파일 업로드 기능
		// 1. 업로드 할 실 경로 찾기
		String path=request.getServletContext().getRealPath("upload");
		System.out.println("path : "+path);		
		// 2. 업로드 파일이 있을수도 없을수도
		String fname=null;
		int fsize=0;

		// 3. vo에 담긴(form) 파일 정보 받아오기
		MultipartFile uploadFile=vo.getUploadFile();
		// 항상 null이 아니기 때문에 이 파일 객체로 판단할 수 없으므로 업로드한 파일 이름을 가져온다
		fname=uploadFile.getOriginalFilename();
		byte[]data=null;	// 아래에서 재사용하기 때문에 try 바깥에 선언
		try {
			data=uploadFile.getBytes();	
			fsize=data.length;
		} catch (Exception e) {
			System.out.println("controller insert file exception ==>"+e.getMessage());
		}
		
		vo.setFname(fname);
		vo.setFsize(fsize);
		
		// ip 주소 받아오기
		String ip=request.getRemoteAddr();	// 요청한 클라이언트의 ip 주소를 받아온다
		vo.setIp(ip);
		System.out.println(vo);
		int re=dao.insert(vo);
		if (re==1&&fname!=null&&!fname.equals("")) {
			//insert 성공했고 fname이 널이 아니고 ""도 아니라면(업로드 파일이 있다면)
			// 4. 파일을 복사해서 저장한다
			try {
				FileOutputStream fos=new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				System.out.println("controller file copy ==>"+e.getMessage());
			}
		}
		ModelAndView mav=new ModelAndView("redirect:/listBoard");
		if (re<=0) {
			mav.addObject("msg","게시물 등록에 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	// postmapping시에 이미 get 요청에서 이름이 같은 view를 넘겨줬으니까 mav로 뷰 네임을 정해주는건가?
	
	
//	@RequestMapping(value="/insertBoard", method=RequestMethod.POST)
//	public void insertForm() {
//	}
//	
//	@RequestMapping(value="/insertBoard", method=RequestMethod.POST)
//	public void insertSubmit() {
//	}
	
	@GetMapping("/detailBoard")
	public void detail(Model m, int no) {
		m.addAttribute("vo",dao.findByNo(no));
	}

	@GetMapping("/delete")
	public void delete(int no, Model m) {
		m.addAttribute("no",no);
	}
	
	@PostMapping("/delete")
	public ModelAndView delete(int no, String pwd, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView("redirect:/listBoard");
		BoardVO vo=dao.findByNo(no);
		int re=dao.delete(no, pwd);
		if (re<=0) {
			mav.setViewName("error");
			mav.addObject("msg","게시물 삭제에 실패");
		}
		if (re==1&&vo.getFname()!=null && !vo.getFname().equals("")) {
			String path=req.getServletContext().getRealPath("upload");
			System.out.println("path : "+path);		
			File file=new File(path+"/"+vo.getFname());
			file.delete();
		}
		return mav;
	}
	
	
	
}
