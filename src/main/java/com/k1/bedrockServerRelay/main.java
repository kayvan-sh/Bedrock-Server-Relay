package com.k1.bedrockServerRelay;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.bootstrap.Bootstrap;

import java.net.InetSocketAddress;

import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockClientInitializer;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockChannelInitializer;

import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.BedrockClientSession;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.codec.v975.Bedrock_v975;
import org.cloudburstmc.protocol.bedrock.codec.compat.BedrockCompat;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;

import io.netty.channel.Channel;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;

import java.util.List;

import com.k1.bedrockServerRelay.RelaySession;

public class main{
    static BedrockServerSession server = null;
    static BedrockClientSession client = null;
    final static BedrockCodec codec_version = BedrockCompat.CODEC;
    static NioEventLoopGroup nelg;

    public static void main(String[] args){
        if (args.length == 0){
            help();
        }else{
            int port;String name;int protocolv;
            if(args.length>1)port = Integer.parseInt(args[1]); else port=19132;
            if(args.length>2)name = args[2]; else name="";
            if(args.length>3)protocolv = Integer.parseInt(args[3]); else protocolv=-1;

            config conf = new config(args[0],port,name);
            start(conf);
        }
    }
    public static void start(config c){
        System.out.println("Bedrock Server relay V0.2 by Kayvan-sh");
        System.out.println("CONFIG:"+c.toString());

        
        nelg=new NioEventLoopGroup();


        //SERVER
        InetSocketAddress bindAddress = new InetSocketAddress("0.0.0.0", 19132);
        BedrockPong pong = new BedrockPong()
                .edition("MCPE")
                .motd("BedrockServerRelay")
                .subMotd(c.serverIp)
                .playerCount(1)
                .maximumPlayerCount(100)
                .gameType("Creative")
                .ipv4Port(19132)
                .serverId(System.currentTimeMillis())
                .version(codec_version.getMinecraftVersion())
                .protocolVersion(codec_version.getProtocolVersion());

       
        new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(NioDatagramChannel.class))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                .group(nelg)
                .childHandler(new BedrockChannelInitializer<RelaySession>() {
                    @Override
                    protected RelaySession createSession0(BedrockPeer peer, int subClientId) {
                        return new RelaySession(peer, subClientId);
                    }

                    @Override
                    protected void preInitChannel(Channel channel) {
                try {
                    int rakVersion = (Integer)channel.config().getOption(RakChannelOption.RAK_PROTOCOL_VERSION);
                    // Force latest if rakVersion is missing
                    if (rakVersion == 0) {
                        rakVersion = 11;
                    }
                    channel.config().setOption(RakChannelOption.RAK_PROTOCOL_VERSION, rakVersion);
                    super.preInitChannel(channel);
                } catch(Exception e) {
                    
                }
                }
                    @Override
                    protected void initSession(RelaySession session) {
                        // Connection established
                        System.out.println("Connection to the server established.");
                        
                        session.setCodec(codec_version);
                        connectClient(session,c);
                    }
                })
                .bind(bindAddress)
                .syncUninterruptibly();
                System.out.println("Server Running on "+ bindAddress);

                
            }

    public static void connectClient(RelaySession se,config conf){
        System.out.println("Connecting to client");
        new Bootstrap()
        .channelFactory(RakChannelFactory.client(NioDatagramChannel.class))
        .group(nelg)
        .handler(new BedrockChannelInitializer<RelaySession>() {
            @Override
            protected RelaySession createSession0(BedrockPeer peer, int subClientId) {
                return new RelaySession(peer, subClientId);
            }

            @Override
            protected void preInitChannel(Channel channel) {
                try {
                    int rakVersion = (Integer)channel.config().getOption(RakChannelOption.RAK_PROTOCOL_VERSION);
                    if (rakVersion == 0) {
                        rakVersion = 11;
                    }
                    channel.config().setOption(RakChannelOption.RAK_PROTOCOL_VERSION, rakVersion);
                    super.preInitChannel(channel);
                } catch(Exception e) {
                    
                }
                }
             
            @Override
            protected void initSession(RelaySession session) {
                // Connection established
                session.setCodec(codec_version);
                RelayPacketHandler rph = new RelayPacketHandler(conf);
                rph.setSessions(se,session);
                session.setPacketHandler(rph);
                session.setOtherSession(se,"Client");

                se.setPacketHandler(rph);
                se.setOtherSession(session,"Server");

                System.out.println("Connected to client.");
            }
        })
        .connect(new InetSocketAddress(conf.serverIp, conf.serverPort))
        .syncUninterruptibly();
    }


public record config(String serverIp,int serverPort,String name){}

static private void help(){
    System.out.println("Usage:");
    System.out.println("java -jar BedrockServerRelay.jar <server ip>");
    System.out.println("Optional args:");
    System.out.println("java -jar BedrockServerRelay.jar <server ip> <server port> <display name>");
}
} 