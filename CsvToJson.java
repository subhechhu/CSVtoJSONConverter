package np;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class CsvToJson {
	static String[] keys = { "position", "allele", "reference_base", "accession_id", "platform_labels" };

	public static void main(String[] args) throws IOException {

		File input = new File("/home/simplify360/Desktop/test.csv");
		

		BufferedReader br = null;
		String line = "";
		String splitter = "\n";
		String[] key = { "position", "allele", "reference_base", "accession_id", "platform_labels" };
		try {
			// boolean first = true; // delete
			boolean firstLine = true;

			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject;

			br = new BufferedReader(new FileReader(input));
			while ((line = br.readLine()) != null) {
				String[] val = new String[5];
				int firstpos = 0;
				int i = 0;
				int c = 0;
				if (firstLine) {
					System.out.println("firstline");
					firstLine = false;
				} else {
					// System.out.println("\nline: " + line);
					String[] country = line.split(splitter);
					for (int a = 0; a < country.length; a++) {
						// System.out.println("country: "+country[a]);
						String part = country[0].replaceAll("\\s", " ");
						for (i = 0; i <= part.length(); i++) {
							if (part.charAt(i) == ' ') {
								// System.out.println(part.substring(firstpos, i));
								val[c] = part.substring(firstpos, i);
								c = c + 1;
								firstpos = i;
							} else if (part.charAt(i) == '[') {
								// System.out.println((part.substring(part.indexOf("["), part.length())));
								val[c] = part.substring(part.indexOf("["), part.length());
								break;
							}
						}
					}
					jsonObject = new JSONObject();
					for (int finVal = 0; finVal < val.length; finVal++) {
						jsonObject.put(key[finVal], val[finVal]);
					}
					jsonArray.put(jsonObject);
				}
			}
			System.out.println("jsonArray: " + jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}
}
