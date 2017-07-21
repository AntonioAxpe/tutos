package net.codejava.spring;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import net.codejava.spring.dao.SportDAO;
import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.Sport;
import net.codejava.spring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	/**
	 * La implementación del UserDao es inyectado automaticamente por la
	 * anotación @AutoWired
	 */
	@Autowired
	private UserDAO userDao;
	@Autowired
	private SportDAO sportDao;
	List<User> listUsers;
	List<Sport> listSports;

	@RequestMapping(value = "/")
	public ModelAndView handleRequest() {
		listUsers = userDao.list();
		listSports = sportDao.listSport();
		ModelAndView model = new ModelAndView("UserList");
		model.addObject("UserList", listUsers);
		model.addObject("SportList", listSports);
		return model;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser() {
		listSports = sportDao.listSport();

		// Map<String, String> mapSport = new LinkedHashMap<String, String>();
		// for (Sport sport : listSports) {
		// mapSport.put(String.valueOf(sport.getId()), sport.getName());
		// }

		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", new User());
		model.addObject("SportList", listSports);
		model.addObject("formTable", "user");
		return model;
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		listSports = sportDao.listSport();
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.get(userId);
		
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", user);
		model.addObject("formTable", "user");
		
		return model;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
	public ModelAndView saveUser(HttpServletRequest request) {

		User usuario = new User();
		usuario.setId(Integer.parseInt(request.getParameter("id")));
		usuario.setUsername(request.getParameter("username"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setPassword(request.getParameter("password"));
		
		String[] sportss = request.getParameterValues("sports");
		
		Sport sport = null;
		Set<Sport> sportList = new HashSet<Sport>();
		for (int i = 0; i < sportss.length; i++) {
			 sport = sportDao.findSport(Integer.parseInt(sportss[i]));
			 sportList.add(sport);
		}
		
		usuario.setSports(sportList);

		userDao.saveOrUpdate(usuario);
		return new ModelAndView("redirect:/");
	}

	/************************ SPORT ********************************/

	@RequestMapping(value = "/newSport", method = RequestMethod.GET)
	public ModelAndView addSport() {
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("sport", new Sport());
		model.addObject("formTable", "sport");
		return model;
	}

	@RequestMapping(value = "/editSport", method = RequestMethod.GET)
	public ModelAndView editSport(HttpServletRequest request) {
		int idSport = Integer.parseInt(request.getParameter("id"));
		Sport sport = sportDao.findSport(idSport);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("sport", sport);
		model.addObject("formTable", "sport");
		return model;
	}

	@RequestMapping(value = "/saveSport", method = RequestMethod.POST)
	public ModelAndView saveSport(@ModelAttribute Sport sport) {
		sportDao.updateSport(sport);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteSport", method = RequestMethod.GET)
	public ModelAndView deleteSport(HttpServletRequest request) {
		int sportId = Integer.parseInt(request.getParameter("id"));
		sportDao.deleteSport(sportId);
		return new ModelAndView("redirect:/");
	}

}
