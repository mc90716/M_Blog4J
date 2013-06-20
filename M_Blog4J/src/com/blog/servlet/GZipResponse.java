/*
 * Copyright 2003 Jayson Falkner (jayson@jspinsider.com)
 * This code is from "Servlets and JavaServer pages; the J2EE Web Tier",
 * http://www.jspbook.com. You may freely use the code both commercially
 * and non-commercially. If you like the code, please pick up a copy of
 * the book and help support the authors, development of more free code,
 * and the JSP/Servlet/J2EE community.
 */
package com.blog.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/**
 * GZIPµÄResponse·â×°
 * 
 * @author Winter Lau
 */
public class GZipResponse extends HttpServletResponseWrapper{
	private GZipStream stream;
	private PrintWriter writer;
	public GZipResponse(HttpServletResponse response)throws IOException{
		super(response);
		stream=new GZipStream(response.getOutputStream());
	}

	@Override
	public ServletOutputStream getOutputStream()throws IOException{
		return stream;
	}

	@Override
	public PrintWriter getWriter()throws IOException{
		if (writer ==null) {
			writer =new PrintWriter(new OutputStreamWriter(
					getOutputStream(), getCharacterEncoding()));
		}
		return writer;
	}

	public void flush()throws IOException{
		if (writer !=null) {
			writer.flush();
		}
		stream.finish();
	}

}