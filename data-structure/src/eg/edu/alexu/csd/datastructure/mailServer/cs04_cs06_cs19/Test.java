package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.queue.IQueue;
import eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19.MyLinkedBased;

/**
 *
 * @author Z.A.Z
 *
 */
public class Test {
	/**
	 *
	 * @param args
	 * main para
	 */
	public static void main(final String[] args) {
		/*MypriorityQueue o = new MypriorityQueue();

		// o.insert(5, 2);
		o.insert(6, 100);
		/*
		 * o.insert(10, 6); o.insert(2,2);
		 */
		// o.insert(9, 2); o.insert('a', 1);
		//o.insert(9, 2); o.insert(5, 4);
		// o.removeMin(); o.removeMin(); o.removeMin();
		//o.removeMin(); o.removeMin();
		// o.removeMin();
		// int x = o.size();

		// o.insert(0, 1);
		/*
		 * o.insert(64, 3); o.insert(5, 2);
		o.insert(1, 1);
		o.insert(5, 5);
		for (int i = 1; i < 100; i++) {
			o.insert(i, i);
		}
		System.out.println(o.removeMin());
		System.out.println(o.removeMin());
		int x = o.size();
		for (int i = 1; i < x; i++) {
			System.out.println(o.removeMin());
		}
		// System.out.println(o.removeMin());
		// System.out.println(o.removeMin());
		// System.out.println(o.size);*/
		MyApp app = new MyApp();
		IContact contact = new IContact("Ziad", "izo", "sas1");
		IContact contact1 = new IContact("Ziad", "zizo", "1457");
		IContact contact2 = new IContact("Ahmed", "7amo", "sa2de");
		app.signup(contact);
		app.signup(contact1);
		app.signup(contact2);
		app.signin("zizo", "1457");
		IMail mail = new IMail();
		mail.setSender("zizo");
		IQueue rec = new MyLinkedBased();
		rec.enqueue("7amo");
		rec.enqueue("izo");
		mail.setRecivers(rec);
		mail.setSubject("first test");
		mail.setText("this is the message ");
		mail.setPriority(1);
		mail.setTime(LocalDateTime.now());
		/*ILinkedList attach = new MyLinkedList();
		attach.add("E:\\college\\2nd_semster\\ds\\"
				+ "bagoush.jpg");
		mail.setAttachmets(attach);*/

		app.compose(mail);
		mail.setFolder("sent");
		ILinkedList mails= new MyLinkedList();
		mails.add(mail);

		app.deleteEmails(mails);
		System.out.println(LocalDateTime.now().toString());
		System.out.println(ZonedDateTime.now());


	/*	//fm.copyFiles("E:\\college\\2nd semster\\ds\\bagoush.jpg", "E:\\b.jpg");
		try {
			Desktop.getDesktop().open(new File("E:\\college\\2nd_semster\\ds\\bagoush.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
