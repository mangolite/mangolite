package org.spamjs.mangolite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.common.io.ByteStreams;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceWriter.
 */
public class ResourceWriter {

	/** The response. */
	HttpServletResponse response;
	
	/** The print writer. */
	PrintWriter printWriter;
	
	/** The output stream. */
	OutputStream outputStream;

	/**
	 * Gets the output stream.
	 *
	 * @return the output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public OutputStream getOutputStream() throws IOException {
		if (this.outputStream == null) {
			this.outputStream = this.response.getOutputStream();
		}
		return this.outputStream;
	}

	/**
	 * Instantiates a new resource writer.
	 *
	 * @param response the response
	 */
	public ResourceWriter(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * Gets the prints the writer.
	 *
	 * @return the prints the writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PrintWriter getPrintWriter() throws IOException {
		if (this.printWriter == null) {
			this.printWriter = this.response.getWriter();
		}
		return this.printWriter;
	}

	/**
	 * Write.
	 *
	 * @param res the res
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(String res) throws IOException {
		getPrintWriter().write(res);
	}

	/**
	 * Write.
	 *
	 * @param inputStream the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(InputStream inputStream) throws IOException {
		if(inputStream==null){
			return;
		}
		ByteStreams.copy(inputStream, getOutputStream());
	}

}
