package ua.com.alevel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class HelloServlet extends HttpServlet {

//    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        try(PrintWriter writer = resp.getWriter()) {
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<h1>Hello Servlet</h1>");
            writer.write("<table>");
            writer.write("<tr>");
            writer.write("<th>Id</th>");
            writer.write("<th>Name</th>");
            writer.write("<th>Age</th>");
            writer.write("</tr>");
//            List<User> users = userDao.findAll();
            for (User user : getRandomList()) {
                writer.write("<tr>");
                writer.write("<td>" + user.getId() + "</td>");
                writer.write("<td>" + user.getName() + "</td>");
                writer.write("<td>" + user.getAge() + "</td>");
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<User> getRandomList() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setAge(new Random().nextInt(20, 40));
            user.setName("name" + i);
            users.add(user);
        }
        return users;
    }

    static class User {
        private String id;
        private String name;
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
