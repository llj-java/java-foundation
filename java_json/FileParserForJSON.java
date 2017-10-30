package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.*;

/**
 * java ��ȡ�����json�ļ�
 * ��ע��
 * ��Ҫ�ֶ�����java-json.jar��
 * 
 * time:2017-10-30 
 * author:llj
 */
public class FileParserForJSON {

	public static void main(String[] args) {
		/**
		 * ��ȡjson�ļ������洢��list�У��������
		 */
		ArrayList<String> jsonResult = null;
		try {
			jsonResult = new ArrayList<String>();//�����洢�ļ���ÿһ��
			//
			BufferedReader br;
			br = new BufferedReader(new FileReader("src/file/json.txt")); //��ȡjson�ļ�
			String name = null;
			//���ж�ȡ�ļ�
			while((name = br.readLine()) != null){
				jsonResult.add(name);
				System.out.println(name);//���ÿһ��
			}
			br.close();//�ر�
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * ��ȡjson�ļ���ȥ��ÿ�пո�ƴ��Ϊ�ַ��������
		 */
		try {
			BufferedReader br;
			String stringResult = "";
			br = new BufferedReader(new FileReader("src/file/json.txt")); //��ȡjson�ļ�
			String name = null;
			//���ж�ȡ�ļ�
			while((name = br.readLine()) != null){
				stringResult += name.trim(); //�ַ������ӣ���ȥ��ǰ��ո�
			}
			System.out.println("string result is " + stringResult);
			br.close();//�ر�
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * ��ȡԭʼjson�ļ������в��������
		 */
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/file/json.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/file/new_json.txt"));
			String s = null, ws = null;
			
			while((s = br.readLine()) != null){
				try {
					JSONObject dataJSON = new JSONObject(s);
					JSONArray features = dataJSON.getJSONArray("features");
					for(int i = 0;i < features.length();i++){
						JSONObject info = features.getJSONObject(i);
						JSONObject properties = info.getJSONObject("properties");
						
						String name = properties.getString("name");
						System.out.println("name is " + name);
						properties.put("NAMEID", "1");
						
						System.out.println("name id is " + properties.getString("NAMEID"));
						properties.remove("ISO");
					}
					ws = dataJSON.toString();
					System.out.println("ws is " + ws);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			bw.write(ws);
			
			bw.flush();
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
