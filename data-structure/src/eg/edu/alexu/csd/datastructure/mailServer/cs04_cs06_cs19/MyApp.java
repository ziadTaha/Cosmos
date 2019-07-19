package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.NameNotFoundException;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19.MyLinkedBased;
/**
 *
 * @author Z.A.Z
 *
 */
public class MyApp  implements IApp {
	private AccountsMani accountsMani;
	private MailsMani mailsMani;
	private Validator validator;
	private IContact current;
	private ILinkedList mails;
	private IFolder[] folders;
	private FilesManpulator fm;
	public MyApp() {
		accountsMani= new AccountsMani();
		validator = new Validator();
		mailsMani = new MailsMani();
		mails= new MyLinkedList();
		fm = new FilesManpulator();
	}

	@Override
	public boolean signin(final String email, final  String password) {
		current=validator.validateOnsignIN(email, password);
		if(current!=null)
		{
			checkTrash();
			folders= getAllFolders();
			return true;
		}
		return false;
	}

	@Override
	public boolean signup(final IContact contact) {
		File file =new File("accounts");
		if(!file.exists()||!file.isDirectory())
		{
			file.mkdir();
			accountsMani.createFirstAccount(contact);
			return true;

		}
		else
		{
             if(validator.validateOnCreation(contact))
             {
            	 accountsMani.createAccountOrdinary(contact);
            	 return true;
             }
		}
		System.out.println("error taken!");
		return false;
	}

	@Override
	public void setViewingOptions(final IFolder folder,
			final IFilter filter, final ISort sort) {

		if(folder==null)
		{
			folders=getAllFolders();
			mails= new MyLinkedList();
			for(int i=0 ;i<folders.length;i++)
			{

				mails=apeandLists(mails,arrayToLinked(folders[i].getAllMails()));
			}
		}
		else
		{
			mails=arrayToLinked(folder.getAllMails());
		}
		if(mails.size()!=0)
        {
		IMail[] temp = linkedToArray(mails);

		sort.sort(temp);

		mails=arrayToLinked(temp);
		if(filter!=null)
		{
			temp = linkedToArray(mails);
			filter.filter(temp);
			mails= arrayToLinked(temp);
		}
        }


	}

	@Override
	public IMail[] listEmails(final int page) {
		if(mails.size()<(page-1)*10||mails.size()==0)
		{

			return linkedToArray(new MyLinkedList());
		}
		else if(mails.size()<page*10)
		{
			return linkedToArray(mails.sublist(((page-1)*10), mails.size()-1));
		}
		else
		{
			return linkedToArray(mails.sublist(((page-1)*10), (page*10)-1));
		}
	}

	@Override
	public void deleteEmails(final ILinkedList mails) {
		for(int i=0;i<mails.size();i++)
		{
			IMail mail = (IMail) mails.get(i);
			mailsMani.deleteFromIndex(mail, "accounts/"+current.getName()+"/"+current.getEmail()+"/"+mail.getFolder());
			String mailName=mail.getTime().toString().replaceAll(":", ".");
			String folderName=mail.getFolder();
			mail.setFolder("trash");
			mail.setTrashedtime(LocalDateTime.now());
			mailsMani.sendMail(mail,"accounts/"+current.getName()+"/"+current.getEmail()+"/trash" );
			fm.deleteDirectory(new File( "accounts/"+current.getName()+"/"+current.getEmail()+"/"+folderName+"/"+mailName));



		}

	}

	@Override
	public void moveEmails(final ILinkedList mails, final  IFolder des) {
		for(int i=0;i<mails.size();i++)
		{
			IMail mail = (IMail) mails.get(i);
			mail.setFolder(des.getName());
			mailsMani.sendMail(mail,"accounts/"+current.getName()+"/"+current.getEmail()+"/"+des.getName() );

		}

	}

	@Override
	public boolean compose(final IMail email) {
		email.setFolder("sent");
	    mailsMani.sendMail(email, "accounts/"+validator.validateOnsending(email.getSender())
	    		+"/"+email.getSender()+"/sent");
	    int size = email.getRecivers().size();
	    email.setFolder("inbox");
	    for(int i=0 ;i<size;i++)
	    {
	    	String reciever= email.getRecivers().dequeue().toString();
	    	email.getRecivers().enqueue(reciever);
	    	if(validator.validateOnsending(reciever)!=null)
	    	{
	    	  mailsMani.sendMail(email,"accounts/"+validator.validateOnsending(reciever)+
	    			  "/"+reciever+"/inbox");
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
		return true;
	}
	private ILinkedList arrayToLinked(IMail[] mails)
	{
		ILinkedList list = new MyLinkedList();
		for(int i=0;i<mails.length;i++)
		{
			list.add(mails[i]);
		}
		return list;
	}
	private IMail[] linkedToArray(ILinkedList list)
	{
		IMail[] array =new IMail[list.size()];
		for(int i=0 ;i<array.length;i++)
		{
			array[i]=(IMail) list.get(i);
		}
		return array;
	}
    private IFolder[] getAllFolders()
    {
    	File file = new File ("accounts/"+current.getName()+"/"+current.getEmail());
    	File[] files =file.listFiles();
    	IFolder[] folders = new IFolder[files.length];
    	for(int i=0;i<files.length;i++)
    	{
    		IFolder folder = new IFolder(files[i].getName(), current);
    		folders[i] = folder;
    	}
    	return folders;
    }
    private ILinkedList apeandLists(ILinkedList listA ,ILinkedList listB)
    {
    	ILinkedList list = new MyLinkedList();
    	for(int i=0;i<listA.size();i++)
    	{
    		list.add(listA.get(i));
    	}
    	for(int i =0 ;i<listB.size();i++)
    	{
    		IMail mailB= (IMail) listB.get(i);
    		LocalDateTime timeB =mailB.getTime();
    		int check=0;
    		for(int j=0;j<listA.size();j++)
    		{
    			IMail mailA= (IMail) listA.get(j);
    			LocalDateTime timeA= mailA.getTime();
    		    Duration duration= Duration.between(timeA, timeB);
    		    long diff =duration.toMillis();
    			if(diff==0)
    			{
    				check=1;
    				break;
    			}

    		}
    		if(check==0)
    		{
    			list.add(listB.get(i));
    		}
    	}
    	return list;
    }
    private void checkTrash()
    {
    	IFolder trash = new IFolder("trash",current);
    	IMail[]trashed=trash.getAllMails();
    	for(int i=0;i<trashed.length;i++)
    	{
    		LocalDateTime trasheTime=trashed[i].getTrashedtime();
    		Duration duration= Duration.between(trasheTime, LocalDateTime.now());
    		long diff =duration.toDays();
    		if(Math.abs(diff)>=30)
    		{
    			mailsMani.deleteFromIndex(trashed[i], "accounts/"+current.getName()+"/"+current.getEmail()+"/"+trashed[i].getFolder());
    			String mailName=trashed[i].getTime().toString().replaceAll(":", ".");
    			fm.deleteDirectory(new File( "accounts/"+current.getName()+"/"+current.getEmail()+"/"+trashed[i].getFolder()+"/"+mailName));
    		}
    	}
    }

}
