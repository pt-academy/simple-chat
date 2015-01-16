/*
 * Copyright (c) 2014 Pantheon Technologies s.r.o. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package sk.spsjm.ptacademy.newchat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author michal.polkorab
 *
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String>{

    static final ChannelGroup channels =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("Vitaj na nasom chatovacom serveri!\n");
        channels.add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        for (Channel c : channels) {
            if (c != ctx.channel()) {
                c.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + "\n");
            }
        }
        
        if ("/quit".equals(msg.toLowerCase())) {
            channels.remove(ctx.channel());
            ctx.close();
        }
    }
}
