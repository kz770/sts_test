package com.example.demo.controller;

import java.io.FileOutputStream;
import java.net.http.HttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Board;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardContoller {

	@PostMapping("/board/insert")
	public String insertSubmit(Board b, HttpServletRequest req) {
		String view = "redirect:/board/list";

		// 파일을 업로드 할 실제 경로를 알아온다
		String path = req.getServletContext().getRealPath("/resources/upload");
		System.out.println("path : " + path);

		// 클라이언트가 업로드한 파일의 정보를 갖고온다
		MultipartFile uploadFile = b.getUploadFile();

		// 업로드한 파일 이름을 갖고옵니다
		String fname = uploadFile.getOriginalFilename();

		// 일반 엔티티에 파일정보가 없다고 보고
		// 파일 이름에 "", 파일 크기에 0을 설정한다.
		b.setFname("");
		b.setFsize(0);

		// 만약, 업로드한 파일이 있다면
		if (fname != null && fname.equals("")) {
			try {
				byte[] data = uploadFile.getBytes();

				// 업로드한 파일 이름과 파일 크기를 엔티티에 저장한다.
				b.setFname(fname);
				b.setFsize(data.length);

				// 파일 복사를 위하여 출력스트림을 생성한다
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				// 생성된 스트림에 파일 내용을 출력한다
				fos.write(data);

				// 파일을 닫아준다.
				fos.write(data);
//			
			} catch (Exception e) {
//			// TODO: handle exception
			}
		}

		return view;
	}
	
	
}
