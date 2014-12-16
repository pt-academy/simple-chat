/*
 * Copyright (c) 2014 Pantheon Technologies s.r.o. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package sk.spsjm.ptacademy.chat.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import sk.spsjm.ptacademy.chat.util.MessageUtils;


/**
 * @author michal.polkorab
 *
 */
public class ServerConnection extends Thread {

    private Socket socket;
    private List<ClientConnectionData> clientConnectionDataList = new ArrayList<>();
    private BufferedReader input;
    private ClientConnectionData myClientData;

    public ServerConnection(Socket socket, List<ClientConnectionData> clientData) {
       try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            myClientData = new ClientConnectionData(new DataOutputStream(socket.getOutputStream()), socket);
            clientData.add(myClientData);
            this.clientConnectionDataList = clientData;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // citaj data kym je socket otvoreny
        while (! myClientData.getSocket().isClosed()) {
            String message;
            try {
                // skontroluj ci nieco caka na vstupe
                if (input.ready()) {
                    // precitaj spravu
                    message = input.readLine();
                    if (MessageUtils.isCommand(message)){
                        ClientCommandInterpreter.processCommand(message, clientConnectionDataList, myClientData);
                    } else {
                        ClientMessageForwarder.forwardMessage(message, clientConnectionDataList, myClientData);
                    }
                }
                // uvolni thread pre dalsie aplikacie
                sleep(100);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
