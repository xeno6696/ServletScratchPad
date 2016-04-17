package com.matt.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class FileParser {

	public List<FileContentRef> readContent(InputStream stream, String fileName) throws IOException {
		List<FileContentRef> fileContent = Lists.newArrayList();
		
		BufferedReader reader = null;
		try  {
			reader = new BufferedReader(new InputStreamReader(stream));
			int lineIndex = 1;
			String line = reader.readLine();
			while (line != null) {
				if (!Strings.isNullOrEmpty(line)) {
					fileContent.add(new FileContentRef(lineIndex, line, fileName));
				}
				line = reader.readLine();
				lineIndex ++;
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Logger.getLogger(FileParser.class.getName()).log(Level.INFO, "Failed to close Reader for file: " + fileName, e);
				}
			}
		}
		
		
		return fileContent;
	}
	
	
	public List<FileContentRef> readContent(InputStream stream, String delineation , String fileName) throws IOException {
		List<FileContentRef> fileContent = Lists.newArrayList();
		
		BufferedReader reader = null;
		try  {
			reader = new BufferedReader(new InputStreamReader(stream));
			StringBuffer content = new StringBuffer();
			int lineIndex = 1;
			int startIndex = -1;
			String line = reader.readLine();
			while (line != null) {
				if (startIndex == -1) {
					startIndex = lineIndex;
				}
				if (line.equals(delineation)) {
					String composedContent = content.toString();
					if (!Strings.isNullOrEmpty(composedContent)) {
						fileContent.add(new FileContentRef(startIndex, composedContent, fileName));
					}
					startIndex = -1;
				} else {
					content.append(line);
				}
				line = reader.readLine();
				lineIndex ++;
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Logger.getLogger(FileParser.class.getName()).log(Level.INFO, "Failed to close Reader for file: " + fileName, e);
				}
			}
		}
		
		
		return fileContent;
	}
}
