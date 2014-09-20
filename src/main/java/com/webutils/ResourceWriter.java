package com.webutils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

public class ResourceWriter {

	HttpServletResponse response;
	PrintWriter printWriter;
	OutputStream outputStream;

	public OutputStream getOutputStream() throws IOException {
		if (this.outputStream == null) {
			this.outputStream = this.response.getOutputStream();
		}
		return this.outputStream;
	}

	public ResourceWriter(HttpServletResponse response) {
		this.response = response;
	}

	public PrintWriter getPrintWriter() throws IOException {
		if (this.printWriter == null) {
			this.printWriter = this.response.getWriter();
		}
		return this.printWriter;
	}

	public void write(String res) throws IOException {
		getPrintWriter().write(res);
	}

	public void write(InputStream inputStream) throws IOException {
		ByteStreams.copy(inputStream, getOutputStream());
	}

}
