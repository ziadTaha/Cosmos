package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MailsMani;

public class IFolder {
	private String name;
	private IContact contact;
    private ILinkedList mails;
    private MailsMani mailsMani;
    public IFolder(String name,IContact contact) {
    	this.name =name;
    	this.contact=contact;
    	mailsMani = new MailsMani();

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ILinkedList getMails() {
		return mails;
	}
	public void setMails(ILinkedList mails) {
		this.mails = mails;
	}
	public IContact getContact() {
		return contact;
	}
	public void createDirectory()
	{
		String path ="accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+name;
		File file = new File(path);
		file.mkdirs();
	}
	@SuppressWarnings("unchecked")
	public void makeIndex()
	{
		    String path="accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+name;
            JSONObject jsonObject = new JSONObject();
            JSONArray mails = new JSONArray();
            jsonObject.put("mails", mails);
            FileWriter fw;
			try {
				fw = new FileWriter(path+"/index.json");
				fw.write(jsonObject.toString());
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
   public IMail[] getAllMails()
   {
	   String path="accounts/"+contact.getName()+"/"+contact.getEmail()+"/"+name;
	   String[] names=mailsMani.getNamesFropmIndex(path);
	   IMail[] allMails= new IMail[names.length];
	   for(int i=0;i<allMails.length;i++)
	   {

		   allMails[i]=mailsMani.getMailByPath(path+"/"+names[i]+"/"+names[i]);
	   }
	   return allMails;
   }


}
