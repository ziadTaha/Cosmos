package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;

public class AccountsMani {
	@SuppressWarnings("unchecked")
	public void createFirstAccount(IContact contact)
	{
		JSONObject object = new JSONObject();
		JSONArray accounts = new JSONArray();
		JSONObject account =new JSONObject();
		account.put("name", contact.getName());
		account.put("email", contact.getEmail());
		account.put("password", contact.getPassword());
		accounts.add(account);
		object.put("accounts", accounts);
		FileWriter fw;
		try {
			fw = new FileWriter("accounts/accounts.json");
			fw.write(object.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		creatAcountFolder(contact);

	}
	public void creatAcountFolder(IContact contact)
	{
		createDirectory("accounts/"+contact.getName()+"/"+contact.getEmail());
		IFolder inbox = new IFolder("inbox", contact);
		inbox.createDirectory();
		inbox.makeIndex();
		IFolder sent= new IFolder("sent", contact);
		sent.createDirectory();
		sent.makeIndex();
		IFolder starred = new IFolder("starred", contact);
		starred.createDirectory();
		starred.makeIndex();
		IFolder trash = new IFolder("trash", contact);
		trash.createDirectory();
		trash.makeIndex();
		IFolder draft = new IFolder("draft", contact);
		draft.createDirectory();
		draft.makeIndex();


	}
	public void createDirectory(String path)
	{
		File file = new File(path);
		file.mkdirs();
	}
	@SuppressWarnings("unchecked")
	public void createAccountOrdinary(IContact contact)
	{
		try {
			FileReader fr = new FileReader("accounts/accounts.json");
			JSONObject jsonObject= (JSONObject)	new JSONParser().parse(fr);
			Object obj =jsonObject.get("accounts");
			JSONArray accounts = (JSONArray) obj;
			JSONObject account =new JSONObject();
			account.put("name", contact.getName());
			account.put("email", contact.getEmail());
			account.put("password", contact.getPassword());
			accounts.add(account);
			jsonObject.put("accounts", accounts);
			FileWriter fw;
			try {
				fw = new FileWriter("accounts/accounts.json");
				fw.write(jsonObject.toString());
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fr.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		creatAcountFolder(contact);
	}



}
