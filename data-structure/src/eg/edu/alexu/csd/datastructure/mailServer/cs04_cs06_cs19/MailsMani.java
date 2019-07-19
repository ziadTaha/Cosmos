package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.queue.IQueue;
import eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19.MyLinkedBased;

public class MailsMani {
	@SuppressWarnings("unchecked")
	public JSONObject createMail(IMail mail) {
		JSONObject mailObj = new JSONObject();
		mailObj.put("subject", mail.getSubject());
		mailObj.put("sender", mail.getSender());
		mailObj.put("text", mail.getText());
		mailObj.put("priority", mail.getPriority());
		mailObj.put("starred", mail.isStarred());
		mailObj.put("folder", mail.getFolder());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dateTime = mail.getTime();
		String timeString = dateTime.format(formatter);
		mailObj.put("time", timeString);
		if(mail.getFolder().equals("trash"))
		{
		LocalDateTime trashTime = mail.getTrashedtime();
		String trashTimeString = trashTime.format(formatter);
		mailObj.put("trashtime", trashTimeString);
		}
		JSONArray receArr = new JSONArray();
        while (!mail.getRecivers().isEmpty())
        {
        	receArr.add(mail.getRecivers().dequeue());
        }
        for (int i = 0; i < receArr.size(); i++) {
			mail.getRecivers().enqueue(receArr.get(i));
		}
		mailObj.put("reciviers", receArr);
	    JSONArray attach = new JSONArray();
	    for (int i = 0; i < mail.getAttachmets().size(); i++) {
			attach.add(mail.getAttachmets().get(i));
		}
	    mailObj.put("attachments", attach);
		return mailObj;
	}

	@SuppressWarnings("unchecked")
	public void addToIndex(IMail mail, String path) {
		JSONObject mailObj = new JSONObject();
		mailObj.put("subject", mail.getSubject());
		mailObj.put("sender", mail.getSender());
		mailObj.put("priority", mail.getPriority());
		mailObj.put("folder", mail.getFolder());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dateTime = mail.getTime();
		String timeString = dateTime.format(formatter);
		mailObj.put("time", timeString);
		if(mail.getFolder().equals("trash"))
		{
		LocalDateTime trashTime = mail.getTrashedtime();
		String trashTimeString = trashTime.format(formatter);
		mailObj.put("trashtime", trashTimeString);
		}
		try {
			FileReader fr = new  FileReader(path+"/index.json");
			JSONObject jsonObject= (JSONObject)
					new JSONParser().parse(fr);
			Object obj =jsonObject.get("mails");
			JSONArray mails =(JSONArray )obj;

			mails.add(mailObj);
			FileWriter fw;
				fw = new FileWriter(path+"/index.json");
				fw.write(jsonObject.toString());
				fw.flush();
				fw.close();
				fr.close();
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


	}
	@SuppressWarnings("unchecked")
	public void sendMail(IMail mail ,String des)
	{
		addToIndex(mail, des);
		JSONObject jsonObject = createMail(mail);
		String mailName=mail.getTime().toString().replaceAll(":", ".");
		File file = new File(des+"/"+mailName+"/attachments");
		file.mkdirs();

		FilesManpulator fm = new FilesManpulator();

		for (int i= 0 ;i<mail.getAttachmets().size();i++)
		{
			String ext =fm.getExtention(mail.getAttachmets().get(i).toString());
			fm.copyFiles(mail.getAttachmets().get(i).toString(),
					des+"/"+mailName+"/attachments/"+i+"."+ext);
			mail.getAttachmets().set(i, des+"/"+mailName+"/attachments/"+i+"."+ext);

		}
		 JSONArray attach = new JSONArray();
		    for (int i = 0; i < mail.getAttachmets().size(); i++) {
				attach.add(mail.getAttachmets().get(i));
			}

		jsonObject.put("attachments", attach);

		try {
			FileWriter fw = new FileWriter(des+"/"+mailName+"/"+mailName+".json");
			fw.write(jsonObject.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public IMail getMailByPath(String path)
	{
		IMail mail = new IMail();
		try {
			FileReader fr = new  FileReader(path+".json");
			Object obj = new JSONParser().parse(fr);
			JSONObject jsonObject = (JSONObject) obj;
			mail.setSubject(jsonObject.get("subject").toString());
			mail.setSender(jsonObject.get("sender").toString());
			mail.setStarred((boolean) jsonObject.get("starred"));
			mail.setText(jsonObject.get("text").toString());
			mail.setPriority(Integer.parseInt(  jsonObject.get("priority").toString()));
			mail.setFolder(jsonObject.get("folder").toString());
			String str = jsonObject.get("time").toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			mail.setTime(dateTime);
			if(mail.getFolder().equals("trash"))
			{
				String str2 = jsonObject.get("trashtime").toString();
				LocalDateTime trashdateTime = LocalDateTime.parse(str2, formatter);
				mail.setTrashedtime(trashdateTime);
			}

			ILinkedList attach = new MyLinkedList();
			JSONArray attArr= (JSONArray) jsonObject.get("attachments");
			for(int i=0;i<attArr.size();i++)
			{
				attach.add(attArr.get(i));
			}
			mail.setAttachmets(attach);
			IQueue queue =new MyLinkedBased();
			JSONArray recArr= (JSONArray) jsonObject.get("reciviers");
			for(int i=recArr.size()-1;i>=0;i--)
			{
				queue.enqueue(recArr.get(i));
			}

			mail.setRecivers(queue);
			fr.close();
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
		return mail;
	}
	public String[] getNamesFropmIndex(String path)
	{
		Object obj;
		String[] paths =null;
		try {
			FileReader fr = new  FileReader(path+"/index.json");
			obj = new JSONParser().parse(fr);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("mails");
			paths =new String[jsonArray.size()];
			for(int i=0;i<jsonArray.size();i++)
			{
				JSONObject mail =(JSONObject) jsonArray.get(i);
				String str = mail.get("time").toString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
				String mailName=dateTime.toString().replaceAll(":", ".");
				paths[i]=mailName;
			}
			fr.close();
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
		return paths;

	}
	@SuppressWarnings("unchecked")
	public void deleteFromIndex(IMail mail,String path)
	{

		try {
			FileReader fr = new  FileReader(path+"/index.json");
			 Object obj = new JSONParser().parse(fr);
			 JSONObject jsonObject = (JSONObject) obj;
			 JSONArray jsonArray = (JSONArray) jsonObject.get("mails");
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			 LocalDateTime dateTime = mail.getTime();
			 String timeString = dateTime.format(formatter);
			 for(int i=0;i<jsonArray.size();i++)
			 {
				 JSONObject mailObj = (JSONObject) jsonArray.get(i);

				 if(timeString.equals(mailObj.get("time")))
				 {
					 jsonArray.remove(i);
					 break;
				 }

			 }
			 jsonObject.put("mails", jsonArray);
			 FileWriter fw;
				fw = new FileWriter(path+"/index.json");
				fw.write(jsonObject.toString());
				fw.flush();
				fw.close();
				fr.close();
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

	}
    public boolean existInInFolder(IMail mail,String path)
    {
    	try {
			FileReader fr = new  FileReader(path+"/index.json");
			 Object obj = new JSONParser().parse(fr);
			 JSONObject jsonObject = (JSONObject) obj;
			 JSONArray jsonArray = (JSONArray) jsonObject.get("mails");
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			 LocalDateTime dateTime = mail.getTime();
			 String timeString = dateTime.format(formatter);
			 for(int i=0;i<jsonArray.size();i++)
			 {
				 JSONObject mailObj = (JSONObject) jsonArray.get(i);

				 if(timeString.equals(mailObj.get("time")))
				 {
					 fr.close();
					 return true ;
				 }

			 }
			 fr.close();

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
    	return false;
    }
}
