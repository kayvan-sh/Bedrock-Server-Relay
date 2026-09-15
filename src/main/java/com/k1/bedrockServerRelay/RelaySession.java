package com.k1.bedrockServerRelay;



import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.BedrockSession;
import org.cloudburstmc.protocol.bedrock.netty.BedrockPacketWrapper;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.UnknownPacket;
import org.cloudburstmc.protocol.common.PacketSignal;
import java.util.List;

public class RelaySession extends BedrockServerSession {

    
    private BedrockSession sendSession;
    private String type;
    


    public RelaySession(BedrockPeer peer, int subClientId) {
        super(peer, subClientId);

    }

    public void setOtherSession(BedrockSession session,String type){
        sendSession=session;this.type=type;
    }
    @Override
    protected void onPacket(BedrockPacketWrapper wrapper) {
        
        BedrockPacket packet = wrapper.getPacket();
        System.out.println(type+": Packet received - "+packet.getClass().getSimpleName());
       
        if (this.packetHandler == null) {
        } else if (this.packetHandler.handlePacket(packet) == PacketSignal.UNHANDLED && this.sendSession != null) {

            ByteBuf buffer = wrapper.getPacketBuffer()
                    .retainedSlice()
                    .skipBytes(wrapper.getHeaderLength());

            UnknownPacket sendPacket = new UnknownPacket();
            sendPacket.setPayload(buffer);
            sendPacket.setPacketId(wrapper.getPacketId());
            this.sendSession.sendPacket(sendPacket);
            System.out.println(type+": "+sendPacket.getClass().getSimpleName()+" Sent!");
        }
    }

    @Override
    public void disconnect(CharSequence reason, boolean hideReason) {
        super.disconnect(reason, hideReason);
        sendSession.disconnect();
    }
}