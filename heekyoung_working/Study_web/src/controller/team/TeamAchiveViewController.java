package controller.team;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Achive;
import dto.AchivePeople;
import service.team.TeamService;
import service.team.TeamServiceImpl;

@WebServlet("/team/achive/view")
public class TeamAchiveViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TeamService teamService = new TeamServiceImpl();
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Achive achive = new Achive();
		
		//각 목표 조회
		List<Achive> achiveList = teamService.getAchiveList(achive);
		
		request.setAttribute("achive", achive);
		
		request.setAttribute("achiveList", achiveList);
		
				
		request.getRequestDispatcher("/view/team/achiveStatus.jsp").forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글 인코딩
		req.setCharacterEncoding("utf-8");
		
		//요청파라미터
		AchivePeople achivePeople = teamService.getAchivePeopleParam(req, resp);
		
		
		
		teamService.checkSubAchive(achivePeople);
				
		
			
		resp.sendRedirect("/team/achive/view");
	}
	

	
}
