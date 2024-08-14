package com.example.demo.Controller;

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

import com.example.demo.DAO.BoardDAO;
import com.example.demo.VO.BoardVO;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/update")	// 컨트롤러 자체에 매핑
public class UpdateBoardController {
	
	@Autowired
	BoardDAO dao;
	
	@RequestMapping(method=RequestMethod.GET)	// 메서드마다 방식만 정해준다
	public void form(Model m, int no) {
		m.addAttribute("vo",dao.findByNo(no));
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView submmit(BoardVO vo, HttpServletRequest req) {
		String path=req.getServletContext().getRealPath("upload");
		System.out.println(path);
		ModelAndView mav=new ModelAndView("redirect:/listBoard");
		
		// 원래 파일 이름과 파일 크기를 담아둡니다
		String oldFname=vo.getFname();
		int oldFsize=vo.getFsize();
		
		// 새로 업로드한 파일
		String fname=null;
		int fsize=0;
		byte []data=null;
		
		MultipartFile uploadFile=vo.getUploadFile();
		fname=uploadFile.getOriginalFilename();
		if (fname != null && !fname.equals("")) {
			try {
				data=uploadFile.getBytes();
				fsize=data.length;
				vo.setFname(fname);
				vo.setFsize(fsize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("upload controller post error ==>" + e.getMessage());
			}
		}
		int re=dao.update(vo);
		
		// 수정에 성공했고 파일도 수정했다면 파일 복사
		if (re>=1 && fname!=null && !fname.equals("")) {
			try {
				System.out.println("수정 성공 : "+vo);
				FileOutputStream fos=new FileOutputStream(path+"/"+fname);
				System.out.println(fname);
				fos.write(data);
				fos.close();
				
				// 원래 파일이 있었다면 삭제하기
				if (oldFname!=null && !oldFname.equals("")) {
					System.out.println("old file : "+oldFname);
					File file=new File(path+"/"+oldFname);
					file.delete();
				}
			} catch (Exception e) {
				System.out.println("update controller post file copy error ==> "+e.getMessage());
			}
		}
		
		if (re<=0) {
			mav.addObject("msg","수정 실패");
		}
		
		return mav;
	}
}
