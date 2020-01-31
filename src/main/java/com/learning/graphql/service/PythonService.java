package com.learning.graphql.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.learning.graphql.dto.OutputDTO;

@Service
public class PythonService {
	
	public void saveFile(String code) {
		try {
			FileWriter fw = new FileWriter("Test.py");
			
			fw.write(code);
			fw.close();
		
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Success...");
	}


	

	public OutputDTO compile(String filepath, String fileName) throws IOException {
		Process p = Runtime.getRuntime().exec("python Test.py");

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
