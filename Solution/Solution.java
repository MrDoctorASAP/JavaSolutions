package HTMLPROC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import HTMLPROC.Element;
import HTMLPROC.Tag;

public class Solution {
	
	public static String[] getContentPage(String target) {
		
		ArrayList<String> tmp = new ArrayList<String>();
		
		String inputLine;
		
		try {
			 
		        URL url = new URL(target);

		        URLConnection conn =  url.openConnection();
		        conn.setRequestProperty(
		            "User-Agent",
		            "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723 Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)");

		        BufferedReader in = new BufferedReader(
		            new InputStreamReader(conn.getInputStream(),"utf-8"));                        
		        
		        while ((inputLine = in.readLine()) != null) {
		            tmp.add(inputLine);
		        }
		        System.out.println("URL Writer : Successfully");
		    }
		
		    catch (Exception e) {
		    	
		        e.printStackTrace();
		        
		    }
		
		return ArrayListToStringArray(tmp);
	}
	
	public static String[] getStringArray(Tag[] tags) {
		
		String[] result = new String[tags.length];
		
		String space;
		
		for(int i = 0; i < tags.length; i++) {
				space = "";
				
				for(int j = 0; j < 20 - tags[i].type.length(); j++)
					space += " ";
				
				result[i] = tags[i].type + space + ":   " + tags[i].content;
			
		}
		
		return result;
		
	}
	
	public static void printStringArray(String[] s) {
		
		for(int i = 0; i < s.length; i++)
			System.out.println( "\"" + s[i].replace("\\", "") + "\",");
		
	}
	
	public static String[] ArrayListToStringArray(ArrayList<String> arrayList) {
		
		String[] result = new String[arrayList.size()];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = arrayList.get(i);
		}
		
		return result;
		
	}
	
	public static class ProgramTimer {
		
		private String nameBreakPoint;
		
		private long start;
		
		private long time;
		
		public ProgramTimer(String nameBreakPoint) {
			start = 0;
			time = 0;
			this.nameBreakPoint = nameBreakPoint;
		}
		
		public void start() {
			start = System.currentTimeMillis();
		}
		
		public void stop() {
			time = System.currentTimeMillis() - start;
		}
		
		public long getTime() {
			return time;
		}
		
		public void print() {
			System.out.println("Time run \"" + nameBreakPoint + "\" : " + time + "ms");
		}
		
		public void printf() {
			long time = this.time;
			long ms = time % 1000;
			long m = time / 60000;
			long s = time / 1000 - m * 60;
			System.out.println("Time run \"" + nameBreakPoint + "\" : " + m + "m " + s + "s " + ms + "ms");
		}
		
		public String getFormatTime() {
			long time = this.time;
			long ms = time % 1000;
			long m = time / 60000;
			long s = time / 1000 - m * 60;
			return m + "m " + s + "s " + ms + "ms";
		}
		
	}
	
	public static ArrayList<Element> addArrayToArrayList(ArrayList<Element> target, ArrayList<Element> elem) {
		
		for(int i = 0; i < elem.size(); i++) {
			target.add(elem.get(i));
		}
		
		return target;
		
	}
	
	public static String[] getStringArray(Element[] elem) {
		
		String[] s = new String[elem.length];
		
		for(int i = 0; i < s.length; i++) {
			
			s[i] = formatLength("" + elem[i].index, 4) + " : " + formatLength(elem[i].type, 20);
			
			for(int j = 0 ; j < elem[i].tags.length; j++) {
				s[i] += " | " + formatLength(elem[i].tags[j], 50);
			}
		}
		
		return s;
		
	}
	
	public static String formatLength(String s, int l) {
		
		int sl = s.length();
		
		for(int i = 0; i < l - sl; i++) {
			s += " ";
		}
		
		return s;
		
	}
	
	public static String[] formatIndex(Element[] elem) {
		String tab = "    ";
		String[] s = new String[elem.length];
		
		for(int i = 0; i < s.length; i++) {
			s[i] = "";
			for(int j = 0; j < elem[i].index; j++)
				s[i] += tab;
			if(!elem[i].type.equals("content"))s[i] += elem[i].type;
			else s[i] += elem[i].tags[0];
		}
		return s;
	}
	
	public static boolean download(String url, String filepath) {
		
		 int buffSize = 1;
		
		 try {
			 	File f = new File(filepath);
			 	if(!f.exists())f.createNewFile();
	            URL connection = new URL(url);
	            HttpURLConnection urlconn;
	            urlconn = (HttpURLConnection) connection.openConnection();
	            urlconn.setRequestMethod("GET");
	            urlconn.connect();
	            InputStream in = null;
	            in = urlconn.getInputStream();
	            OutputStream writer = new FileOutputStream(filepath);
	            byte buffer[] = new byte[buffSize];
	            int c = in.read(buffer);
	            while (c > 0) {
	                writer.write(buffer, 0, c);
	                c = in.read(buffer);
	            }
	            writer.flush();
	            writer.close();
	            in.close();
	            return true;
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return false;
	        }
		
		
		
	}
	
	public static ArrayList<String> addStringArrayToArrayList(ArrayList<String> ls, String[] s){
		
		for(int i = 0; i < s.length; i++) {
			ls.add(s[i]);
		}
		
		return ls;
		
	}
	
	public static String[] getContent(String target, String start,String end) {
		
		ArrayList<String> ls = new ArrayList<String>();
		boolean b;
		
		for(int i = 0; i < target.length(); i++) {
			
			b = true;
			
			int j = 0;
			
			for(j = 0; j < start.length(); j++) {
				
				if(j == target.length() - 1) {
					
					b = false;
					break;
					
				} 
				
				if(target.charAt(i+j) != start.charAt(j)) {
					
					b = false;
					break;
					
				}
				
			}
			
			if(b) {
				boolean g = true;
				
				int h = 0;
				int k = j + i;
				for(; k < target.length(); k++) {
					
					g = true;
					
					for(h = 0; h < end.length(); h++) {
						
						if(k + h == target.length()) {
							g = false;
							break;
						}
						
						if(target.charAt(h+k) != end.charAt(h)) {
							g = false;
							break;
						}
						
					}
					 
					if(g) {
						
						ls.add(target.substring(j + i, k));
						
						break;
						
					}
				
				}
				
			}
			
		}
		
		return ArrayListToStringArray(ls);
		
	}
	
}
