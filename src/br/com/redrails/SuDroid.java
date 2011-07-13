package br.com.redrails;
/*
 * Luiz Carvalho
 * @LuizCarvalho
 * luizcarvalho@redrails.com.br
 * www.redrails.com.br
 * http://br.linkedin.com/in/luizkarvalho (recommend me) 
 * more in http://www.redrails.com.br/tags/QoV/
 * 
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log; 

public class SuDroid {
	private static final String SU = "su";
	public String success_msg = null;
	public String error_msg = null;
	public String exception = null;
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
			showException("SuDroid Exeception:  " + ex.toString());
		} catch (NullPointerException ex) {
			success = false;
			showException("SuDroid Exeception:  " + ex.toString());
		}

	}

	public String result() {
		if(success || !success_msg.isEmpty()){
			return success_msg;
		}		
		return error_msg;
	}

	public Process run(String command) {
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec(SU);
			DataOutputStream dataProc = new DataOutputStream(proc.getOutputStream());
			dataProc.writeBytes("exec " + command + "\n");
			dataProc.flush();
		} catch (Exception ex) {
			showException(ex.getMessage());
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
			showException(ex.getMessage());
		}

		if (result != null) {
			return result.toString();
		} else
			return null;

	}

	public void showException(String msg) {
		exception = exception +"\n"+ msg;
		//System.out.println(msg); // IF NOT ANDROID
		Log.e("SuDroid: ", "Le Exception: " + msg);
	}

}
