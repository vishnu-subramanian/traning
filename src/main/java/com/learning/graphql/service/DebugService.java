package com.learning.graphql.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.graphql.dto.OutputDTO;

@Service
public class DebugService {

	public void saveFile(String code) {
		try {
			FileWriter fw = new FileWriter("Test.java");
			
			fw.write(code);
			fw.close();
			File f = new File("Test.class");
			deleteFile(f);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Success...");
	}

	public void deleteFile(File file) {
		try {
			
			file.delete();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Success...");
	}
	

	public OutputDTO compile(String filepath, String fileName) throws IOException {
		Process p = Runtime.getRuntime().exec("javac Test.java");

		String error = getError(p);
		
		String output = getOutput(p);

		OutputDTO dto = new OutputDTO();
		dto.setOutput(output);
		dto.setError(error);
		return dto;
	}

	public OutputDTO run(String classname) throws IOException {
		Process p = Runtime.getRuntime().exec("java Test");

		String error = getError(p);
		
		String output = getOutput(p);

		OutputDTO dto = new OutputDTO();
		dto.setOutput(output);
		dto.setError(error);
		return dto;
	}

	public String getOutput(Process p) throws IOException {
		String s = null;
		String output = "";
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		System.out.println("Here is the standard output of the command:\n");
		while ((s = stdInput.readLine()) != null) {
			output = output + s + "\n";
		}
		return output;
	}

	public String getError(Process p) throws IOException {
		String s = null;
		String error = "";
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		System.out.println("Here is the standard output of the command:\n");
		while ((s = stdError.readLine()) != null) {
			error = error + s + "\n";
		}
		return error;
	}

}
