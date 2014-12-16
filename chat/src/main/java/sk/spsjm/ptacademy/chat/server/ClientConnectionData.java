/*
 * Copyright (c) 2014 Pantheon Technologies s.r.o. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package sk.spsjm.ptacademy.chat.server;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author michal.polkorab
 *
 */
public class ClientConnectionData {

    private DataOutputStream dataOutputStream;
    private String nick;
    private Socket socket;

    public ClientConnectionData(DataOutputStream dataOutputStream, Socket socket){
        if (dataOutputStream == null){
            throw new IllegalStateException("Client's data output stream can't be null.");
        }
        this.dataOutputStream = dataOutputStream;
        this.socket = socket;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }
    
    
}