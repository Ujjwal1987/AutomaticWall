package com.example.chase.automaticwall;

import java.net.Socket;

/**
 * Created by chase on 18-02-2017.
 */

public class Socket_handler {

    private static Socket socket;

    public static synchronized Socket getSocket() {
        return socket;
    }

    public static synchronized void setSocket(Socket socket) {
        Socket_handler.socket = socket;
    }
}
