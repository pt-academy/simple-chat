/*
 * Copyright (c) 2014 Pantheon Technologies s.r.o. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package sk.spsjm.ptacademy.chat.util;

/**
 * @author michal.polkorab
 *
 */
public class MessageUtils {

    
    private MessageUtils(){
        throw new IllegalStateException("This class should not be instantiated.");
    }
    
    public static boolean isCommand(String message){
        return message.startsWith("/");        
    }
}
