package com.krawa.sharedelementfragmenttransition;

import java.util.ArrayList;
import java.util.Collection;

public class DialogItem {

    private int photo;
    private String title;
    private String message;
    private long date;

    private String did;
    private Collection<User> members;
    private String nameChat;
    private boolean isGroupChat;
    private int unread;
    private Message lastMessage;

    public DialogItem (String did, int photo, String title, String message, long date){
        this.did = did;
        this.photo = photo;
        this.title = title;
        this.message = message;
        this.date = date;
    }

    public static ArrayList<DialogItem> getItems(){
        ArrayList<DialogItem> list = new ArrayList<>();
        list.add(new DialogItem("d3453453",
                R.drawable.ava1,
                "Crazy Smile",
                "Hello!",
                1435245633));
        list.add(new DialogItem("d5755453",
                R.drawable.ava2,
                "Cris Rockwool",
                "How are you?",
                1435236933));
        list.add(new DialogItem("d7686676",
                R.drawable.ava3,
                "Green Marsian",
                "I'll be coming for YOU!!",
                1435103733));
        list.add(new DialogItem("d7864564",
                R.drawable.ava4,
                "Morpheux",
                "Follow the white penguin",
                1435303593));
        list.add(new DialogItem("d1213234",
                R.drawable.ava5,
                "Chosen One",
                "Blast after 2 hours!",
                1435326513));

        return list;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Collection<User> getMembers() {
        return members;
    }

    public void setMembers(Collection<User> members) {
        this.members = members;
    }

    public String getNameChat() {
        return nameChat;
    }

    public void setNameChat(String nameChat) {
        this.nameChat = nameChat;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setIsGroupChat(boolean isGroupChat) {
        this.isGroupChat = isGroupChat;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
