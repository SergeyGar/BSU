package com.example.web7;

import java.io.*;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String page = req.getParameter("page");

        switch (page) {
            case "dough": {
                Cake currentCake;
                if(req.getParameter("edit") != null) {
                    currentCake = (Cake) req.getSession().getAttribute("cake");
                }
                else{
                    currentCake = new Cake();
                }
                req.getSession().setAttribute("cake", currentCake);
                req.getRequestDispatcher("dough.jsp").forward(req, resp);
                break;
            }
            case "cream": {
                Cake currentCake;
                if (req.getSession().getAttribute("cake") != null) {
                    currentCake = (Cake) req.getSession().getAttribute("cake");
                } else {
                    currentCake = new Cake();
                }
                if (req.getParameter("edit") == null) {
                    String inputData = req.getParameter("dough");
                    currentCake.setDough(inputData);
                    req.getSession().setAttribute("cake", currentCake);
                }
                req.getRequestDispatcher("cream.jsp").forward(req, resp);
                break;
            }
            case "topping": {
                Cake currentCake = (Cake) req.getSession().getAttribute("cake");
                if (req.getParameter("edit") == null) {
                    String inputData = req.getParameter("cream");
                    currentCake.setCream(inputData);
                    req.getSession().setAttribute("cake", currentCake);
                }
                req.getRequestDispatcher("topping.jsp").forward(req, resp);
                break;
            }
            case "result": {
                Cake currentCake = (Cake) req.getSession().getAttribute("cake");

                if (req.getParameter("edit") == null) {
                    String inputData = req.getParameter("topping");
                    currentCake.setTopping(inputData);
                    req.getSession().setAttribute("cake", currentCake);
                }
                req.getRequestDispatcher("result.jsp").forward(req, resp);
                break;
            }
            case "":


                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        int x = 0;

    }

    public void destroy() {
    }
}