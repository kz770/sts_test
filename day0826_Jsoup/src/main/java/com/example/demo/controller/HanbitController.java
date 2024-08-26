package com.example.demo.controller;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.Book;
import com.example.demo.vo.NewBook;

@RestController
public class HanbitController {
	
	@Autowired
	private BookDAO dao;
	
	@PostMapping("/search")
    public String search(String keyword) {
        String base = "https://www.hanbit.co.kr";
        // 사용자가 입력한 검색어를 갖고 url을 만들어준다
        String url = "https://www.hanbit.co.kr/store/books/new_book_list.html?keyWord="+keyword+"&searchKey=p_title";
        try {
            Document doc= Jsoup.connect(url).get();
            Elements list =  doc.select(".book_tit");
            for(Element e :list) {
            	//book_tit의 첫번째 자식태그(a)를 찾는다
                Element a = e.firstElementChild();
                
                //a태그의 글자와 href를 갖고온다
                String title = a.text();
                String link = a.attr("href");
                
                //  /store/books/look.php?p_code=B6171497304
                // 링크로부터 책 번호를 뽑아와서 저장한다
                String p_code = link.substring(link.indexOf("=")+1); //=이 있는 위치 다음부터 잘라와~
                
                // 베이스 경로에 link를 연결하여 책 상세보기 url을 만들어준다
                String url2 = base + link;	//상세보기 url
                Document doc2= Jsoup.connect(url2).get();
                
                Elements li =
                doc2.select(".info_list").get(0).getElementsByTag("li");
                System.out.println("li의 총 길이"+li.size());
                String writer="";
                String regdate="";
                int price=0;
                
                for (Element l : li) {
					Elements strongTags=l.select("strong");
					for (Element strongTag : strongTags) {
						if (strongTag.text().contains("저자")) {
							writer=l.getElementsByTag("span").get(0).text();
						}
						if (strongTag.text().contains("출간")) {
							regdate=l.getElementsByTag("span").get(0).text();
							
						}
					}
				}
                
                
//                if (li.size()<=6) {
//                	writer = li.get(0).getElementsByTag("span").get(0).text();
//                	regdate = li.get(1).getElementsByTag("span").get(0).text();
//                	price = Integer.parseInt(doc2.select(".pbr").get(0).text().replace(",", "").replace("원", ""));
//				}else {
//					writer = li.get(0).getElementsByTag("span").get(0).text();
//					regdate = li.get(2).getElementsByTag("span").get(0).text();
//					
//				}
				price = Integer.parseInt(doc2.select(".pbr").get(0).text().replace(",", "").replace("원", ""));
                
                
                System.out.println("---------------------------");
                System.out.println("도서코드:"+p_code);
                System.out.println("도서명:"+title);
                System.out.println("저자:"+writer);
                System.out.println("출간일:"+regdate);
                System.out.println("가격:"+price);
                System.out.println("---------------------------");
//                Book book = new Book();
//                book.setP_code(p_code);
//                book.setTitle(title);
//                book.setRegdate(regdate);
//                book.setPrice(price);
//                book.setWriter(writer);
//                dao.save(book);
            }
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
        return "OK";
    }
	
	// img가 있는 url과 저장할 file명을 매개변수로 전달 받아 파일을 다운로드하는 메서드
	public void imageDownload(String addr, String fname) {
        fname = fname.replace("/", "_");
        fname = fname.replace("?", "_");
        fname = fname.replace("#", "_");
        fname = fname.replace(":", "_");
        try {
            URL url = new URL(addr);
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream("c:/data/"+fname+".jpg");
            FileCopyUtils.copy(is.readAllBytes(), fos);
            is.close();
            fos.close();
            System.out.println(fname+"을 저장하였습니다.");
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
    }
	
	@GetMapping("/downAll")
    public String downAll() {
        try {
            int page = 1;
            while(true) {
                String url = "https://www.hanbit.co.kr/store/books/new_book_list.html?page="+page;
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select(".view_box");
                if(elements.size() == 0) {
                    break;
                }
                for(Element e:elements) {
                    Elements img  = e.getElementsByTag("img");
                    String src = img.get(img.size()-1).attr("src");
                    String title =
                    e.select(".book_tit").get(0).getElementsByTag("a").get(0).text();
                    imageDownload("https://www.hanbit.co.kr"+src, title);
                }
                System.out.println(page+"페이지를 다운로드하였습니다.");
                System.out.println("-------------------------------");
                page++;
            }
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
        return "OK";
    }
	
	@GetMapping("downImg")
	public String downImg() {
		String data="OK";
		String addr="https://www.hanbit.co.kr/data/books/B3243045669_l.jpg";
		try {
			String fname="실무로 통하는 타입스크립트.jpg";
			URL url=new URL(addr);
			InputStream is=url.openStream();
			FileOutputStream fos=new FileOutputStream("c:/img/"+fname);
			FileCopyUtils.copy(is.readAllBytes(), fos);
			is.close();
			fos.close();
			System.out.println("이미지를 다운로드 하였습니다");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	@GetMapping("/seat")
	public String getSeat() {
		String data="";
		String url="http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp";
		//waiting_f
		try {
			Document doc=Jsoup.connect(url).get();
			data=doc.select(".wating_f").get(0).text();
			System.out.println(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

	@GetMapping("/newBook")
	public List<NewBook> newBook() {
		List<NewBook> arr=new ArrayList<NewBook>();
		String base="https://www.hanbit.co.kr/";
		// url에 연결하여 문서를 읽어온다
		int i=1;
		while(true) {
			String url="https://www.hanbit.co.kr/store/books/new_book_list.html?page="+i;
			try {
				Document doc=Jsoup.connect(url).get();
//			System.out.println(doc);
				Elements list=doc.select(".book_tit");
				if (list.size()==0) {
					break;
				}
				for (Element e : list) {
					Element a=e.firstElementChild();
					String title=a.text();
//					System.out.println(title);
					String link=base+a.attr("href");
//					System.out.println(link);
					arr.add(new NewBook(title,link));
					
				}
			} catch (Exception e) {
				System.out.println("예외 발생 : "+e.getMessage());
			}
			System.out.println(i+"페이지 수집");
			i++;
		}
		return arr;	//rest controller의 응답은 자동으로 json 형태로 만들어진다.
	}
	
//	@GetMapping("/newBook")
//	public List<NewBook> newBook() {
//		List<NewBook> arr=new ArrayList<NewBook>();
//		// url에 연결하여 문서를 읽어온다
//		String url="https://www.hanbit.co.kr/store/books/new_book_list.html";
//		String base="https://www.hanbit.co.kr/";
//		try {
//			Document doc=Jsoup.connect(url).get();
////			System.out.println(doc);
//			Elements list=doc.select(".book_tit");
//			for (Element e : list) {
//				Element a=e.firstElementChild();
//				String title=a.text();
//				System.out.println(title);
//				String link=base+a.attr("href");
//				System.out.println(link);
//				arr.add(new NewBook(title,link));
//				
//			}
//		} catch (Exception e) {
//			System.out.println("예외 발생 : "+e.getMessage());
//		}
//		return arr;	//rest controller의 응답은 자동으로 json 형태로 만들어진다.
//	}
	
//	@GetMapping("/newBook")
//	public String newBook() {
//		// url에 연결하여 문서를 읽어온다
//		String url="https://www.hanbit.co.kr/store/books/new_book_list.html";
//		String base="https://www.hanbit.co.kr/";
//		try {
//			Document doc=Jsoup.connect(url).get();
////			System.out.println(doc);
//			Elements list=doc.select(".book_tit");
//			for (Element e : list) {
//				Element a=e.firstElementChild();
//				String title=a.text();
//				System.out.println(title);
//				String link=base+a.attr("href");
//				System.out.println(link);
//			}
//		} catch (Exception e) {
//			System.out.println("예외 발생 : "+e.getMessage());
//		}
//		return "OK";
//	}
}
