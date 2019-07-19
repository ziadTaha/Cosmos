package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.stack.IStack;
import eg.edu.alexu.csd.datastructure.stack.cs19.MyStack;

public class IFilter {
	private String topic;
	private String value;
    public ILinkedList filter (IMail[] mails)
    {
    	if(topic.equals("subject"))
    	{
    		return binarySearchSubject(mails, 0, mails.length-1, value);
    	}
    	else if(topic.equals("sender"))
    	{
    		return binarySearchSender(mails, 0, mails.length-1, value);
    	}
    	else if(topic.equals("priority"))
    	{
    		return binarySearchPriority(mails, 0, mails.length-1, Integer.parseInt(value));
    	}
    	return null;

    }
	public ILinkedList binarySearchSubject(IMail[] mails,int l,int h,String subject)
	{
		ILinkedList mailList=new MyLinkedList();
		IStack s= new MyStack();
		s.push(l);
		s.push(h);
		while(!s.isEmpty())
		{
			h =(int) s.pop();
			l =(int) s.pop();
			int m= (l+h)/2;
			if(mails[m].getSubject().equals(subject))
			{
				while((mails[m-1].getSubject().equals(subject)))
				{
					m--;
				}
				if(!mails[m].getSubject().equals(subject))
				{
					m++;
				}
				while(mails[m].getSubject().equals(subject))
				{
					mailList.add(mails[m]);
					m++;
				}
				break;
			}
			else if(h<l)
			{
				break;
			}
			else if(mails[m].getSubject().compareTo(subject)>0)
			{
				s.push(l);
				s.push(m);
			}
			else if(mails[m].getSubject().compareTo(subject)<0)
			{
				s.push(m);
				s.push(h);
			}

		}
		return mailList;
	}
	public  ILinkedList binarySearchSender(IMail[] mails,int l,int h,String sender)
	{
		ILinkedList mailList=new MyLinkedList();
		IStack s= new MyStack();
		s.push(l);
		s.push(h);
		while(!s.isEmpty())
		{
			h =(int) s.pop();
			l =(int) s.pop();
			int m= (l+h)/2;
			if(mails[m].getSender().equals(sender))
			{
				while((mails[m-1].getSender().equals(sender)))
				{
					m--;
				}
				if(!mails[m].getSender().equals(sender))
				{
					m++;
				}
				while(mails[m].getSender().equals(sender))
				{
					mailList.add(mails[m]);
					m++;
				}
				break;
			}
			else if(h<l)
			{
				break;
			}
			else if(mails[m].getSender().compareTo(sender)>0)
			{
				s.push(l);
				s.push(m);
			}
			else if(mails[m].getSender().compareTo(sender)<0)
			{
				s.push(m);
				s.push(h);
			}

		}
		return mailList;
	}

	public ILinkedList binarySearchPriority(IMail[] mails,int l,int h,int priority)
	{
		ILinkedList mailList=new MyLinkedList();
		IStack s= new MyStack();
		s.push(l);
		s.push(h);
		while(!s.isEmpty())
		{
			h =(int) s.pop();
			l =(int) s.pop();
			int m= (l+h)/2;
			if(mails[m].getPriority()==priority)
			{
				while((m-1>=0&&mails[m-1].getPriority()==priority))
				{
					m--;
				}
				if((m-1>=0&&mails[m-1].getPriority()!=priority))
				{
					m++;
				}
				while((m+1<mails.length&&mails[m+1].getPriority()==priority))
				{
					mailList.add(mails[m]);
					m++;
				}
				break;
			}
			else if(h<l)
			{
				break;
			}
			else if(mails[m].getPriority()>priority)
			{
				s.push(l);
				s.push(m);
			}
			else if(mails[m].getPriority()<priority)
			{
				s.push(m);
				s.push(h);
			}

		}
		return mailList;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
