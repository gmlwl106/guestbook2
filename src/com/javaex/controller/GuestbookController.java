package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
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
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			
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
			WebUtil.redirect(request, response, "./gbc?action=list");
			
		} else if("deleteForm".equals(action)) {
			
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
			
		} else if("delete".equals(action)) {
			//파라미터 가져오기
			int delNo = Integer.parseInt(request.getParameter("del_no"));
			String delPw = request.getParameter("del_pw");
			
			
			GuestBookDao gbDao = new GuestBookDao();
			
			gbDao.guestDelete(delNo, delPw);
			
			//리다이렉트 list
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=list");
			
			
		} else {
			System.out.println("action 파라미터 없음");
			WebUtil.redirect(request, response, "/guestbook2/gbc?action=list");
		}
		
		
	}

	
	
	
	//post 방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
