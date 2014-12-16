/*
 * Copyright (c) 2014 Pantheon Technologies s.r.o. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package sk.spsjm.ptacademy.chat.server;

import java.io.IOException;
import java.util.List;

/**
 * @author michal.polkorab
 *
 */
public final class ClientCommandInterpreter {

    public static void processCommand(String command, List<ClientConnectionData> clientConnectionDataList, ClientConnectionData myClientConnection){
        if (command.equals("/quit")) {
            System.out.println("Ukoncujem spojenie");
            try {
                myClientConnection.getSocket().close();
            } catch (IOException e) {
            }
            for (ClientConnectionData clientConnectionData:clientConnectionDataList){
                if (clientConnectionData.getDataOutputStream().equals(myClientConnection.getDataOutputStream())){
                    clientConnectionDataList.remove(clientConnectionData);
                    break;
                }
            }
        }
        if (command.indexOf(" ")>=0 && command.substring(0, command.indexOf(" ")).equals("/nick")) {
            myClientConnection.setNick(command.substring(command.indexOf(" "), command.length()));
        } 

    }
}
