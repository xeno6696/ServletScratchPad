package com.matt.filter;

public class FileContentRef {

	public final int startLine;
	public final String fileName;
	public final String content;
	
	public FileContentRef(int line, String refContent, String refFile) {
		this.startLine = line;
		this.fileName = refFile;
		this.content = refContent;
	}
}
