package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
       
    //생성자-디폴트

	
	
	//get 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post 방식일때 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		
		//action 파라미터 꺼내기
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) { //리스트
			//데이터 가져오기
			GuestBookDao gbDao = new GuestBookDao();
			List<GuestBookVo> gbList = gbDao.getGuestList();
			
			//request에 데이터 추가
			request.setAttribute("gbList", gbList);
			
			//데이터 + html -> jsp
			RequestDispatcher rd = request.getRequestDispatcher("./addList.jsp");
			rd.forward(request, response);
			
		} else if("write".equals(action)) { //글 추가하기
			//파라미터 가져오기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			String date = request.getParameter("reg_date");
			
			//guestInsert로 DB에 추가
			GuestBookDao gbDao = new GuestBookDao();
			int count  = gbDao.guestInsert(new GuestBookVo(name, password, content, date));
			System.out.println(count);
			
			//리다이렉트 list
			response.sendRedirect("./gbc?action=list");
			
		} else if("delete".equals(action)) {
			//파라미터 가져오기
			int delNo = Integer.parseInt(request.getParameter("del_no"));
			String delPw = request.getParameter("del_pw");
			
			
			GuestBookDao gbDao = new GuestBookDao();
			GuestBookVo guest = gbDao.getGuest(delNo);
			
			//입력한 비밀번호와 같으면 삭제
			if(guest.getPassword().equals(delPw)) {
				gbDao.guestDelete(delNo);
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
			
			//리다이렉트 list
			response.sendRedirect("./gbc?action=list");
			
			
		} else {
			System.out.println("action 파라미터 없음");
		}
		
		
	}

	
	
	
	//post 방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
