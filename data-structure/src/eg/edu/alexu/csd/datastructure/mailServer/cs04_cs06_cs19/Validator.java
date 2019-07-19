package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.mailServer.IContact;

public class Validator {
	public boolean validateOnCreation(IContact contact) {
		try {
			Object obj = new JSONParser().parse(new FileReader("accounts/accounts.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
			String[] contacts = new String[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				Object element = jsonArray.get(i);
				JSONObject account = (JSONObject) element;
				String contactObj = account.get("email").toString();
				contacts[i] = contactObj;
			}
			for (int i = 0; i < contacts.length; i++) {
				if (contact.getEmail().equals(contacts[i])) {
					return false;
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public IContact validateOnsignIN(String email, String password) {
		try {
			Object obj = new JSONParser().parse(new FileReader("accounts/accounts.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
			IContact[] contacts = new IContact[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				Object element = jsonArray.get(i);
				JSONObject account = (JSONObject) element;
				IContact contactObj = new IContact(account.get("name").toString(), account.get("email").toString(),
						account.get("password").toString());
				contacts[i] = contactObj;
			}
			for (int i = 0; i < contacts.length; i++) {
				if (email.equals(contacts[i].getEmail())) {
					if (password.equals(contacts[i].getPassword())) {
						return contacts[i];
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String validateOnsending(String email) {
		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader("accounts/accounts.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
			for (int i = 0; i < jsonArray.size(); i++) {
				Object element = jsonArray.get(i);
				JSONObject account = (JSONObject) element;
				if (email.equals(account.get("email").toString())) {
					return account.get("name").toString() ;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
