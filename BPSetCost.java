import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BPSetCost extends LiveText {

	String setNumber;
	String setCost = "";

	public BPSetCost(String setNum) {

		url = "https://www.brickpicker.com/bpms/set.cfm?set=" + setNumber;
		setNumber = setNum;

	}

	public void run() {

		Document doc = null;
		String url = "https://www.brickpicker.com/bpms/set.cfm?set=" + setNumber;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException ioe) {
		}
		Elements texts = doc.getElementsByTag("TR");
		for (Element e : texts) {
			if (e.child(1).text().indexOf('$') != -1) {
				setCost = e.child(1).text();
				setCost = '$' + setCost.substring(setCost.indexOf('$') + 2, setCost.indexOf('(') - 1);
				// System.out.println(setCost);
				break;
			}
		}

		try {
			Thread.sleep(updateRate);
			System.out.println("Updated");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public String toString() {
		if (setCost.equals(""))
			return "--";
		return setCost;
	}

}
