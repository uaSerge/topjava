package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
        log.debug("redirect to meals");
        request.setAttribute("meal", MealDaoImpl.getInstance().getListMealWithExceeded());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);}
        if (action != null && action.equals("delete")) {
            String id = request.getParameter("id");
            MealDaoImpl.getInstance().removeMeal(Integer.parseInt(id));
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String description = req.getParameter("description");
        String calories = req.getParameter("calories");
        Meal meal = new Meal(LocalDateTime.parse(date), description, Integer.parseInt(calories));
        MealDaoImpl mealDao = MealDaoImpl.getInstance();
        mealDao.addMeal(meal);
        doGet(req, resp);
    }
}
