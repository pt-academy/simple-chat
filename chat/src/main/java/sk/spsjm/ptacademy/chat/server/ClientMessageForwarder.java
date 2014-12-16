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
public final class ClientMessageForwarder {

    private ClientMessageForwarder(){
        throw new IllegalStateException("This is utility class.");
    }
    
    public static void forwardMessage(String message, List<ClientConnectionData> clientConnectionDataList, ClientConnectionData clientData) throws IOException {
        String nickName = clientData.getNick() ;
        if (nickName == null || nickName.equals("")){
            nickName = "anonymous";
        }
        for (ClientConnectionData clientConnectionData : clientConnectionDataList) {                            
            clientConnectionData.getDataOutputStream().writeBytes(nickName +":"+ message + "\n");
        }
    }

}
