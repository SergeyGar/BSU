package com.example.results;

import dao.impl.DaoStudentsImpl;
import dao.impl.DaoSubjectsImpl;
import domain.Student;
import domain.Subject;
import org.postgresql.ds.PGSimpleDataSource;
import realization.Students;
import realization.Subjects;
import realization.impl.StudentsImpl;
import realization.impl.SubjectsImpl;

import java.io.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private Students students;

    private Subjects subjects;

    @Override
    public void init() throws ServletException {
        super.init();
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setDatabaseName("postgres");

        try {
            students = new StudentsImpl(new DaoStudentsImpl(pgSimpleDataSource));

            subjects = new SubjectsImpl(new DaoSubjectsImpl(pgSimpleDataSource));

        } catch (SQLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String page = req.getParameter("page");

        switch (page){
            case "main":{
                if(req.getParameter("add") != null) {
                    int id = students.getNextStudentId();
                    String name = req.getParameter("name");
                    int group = Integer.parseInt(req.getParameter("group"));
                    String[] marks = req.getParameterValues("marks");

                    List<Subject> subjectList = subjects.getAllSubjects();
                    Map<Integer, Integer> map = new HashMap<>();
                    for(int i = 0; i < marks.length; i++){
                        map.put(subjectList.get(i).getId(), Integer.valueOf(marks[i]));
                    }
                    students.addStudent(new Student(id, group, name, map));
                }
                req.getSession().removeAttribute("debtor");
                resp.sendRedirect("main.jsp");
                break;
            }
            case "debtors":{
                req.getSession().setAttribute("debtor", "debtor");
                resp.sendRedirect("main.jsp");
                break;

            }
        }


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("do");

        switch (action){
            case "add":{
                req.getRequestDispatcher("add.jsp").forward(req, resp);
                break;
            }
            case "delete":{
                try {
                    Integer.parseInt(req.getParameter("student"));
                }
                catch (NumberFormatException e){
                    req.getRequestDispatcher("errorPage.jsp").forward(req, resp);
                }
                int idOfStudentToDelete = Integer.parseInt(req.getParameter("student"));
                students.deleteStudent(idOfStudentToDelete);
                resp.sendRedirect("main.jsp");
                break;
            }
            case "marks":{
                int idOfStudentToShowMarks = Integer.parseInt(req.getParameter("studentId"));
                List<Student> studentList = students.getAllStudents();
                for(Student student: studentList){
                    if(student.getId() == idOfStudentToShowMarks){
                        StringBuilder stringBuilder = new StringBuilder();
                        Map<Integer, Integer> marks = student.getMarks();
                        for (Map.Entry<Integer, Integer> pair : marks.entrySet()) {
                            Subject subject = subjects.getById(pair.getKey());
                            stringBuilder.append(subject.getName()).append(": ");
                            stringBuilder.append(pair.getValue());
                            stringBuilder.append("\n");
                        }
                        req.getSession().setAttribute("marks", stringBuilder.toString());
                        break;
                    }
                }
                req.getSession().setAttribute("students", students.getAllStudents());
                resp.sendRedirect("main.jsp");
                break;
            }

        }


    }

    public void destroy() {
    }
}