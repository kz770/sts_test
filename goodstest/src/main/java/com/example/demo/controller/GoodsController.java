package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class GoodsController {
	public int totalRecord = 0;
	public int totalPage = 1;
	public int pageSIZE = 6;
	int start = 0;
	int end = 0;

	@Autowired
	private GoodsDAO dao;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/listGoods")
	public void list(Model m) {
//		List<GoodsVO> list=dao.findAll();
//		m.addAttribute("list",list);
		m.addAttribute("list", dao.findAll());
		// 요청한 이름의 뷰가 자동으로 설정된다.
	}

	@GetMapping("/listAll")
	public void listAll(Model m, int pageNUM, String keyword, String op, String orderColumn, String searchColumn, HttpSession session) {
		
		
		
		System.out.println("keyword : "+"|"+keyword+"|");
		
		//검색어가 ""이 오면 모두 검색 시키기 위하여
		//세션에 있는 검색어를 삭제한다
		//그리고 검색어에 null을 설정한다
		
		if (keyword != null && keyword.equals("")) {
			System.out.println("=== 모두 검색 ===");
			session.removeAttribute("keyword");
			keyword=null;
		}

		
		// 새로운 keyword가 없고, 세션에 저장된 검색어가 있다면 갖고온다
		if ((keyword==null || keyword.equals("")) && session.getAttribute("keyword")!=null) {
			keyword = (String)session.getAttribute("keyword");	//object를 반환
			searchColumn = (String)session.getAttribute("searchColumn");
			
		}
		
		// 새로운 정렬 컬럼이 없고 세션에 저장된 정렬컬럼이 있다면 갖고 온다.
		if (orderColumn==null && session.getAttribute("orderColumn")!=null) {
			orderColumn=(String)session.getAttribute("orderColumn");
		}
		
		HashMap map = new HashMap();
		map.put("searchColumn", searchColumn);
		map.put("orderColumn", orderColumn);
		System.out.println("orderColumn"+orderColumn);
		map.put("op", op);
		map.put("keyword", keyword);
		totalRecord = dao.totalRecord(map);
		System.out.println("total Record : " + totalRecord);
		totalPage = (int) Math.ceil((double) totalRecord / pageSIZE);
		System.out.println("total page : " + totalPage);
		start = ((pageNUM - 1) * pageSIZE) + 1;
		end = 0;
		if (pageNUM == totalPage) {
			end = totalRecord;
		} else {
			end = pageSIZE * pageNUM;
		}

		System.out.print("page : " + pageNUM);
		System.out.print("  start : " + start);
		System.out.println("  end : " + end);

		
		map.put("start", start);
		map.put("end", end);
		map.put("keyword", keyword);

		m.addAttribute("list", dao.listAll(map));
		m.addAttribute("totalRecord", totalRecord);
		m.addAttribute("totalPage", totalPage);
		m.addAttribute("keyword", keyword);
		
		//session.setAttribute("keyword", keyword);
		if (orderColumn!=null) {	// 입력창에 의해서 데이터가 들어오지 않으므로 "" 처리가 필요하지 않다.
			session.setAttribute("orderColumn", orderColumn);
		}
		//검색어가 있다면 session에 저장한다
		if (keyword!=null && !keyword.equals("")) {
			session.setAttribute("searchColumn", searchColumn);
			session.setAttribute("op", op);
			session.setAttribute("keyword", keyword);
		}
	}

	@GetMapping("/insert")
	public void insert() {
	}

	@PostMapping("/insert")
	public ModelAndView submit(GoodsVO vo, HttpServletRequest req) {
		String path = req.getServletContext().getRealPath("images");
		System.out.println(path);

		MultipartFile uploadFile = vo.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		System.out.println(fname);
		vo.setFname(fname);
		System.out.println(vo.getFname());
		int re = dao.insert(vo);
		if (re > 0) {
			try {
				byte[] data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		ModelAndView mav = new ModelAndView("redirect:/listAll");
		if (re <= 0) {
			mav.addObject("msg", "new item registration error occurs");
			mav.setViewName("error");
		}
		return mav;
	}

	@GetMapping("/detail")
	public void detail(String no, Model m) {
		m.addAttribute("vo", dao.findByNo(no));
		System.out.println("detail fname ==> " + dao.findByNo(no).getFname());
	}

	@GetMapping("/update")
	public void updateForm(String no, Model m) {
		m.addAttribute("vo", dao.findByNo(no));
		System.out.println("get update fname ==> " + dao.findByNo(no));
	}

	@PostMapping("/update")
	public ModelAndView updateSubmit(GoodsVO vo, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("error");
		String path = req.getServletContext().getRealPath("images");
		String oldFname = vo.getFname();
		System.out.println(oldFname);
		MultipartFile uploadFile = vo.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		System.out.println(fname);

		//파일 수정 후 update 진행
		vo.setFname(fname);
		int re = dao.update(vo);
		
			if (!fname.equals("") && fname != null) {	// 업로드한 파일이 있을 때
				byte[] data = null;
				System.out.println("after update fname"+vo.getFname());
				try {
					// 새로운 파일 복사
					data = uploadFile.getBytes();
					FileOutputStream fos = new FileOutputStream(path + "/" + fname);
					vo.setFname(fname);
					fos.write(data);
					fos.close();
					
					// 이전 파일 삭제
					if (oldFname != null && !oldFname.equals("")) { // 수정 전 파일 이름이 존재한다면
						System.out.println("이전파일삭제 진행");
						File oldFile = new File(path + "/" + oldFname);
						oldFile.delete();
					}
				} catch (Exception e) {
					System.out.println("update post error ==>" + e.getMessage());
				}
			}
			
			
			
			if (re > 0) { // 업데이트에 성공한다면
				mav.setViewName("redirect:/listAll?pageNUM=1");
			}
		return mav;
	}

	@GetMapping("/delete")
	public ModelAndView delete(String no, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("redirect:/listAll");
		String fname = dao.findByNo(no).getFname();
		int re = dao.delete(no);
		if (re <= 0) {
			mav.addObject("msg", "삭제 실패");
			mav.setViewName("error");
		}
		if (re > 0) {

			String path = req.getServletContext().getRealPath("images");
			System.out.println(path);
			File file = new File(path + "/" + fname);
			file.delete();
		}
		return mav;
	}

//	@PostMapping("/delete")
//	public void submit(int no) {
//		
//	}

}
