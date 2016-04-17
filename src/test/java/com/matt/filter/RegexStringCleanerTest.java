package com.matt.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.matt.filters.RegexStringCleaner;

@RunWith(Parameterized.class)
public class RegexStringCleanerTest {

	@Parameters(name = "{0}")
	public static Collection<Object[]> buildTest() throws IOException {
		Collection<Object[]> params = Lists.newArrayList();
		FileParser parser = new FileParser();
		Map<String, String> fileDelineatorMap = Maps.newHashMap();
		/*
		 * 
		File dir = new File("C:\\Users\\Jeremiah\\Desktop\\SecLists-master\\SecLists-master\\Fuzzing");
		for (File file :dir.listFiles()) {
			System.out.println(String.format("fileDelineatorMap.put(\"/%s\", null);", file.getName()));
		}*/
		
		
		fileDelineatorMap.put("/fuzzing/alphanum_case.txt", null);
	/*	fileDelineatorMap.put("/fuzzing/alphanum_case_extra.txt", null);
		fileDelineatorMap.put("/fuzzing/char.txt", null);
		fileDelineatorMap.put("/fuzzing/COMMIX_INJECT_HERE.txt", null);
		fileDelineatorMap.put("/fuzzing/doble_uri_hex.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_DB2Enumeration.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_GenericBlind.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_Metacharacters.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_MSSQL.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_MSSQLEnumeration.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_MYSQL.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_MySQL_ReadLocalFiles.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_MySQL_SQLi_LoginBypass.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_Oracle.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_PostgresEnumeration.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_UnixAttacks.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_URIXSS.txt", null);
		fileDelineatorMap.put("/fuzzing/FUZZDB_WindowsAattacks.txt", null);
		fileDelineatorMap.put("/fuzzing/Generic_SQLi", null);
		fileDelineatorMap.put("/fuzzing/JHADDIX_FORMATSTRING", null);
		fileDelineatorMap.put("/fuzzing/JHADDIX_HTML5sec_Injections.txt", null);
		fileDelineatorMap.put("/fuzzing/JHADDIX_LFI.txt", null);
		fileDelineatorMap.put("/fuzzing/JHADDIX_SSI_Injection.txt", null);
		fileDelineatorMap.put("/fuzzing/JHADDIX_XSS.txt", null);
		fileDelineatorMap.put("/fuzzing/JHADDIX_XSS_WITH_CONTEXT.txt", null);
		fileDelineatorMap.put("/fuzzing/JSON_Fuzzing.txt", null);
		fileDelineatorMap.put("/fuzzing/LDAP_FUZZ.txt", null);
		fileDelineatorMap.put("/fuzzing/MarioXSSVectors.txt", null);
		fileDelineatorMap.put("/fuzzing/RSNAKE_XSS.txt", null);
		fileDelineatorMap.put("/fuzzing/SKULLSECURITY_FuzzingStrings.txt", null);
		fileDelineatorMap.put("/fuzzing/special_chars.txt", null);
		fileDelineatorMap.put("/fuzzing/test_ext.txt", null);
		fileDelineatorMap.put("/fuzzing/unicode.txt", null);
		fileDelineatorMap.put("/fuzzing/uri_hex.txt", null);
		fileDelineatorMap.put("/fuzzing/XML_FUZZ", null);
		fileDelineatorMap.put("/fuzzing/XXE_Fuzzing.txt", null);*/
		
		

		for (String fileName : fileDelineatorMap.keySet()) {
			InputStream stream = RegexStringCleaner.class.getResourceAsStream(fileName);
			String delineator = fileDelineatorMap.get(fileName);
			if (delineator == null) {
				for (FileContentRef contentRef : parser.readContent(stream, fileName)) {
					params.add(new Object[]{String.format("%s - Line (%s)", contentRef.fileName, contentRef.startLine), contentRef.content});
				}	
			} else {
				for (FileContentRef contentRef : parser.readContent(stream, delineator, fileName)) {
					params.add(new Object[]{String.format("%s - Line (%s)", contentRef.fileName, contentRef.startLine), contentRef.content});
				}
			}
			stream.close();
		}

		return params;
	}
	
	private RegexStringCleaner cleaner = new RegexStringCleaner();
	private String testString;
	
	public RegexStringCleanerTest(String testName, String testTarget) {
	 this.testString = testTarget;
	}
	
	@Test
	public void perform() {
		String processed = cleaner.apply(testString);
		//Per mseil if data in == data out then no worky.
		Assert.assertNotEquals(String.format("MISMATCH %s == %s", processed, testString),processed,  testString);
	}
}
