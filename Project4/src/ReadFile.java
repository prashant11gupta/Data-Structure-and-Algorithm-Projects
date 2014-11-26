import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
	private int arraySize;
	private Object[] temp1;
	private LinearProbing<String> lp;

	// Read dictionary.txt and insert words into hash table created in LinearProbing class
	public void readFile(String file_name) {
		lp = new LinearProbing<String>(10);
		
		BufferedReader reader = null;
		try {
			String currentLine;
			reader = new BufferedReader(new FileReader(file_name));
			while ((currentLine = reader.readLine()) != null) {
				lp.insert(currentLine);
			}
			arraySize = lp.getSize();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
// Read words from hash table
	
	public String readDictionary(String x) {
		StringBuffer buffer = new StringBuffer();
		int i = lp.getHashCode(x, arraySize);
		if (i < 0)
			i += arraySize;
		int j = 0;

		temp1 = lp.getArray();

		if(lp.contains(x.toLowerCase()))
			return x;
		
		System.out.println(x+" is misspelled");
		System.out.print("Alternatives : ");
		while (j < arraySize) {
			if (temp1[j] != null) {
				String word = (isNeighbor(x, (String) temp1[j]));
				if (word.isEmpty())
					;
				else 
				{
					buffer.append(word);
					buffer.append(" ");
				}
			}
			j++;
		}

		return buffer.toString();
	}
	
// Returns list of suggested words that are same but have one letter changed
	
	public String isNeighbor(String a, String b) {
		int differ = 0;
		if (a.length() == b.length()) {

			for (int j = 0; j < a.length(); j++) {
				if (a.charAt(j) != b.charAt(j))
					differ++;
			}
		}

		if (differ == 1)
			return b;
		else
			return "";
	}
	
//Read document.txt , matches each word with dictionary loaded into hash table and throw error for
// misspelled words
	
	public void readDocument(String path) throws IOException {
		FileInputStream file = null;
		BufferedReader bufferedReader = null;
		// pattern to extract words from the file
		Pattern pattern = Pattern.compile("\\w+");
		try {
			file = new FileInputStream(path);
			bufferedReader = new BufferedReader(new InputStreamReader(file));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				// extract words from the line
				while (matcher.find()) {
					String word = matcher.group();
					
					if (!lp.contains(word.toLowerCase()))
						System.out.println(readDictionary(word));
					
				}
			}

		} finally {
			// close the resources
			if (bufferedReader != null)
				bufferedReader.close();
			if (file != null)
				file.close();
		}
	}

	public static void main(String[] args) throws IOException {

		String fileName = "F:\\University of Texas at Dallas\\Java Project\\Project4\\src\\dictionary.txt";
		String file_Name = "F:\\University of Texas at Dallas\\Java Project\\Project4\\src\\nosilverbullet.txt";
		ReadFile rd = new ReadFile();

		rd.readFile(fileName);
		rd.readDocument(file_Name);
		
	}
}