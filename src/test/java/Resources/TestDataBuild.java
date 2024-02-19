package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.MiniLoc;

public class TestDataBuild {

	public AddPlace addPlacePayload(String Name, String Language, String Address) {

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(Address);
		p.setLanguage(Language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(Name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		MiniLoc l = new MiniLoc();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;

	}

}
