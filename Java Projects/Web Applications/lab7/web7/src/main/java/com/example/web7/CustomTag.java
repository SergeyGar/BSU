package com.example.web7;


import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CustomTag extends SimpleTagSupport {

//    private String dough;
//
//    private String cream;
//
//    private String topping;

    private Cake cake;

    private String errorPage;

//    public void setDough(String dough) {
//        this.dough = dough;
//    }
//
//    public void setCream(String cream) {
//        this.cream = cream;
//    }
//
//    public void setTopping(String topping) {
//        this.topping = topping;
//    }

    public void setCake(Cake cake) { this.cake = cake;}

    public void setErrorPage(String errorPage) {this.errorPage = errorPage;}

    @Override
    public void doTag() throws JspException, IOException {
//        String stringToPrint = "<h1>Your cake!</h1>" +
//                "<p>Dough: " + dough + "</p>" +
//                "<p>Cream: " + cream + "</p>" +
//                "<p>Topping: " + topping + "</p>";

        if(cake.getDough().equals("") ||
                cake.getCream().equals("") ||
                cake.getTopping().equals("")){
            try {
                ((PageContext) getJspContext()).include(errorPage);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        String stringToPrint =
                "<p>Dough: " + cake.getDough() + "</p>" +
                "<p>Cream: " + cake.getCream() + "</p>" +
                "<p>Topping: " + cake.getTopping() + "</p>";

        JspWriter out = getJspContext().getOut();
        getJspBody().invoke(out);
        out.println(stringToPrint);
    }

}
