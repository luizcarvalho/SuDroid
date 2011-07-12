package br.com.redrails;
/*
 * Luiz Carvalho
 * @LuizCarvalho
 * luizcarvalho@redrails.com.br
 * www.redrails.com.br
 * http://br.linkedin.com/in/luizkarvalho (recommend me) 
 * more in http://www.redrails.com.br/tags/QoV/
 * */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SuDroid {
	// private static final String TAG = "sudroid";

	public String success_msg = null;
	public String error_msg = null;
	public Boolean success = false;

	public SuDroid(String command) {
		Process proc = run(command);

		try {

			if (proc.waitFor() == 0)
				success = true;

			success_msg = getResult(proc.getInputStream());
			error_msg = getResult(proc.getErrorStream());

		} catch (InterruptedException ex) {
			success = false;
			print("Error in execute command: " + ex.toString());
		} catch (NullPointerException ex) {
			success = false;
			print("Error in execute command:  " + ex.toString());
		}

	}

	public String result() {
		return "";
	}

	public Process run(String command) {
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec("sudo");
			DataOutputStream dataProc = new DataOutputStream(proc.getOutputStream());
			dataProc.writeBytes("exec " + command + "\n");
			dataProc.flush();
		} catch (Exception ex) {
			print(ex.getMessage());
			success = false;
			proc = null;
		}
		return proc;
	}

	private String getResult(InputStream istream) {
		String line = null;
		StringBuffer result = new StringBuffer();
		BufferedReader dataIstream = new BufferedReader(new InputStreamReader(
				istream));

		try {
			while ((line = dataIstream.readLine()) != null) {
				result.append(line + "\n");
			}
			dataIstream.close();
		} catch (Exception ex) {
			success = false;
			print(ex.getMessage());
		}

		if (result != null) {
			return result.toString();
		} else
			return null;

	}

	public void print(String msg) {
		System.out.println(msg);
		// Log.e(TAG, "A Error Occurred: " + e.toString());
	}

}
