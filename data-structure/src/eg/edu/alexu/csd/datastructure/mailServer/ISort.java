package eg.edu.alexu.csd.datastructure.mailServer;

import java.time.Duration;
import java.time.LocalDateTime;

import eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19.MypriorityQueue;
import eg.edu.alexu.csd.datastructure.stack.IStack;
import eg.edu.alexu.csd.datastructure.stack.cs19.MyStack;

public class ISort {
	private String type;
	public ISort(String type ) {
		this.type=type;
	}
	public void sort (IMail[] mails)
	{
		if(type.equals("priority"))
		{
			mails=sortPriority(mails);
		}
		else
		{
			quickSortStack(mails, 0, mails.length-1, type);
		}
	}
	/**
	 *  swaping two mails
	 * @param a first mail
	 * @param b second mail
	 */
	public void swap(IMail a, IMail b)
	{
		IMail temp =a;
		a=b;
		b=temp;
	}
	private IMail [] sortPriority(IMail[] mails)
	{
		IPriorityQueue p = new MypriorityQueue();
		for(int i=0;i<mails.length;i++)
		{
			p.insert(mails[i], mails[i].getPriority());
		}
		IMail[] mailsSorted = new IMail[p.size()];
		for (int i=p.size()-1 ;i>=0;i--)
		{
			mailsSorted[i]= (IMail) p.removeMin();
		}
		return mailsSorted;
	}
	int partition(IMail[] mails,int l,int h,String topic)
	{
		IMail x= mails[h];
		int i=(l-1);
		for(int j= l;j<h;j++)
		{
			if(!compareMail(mails[j],x,topic))
			{
				i++;
				swap(mails[j],mails[i]);
			}
		}
		swap(mails[i+1],mails[h]);
		return i+1;

	}
	void quickSortStack(IMail mails[],int l,int h,String topic)
	{
		IStack stack = new MyStack();
		int p;
		stack.push(l);
		stack.push(h);
		while(!stack.isEmpty())
		{
			h=(int) stack.pop();
			l=(int) stack.pop();
			p=partition(mails, l, h,topic);
			if(p-1>l)
			{
				stack.push(l);
				stack.push(p-1);
			}
			if(p+1<h)
			{
				stack.push(p+1);
				stack.push(h);
			}
		}
		for(int i=0 ;i<mails.length/2;i++)
		{
			IMail temp = mails[i];
			mails[i]=mails[mails.length-1-i];
			mails[mails.length-1-i]=temp;

		}

	}
	public boolean compareMail(IMail mailA,IMail mailB,String topic)
	{
		if(topic.equals("subject"))
		{
			return compareMailSubject(mailA, mailB);
		}
		else if(topic.equals("sender"))
		{
			return compareMailSender(mailA, mailB);
		}
		else
		{
			return compareMailTime(mailA, mailB);
		}
	}
	public boolean compareMailTime(IMail mailA,IMail mailB)
	{
		LocalDateTime timeA= mailA.getTime();
		LocalDateTime timeB= mailB.getTime();
		Duration duration=Duration.between(timeA, timeB);

		long diff =duration.toMillis();
		if(diff>0)
		{
			return true;

		}
		return false;
	}
	public boolean compareMailSubject(IMail mailA,IMail mailB)
	{
		if(mailA.getSubject().compareTo(mailB.getSubject())>0)
		{
			return true;
		}
		return false;
	}
	public boolean compareMailSender(IMail mailA,IMail mailB)
	{
		if(mailA.getSender().compareTo(mailB.getSender())>0)
		{
			return true;
		}
		return false;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
