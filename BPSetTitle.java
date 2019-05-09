import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BPSetTitle extends LiveText {

	String setNumber = "";
	String setTitle = "";

	public BPSetTitle(String setNum) {

		url = "https://www.brickpicker.com/bpms/set.cfm?set=" + setNumber;
		setNumber = setNum;

	}

	public void run() {
		for (;;) {

			Document doc = null;
			String url = "https://www.brickpicker.com/bpms/set.cfm?set=" + setNumber;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException ioe) {
			}

			Element setName = doc.getElementsByTag("H1").get(1);

			setTitle = setName.text();
			setTitle = setTitle.substring(setTitle.indexOf(':') + 1);

			try {
				Thread.sleep(updateRate);
				System.out.println("Updated");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public String toString() {
		if (setTitle.equals(""))
			return "--";
		return setTitle;
	}

}
