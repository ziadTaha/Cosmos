package eg.edu.alexu.csd.datastructure.mailServer;

import java.time.LocalDateTime;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50.MyLinkedList;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

public class IMail {
    private String subject;
    private String sender;
    private IQueue recivers;
    private String text;
    private boolean starred;
    private ILinkedList attachmets;
    private LocalDateTime time;
    private LocalDateTime trashedtime;
    private int priority;
    private String folder;
    public IMail() {
    	 attachmets= new MyLinkedList();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public IQueue getRecivers() {
		return recivers;
	}
	public void setRecivers(IQueue recivers) {
		this.recivers = recivers;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isStarred() {
		return starred;
	}
	public void setStarred(boolean starred) {
		this.starred = starred;
	}
	public ILinkedList getAttachmets() {
		return attachmets;
	}
	public void setAttachmets(ILinkedList attachmets) {
		this.attachmets = attachmets;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public LocalDateTime getTrashedtime() {
		return trashedtime;
	}
	public void setTrashedtime(LocalDateTime trashedtime) {
		this.trashedtime = trashedtime;
	}
}
