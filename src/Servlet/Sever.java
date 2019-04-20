package Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
/**
 * Servlet 学习示例
 * <p>
 * Servlet 生命周期可被定义为从创建直到毁灭的整个过程。以下是 Servlet 遵循的过程：
 * Servlet 通过调用 init () 方法进行初始化。
 * Servlet 调用 service() 方法来处理客户端的请求。
 * Servlet 通过调用 destroy() 方法终止（结束）。
 * 最后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。
 *
 * @author qiaoger@126.com
 */

// 扩展 HttpServlet 类
public class Sever extends HttpServlet {

    private String message;
    //初始化
    public void init() throws ServletException {
        // 执行必需的初始化
        super.init();
        System.out.println("HelloServlet 初始化");
        message = "bro,door is going to be open!";
    }

    /**
     *
     * service() 方法是执行实际任务的主要方法。
     * Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，
     * 并把格式化的响应写回给客户端。
     *
     * service() 方法由容器调用，service 方法在适当的时候调用
     * doGet、doPost、doPut、doDelete 等方法。所以，您不用对 service() 方法做任何动作，
     * 您只需要根据来自客户端的请求类型来重写 doGet() 或 doPost() 即可。
     * doGet() 和 doPost() 方法是每次服务请求中最常用的方法。
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */


    public void doPost(HttpServletRequest req, HttpServletResponse res){
        String name= req.getParameter("name");
        String pw= req.getParameter("pw");
        System.out.println(req.getParameter("name"));

        System.out.println(req.getParameter("pw"));
        boolean flag=false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:/1.csv"));//换成你的文件名
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String tname = item[0];//name
                if(name.equals(tname)){

                    String tpw = item[1];//password
                    System.out.println(tname+" "+tpw);
                    if(pw.equals(tpw)){
                        flag=true;//账号密码正确
                        System.out.println("通过");

                    }
                    else{
                        //密码错误
                        System.out.println("密码错误");

                        break;
                    }
                }

                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.setContentType("text/html");
        // 业务处理
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(flag){
        out.println("<h1>" + message + " " + name +"</h1>");}
        else{

            out.println("<h1>" + "password is wrong or name not exists" +"</h1>");
        }


    }




    //销毁的时候执行
    public void destroy() {
        super.destroy();
        System.out.println("HelloServlet 销毁啦");
    }
}
