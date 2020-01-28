

public static String[] ReadFile(String filePath) {
    
		ArrayList<String> list = new ArrayList<String>();
		String[] content = null;
		FileReader fr = null;
		BufferedReader br = null;
		
    try {
			
      fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
      while(line != null) {
				list.add(line);
				line = br.readLine();
			}
			
		}catch(Exception e) {
			return null;
		}
		
    content = new String[list.size()];
		
    for(int i = 0; i < content.length; i++) {
			content[i] = list.get(i);
		}
		
    return content;
}
  
  
