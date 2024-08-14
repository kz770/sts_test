package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/updateBoard")
public class UpdateBoardController {
	@Autowired
	private BoardDAO dao;

	@RequestMapping(method = RequestMethod.GET)
	public void form(HttpServletRequest req,Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest req,BoardVO b) {
		String path = req.getServletContext().getRealPath("upload");
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		
		//원래 파일이름과 파일크기를 담아둡니다.
		String oldFname = b.getFname();
		int oldFsize = b.getFsize();
		
		String fname= null;
		int fsize = 0;
		byte []data = null;
		
		//업로드한 파일의 정보를 갖고 온다.
		MultipartFile uploadFile = b.getUploadFile();
		
		//업로드한 파일이 있다면
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				data = uploadFile.getBytes();
				fsize = data.length;
				b.setFname(fname);
				b.setFsize(fsize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//db에 수정을 요청한다.
		int re = dao.update(b);
		
		//수정에 성공했고, 파일도 수정했다면 파일을 복사시켜 준다.
		if(re >= 1 && fname != null && !fname.equals("")) {
			try {
				//파일출력하기 위한 스트림을 생성
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				
				//파일에 데이터를 기록한다.
				fos.write(data);
				
				//스트림을 닫아준다.
				fos.close();
				
				//원래파일이 있었다면 삭제 해 줍니다.
				if(oldFname != null && !oldFname.equals("")) {
					File file = new File(path + "/" + oldFname);
					file.delete();
				}
			}catch (Exception e) {
				System.out.println("예외발생"+e.getMessage());
			}
		}
		
		if(re <= 0) {
			mav.addObject("msg", "게시물 수정에 실패");
			mav.setViewName("error");
		}
		
		return mav;
	}
}











