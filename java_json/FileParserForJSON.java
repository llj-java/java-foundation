package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.*;

/**
 * java 读取和输出json文件
 * 备注：
 * 需要手动引入java-json.jar包
 * 
 * time:2017-10-30 
 * author:llj
 */
public class FileParserForJSON {

	public static void main(String[] args) {
		/**
		 * 读取json文件，并存储到list中，按行输出
		 */
		ArrayList<String> jsonResult = null;
		try {
			jsonResult = new ArrayList<String>();//链表，存储文件的每一行
			//
			BufferedReader br;
			br = new BufferedReader(new FileReader("src/file/json.txt")); //读取json文件
			String name = null;
			//按行读取文件
			while((name = br.readLine()) != null){
				jsonResult.add(name);
				System.out.println(name);//输出每一行
			}
			br.close();//关闭
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * 读取json文件，去除每行空格，拼接为字符串，输出
		 */
		try {
			BufferedReader br;
			String stringResult = "";
			br = new BufferedReader(new FileReader("src/file/json.txt")); //读取json文件
			String name = null;
			//按行读取文件
			while((name = br.readLine()) != null){
				stringResult += name.trim(); //字符串连接，并去除前后空格
			}
			System.out.println("string result is " + stringResult);
			br.close();//关闭
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 读取原始json文件并进行操作和输出
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
