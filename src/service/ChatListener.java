package service;

import controller.ChatController;
import Entity.Message;
import Entity.User;

import java.io.*;
import java.util.HashMap;
import ca.weblite.codename1.net.Socket;


public class ChatListener implements Runnable{

    private static String hostname;
    private static int port;
    private static final HashMap<User,ChatController> chatListcontrollers = new HashMap<>();
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static User user;
    private final MessageService messageService = MessageService.getInstance();
    public ChatListener(String hostname, int port,User user) {
        com.codename1.io.Util.register("User", User.class);
        com.codename1.io.Util.register("Message", Message.class);
        ChatListener.hostname = hostname;
        ChatListener.port = port;
        ChatListener.user = user;
        this.user.setSalt("M");
    }

    public static void addController(ChatController controller)
    {
        chatListcontrollers.put(controller.getChatUser(), controller);
    }
    
    public static void removeController(ChatController controller)
    {
        chatListcontrollers.remove(controller.getChatUser());
    }
    
    
    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            send(user);
            while (socket.isConnected()) {
                try {
                    Message message = null;
                    message = (Message) com.codename1.io.Util.readObject(inputStream);
                    if(message != null)
                    {
                        ChatController controller = chatListcontrollers.get(message.getSender());
                        if(controller != null)
                        {
                            System.out.println(controller.getChatUser());
                            controller.addToChat(message);
                        }
                        else
                            messageService.createMessageNotification(message);
                    }
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
        }

    }

    /* This method is used for sending a normal Message
     * @param msg - The message which the user generates
     */
    public static void send(Object obj) {
        try {
            com.codename1.io.Util.writeObject(obj, outputStream);
            outputStream.flush();
        } catch (IOException ex) {
        }
    }
}
