package com.blog.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;

import com.blog.Globals;

/**
 * Servlet implementation class RandomImageServlet
 */
public class RandomImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String randString = random();
		session.setAttribute(Globals.RANDOM_LOGIN_KEY,randString);
		render(randString,response.getOutputStream());
	}
	/**
	 * 生成四位的随机数
	 * @return
	 */
	private static String random(){
		return RandomStringUtils.randomNumeric(4);
	}
	
	 /**
     * 根据要求的数字生成图片,背景为白色,字体大小16,字体颜色黑色粗体
     * @param num	要生成的数字
     * @param out	输出流
     * @throws IOException
     */
    private static void render(String num,OutputStream out) throws IOException{
        if(num.getBytes().length > 4)
            throw new IllegalArgumentException("The length of param num cannot exceed 4.");
        int width = 40;
        int height = 15;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);        
        Graphics2D g = (Graphics2D)bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        Font mFont = new Font("Tahoma", Font.PLAIN, 14);
        g.setFont(mFont);
        g.setColor(Color.BLACK);
        g.drawString(num,2,13);
        	ImageIO.write(bi, "png", out);
    }
}
