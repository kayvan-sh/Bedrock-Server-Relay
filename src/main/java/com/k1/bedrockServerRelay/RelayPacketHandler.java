package com.k1.bedrockServerRelay;

import org.cloudburstmc.protocol.bedrock.BedrockSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2169.Bedrock_v2169;
import org.cloudburstmc.protocol.bedrock.codec.v2192.Bedrock_v2192;
import org.cloudburstmc.protocol.bedrock.codec.v291.Bedrock_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.Bedrock_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.Bedrock_v332;
import org.cloudburstmc.protocol.bedrock.codec.v340.Bedrock_v340;
import org.cloudburstmc.protocol.bedrock.codec.v354.Bedrock_v354;
import org.cloudburstmc.protocol.bedrock.codec.v361.Bedrock_v361;
import org.cloudburstmc.protocol.bedrock.codec.v388.Bedrock_v388;
import org.cloudburstmc.protocol.bedrock.codec.v389.Bedrock_v389;
import org.cloudburstmc.protocol.bedrock.codec.v390.Bedrock_v390;
import org.cloudburstmc.protocol.bedrock.codec.v407.Bedrock_v407;
import org.cloudburstmc.protocol.bedrock.codec.v408.Bedrock_v408;
import org.cloudburstmc.protocol.bedrock.codec.v419.Bedrock_v419;
import org.cloudburstmc.protocol.bedrock.codec.v422.Bedrock_v422;
import org.cloudburstmc.protocol.bedrock.codec.v428.Bedrock_v428;
import org.cloudburstmc.protocol.bedrock.codec.v431.Bedrock_v431;
import org.cloudburstmc.protocol.bedrock.codec.v440.Bedrock_v440;
import org.cloudburstmc.protocol.bedrock.codec.v448.Bedrock_v448;
import org.cloudburstmc.protocol.bedrock.codec.v465.Bedrock_v465;
import org.cloudburstmc.protocol.bedrock.codec.v471.Bedrock_v471;
import org.cloudburstmc.protocol.bedrock.codec.v475.Bedrock_v475;
import org.cloudburstmc.protocol.bedrock.codec.v486.Bedrock_v486;
import org.cloudburstmc.protocol.bedrock.codec.v503.Bedrock_v503;
import org.cloudburstmc.protocol.bedrock.codec.v527.Bedrock_v527;
import org.cloudburstmc.protocol.bedrock.codec.v534.Bedrock_v534;
import org.cloudburstmc.protocol.bedrock.codec.v544.Bedrock_v544;
import org.cloudburstmc.protocol.bedrock.codec.v545.Bedrock_v545;
import org.cloudburstmc.protocol.bedrock.codec.v554.Bedrock_v554;
import org.cloudburstmc.protocol.bedrock.codec.v557.Bedrock_v557;
import org.cloudburstmc.protocol.bedrock.codec.v560.Bedrock_v560;
import org.cloudburstmc.protocol.bedrock.codec.v567.Bedrock_v567;
import org.cloudburstmc.protocol.bedrock.codec.v568.Bedrock_v568;
import org.cloudburstmc.protocol.bedrock.codec.v575.Bedrock_v575;
import org.cloudburstmc.protocol.bedrock.codec.v582.Bedrock_v582;
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589;
import org.cloudburstmc.protocol.bedrock.codec.v594.Bedrock_v594;
import org.cloudburstmc.protocol.bedrock.codec.v618.Bedrock_v618;
import org.cloudburstmc.protocol.bedrock.codec.v622.Bedrock_v622;
import org.cloudburstmc.protocol.bedrock.codec.v630.Bedrock_v630;
import org.cloudburstmc.protocol.bedrock.codec.v649.Bedrock_v649;
import org.cloudburstmc.protocol.bedrock.codec.v662.Bedrock_v662;
import org.cloudburstmc.protocol.bedrock.codec.v671.Bedrock_v671;
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685;
import org.cloudburstmc.protocol.bedrock.codec.v686.Bedrock_v686;
import org.cloudburstmc.protocol.bedrock.codec.v712.Bedrock_v712;
import org.cloudburstmc.protocol.bedrock.codec.v729.Bedrock_v729;
import org.cloudburstmc.protocol.bedrock.codec.v748.Bedrock_v748;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;
import org.cloudburstmc.protocol.bedrock.codec.v786.Bedrock_v786;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;
import org.cloudburstmc.protocol.bedrock.codec.v859.Bedrock_v859;
import org.cloudburstmc.protocol.bedrock.codec.v860.Bedrock_v860;
import org.cloudburstmc.protocol.bedrock.codec.v924.Bedrock_v924;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.codec.v975.Bedrock_v975;
import org.cloudburstmc.protocol.bedrock.data.auth.AuthType;
import org.cloudburstmc.protocol.bedrock.data.auth.TokenPayload;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.bedrock.util.JsonUtils;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.jose4j.json.JsonUtil;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.lang.JoseException;

import javax.crypto.SecretKey;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class RelayPacketHandler implements BedrockPacketHandler{

    public boolean CRACKED=true;
    BedrockSession server;
    BedrockSession client;
    BedrockSession se;
    static KeyPair keyPair;
    static byte[] Salt;
    ECPublicKey clientKey;

    main.config config;
    Map<Integer, BedrockCodec> codecs = new HashMap<>();


    String clientJwt;
    JSONObject skinData;
    
    public RelayPacketHandler(main.config conf){
        this.config=conf;

        codecs.put(291, Bedrock_v291.CODEC);
        codecs.put(313, Bedrock_v313.CODEC);
        codecs.put(332, Bedrock_v332.CODEC);
        codecs.put(340, Bedrock_v340.CODEC);
        codecs.put(354, Bedrock_v354.CODEC);
        codecs.put(361, Bedrock_v361.CODEC);
        codecs.put(388, Bedrock_v388.CODEC);
        codecs.put(389, Bedrock_v389.CODEC);
        codecs.put(390, Bedrock_v390.CODEC);
        codecs.put(407, Bedrock_v407.CODEC);
        codecs.put(408, Bedrock_v408.CODEC);
        codecs.put(419, Bedrock_v419.CODEC);
        codecs.put(422, Bedrock_v422.CODEC);
        codecs.put(428, Bedrock_v428.CODEC);
        codecs.put(431, Bedrock_v431.CODEC);
        codecs.put(440, Bedrock_v440.CODEC);
        codecs.put(448, Bedrock_v448.CODEC);
        codecs.put(465, Bedrock_v465.CODEC);
        codecs.put(486, Bedrock_v486.CODEC);
        codecs.put(503, Bedrock_v503.CODEC);
        codecs.put(527, Bedrock_v527.CODEC);
        codecs.put(534, Bedrock_v534.CODEC);
        codecs.put(544, Bedrock_v544.CODEC);
        codecs.put(545, Bedrock_v545.CODEC);
        codecs.put(554, Bedrock_v554.CODEC);
        codecs.put(557, Bedrock_v557.CODEC);
        codecs.put(560, Bedrock_v560.CODEC);
        codecs.put(567, Bedrock_v567.CODEC);
        codecs.put(568, Bedrock_v568.CODEC);
        codecs.put(575, Bedrock_v575.CODEC);
        codecs.put(582, Bedrock_v582.CODEC);
        codecs.put(589, Bedrock_v589.CODEC);
        codecs.put(594, Bedrock_v594.CODEC);
        codecs.put(618, Bedrock_v618.CODEC);
        codecs.put(622, Bedrock_v622.CODEC);
        codecs.put(630, Bedrock_v630.CODEC);
        codecs.put(649, Bedrock_v649.CODEC);
        codecs.put(662, Bedrock_v662.CODEC);
        codecs.put(671, Bedrock_v671.CODEC);
        codecs.put(685, Bedrock_v685.CODEC);
        codecs.put(686, Bedrock_v686.CODEC);
        codecs.put(712, Bedrock_v712.CODEC);
        codecs.put(729, Bedrock_v729.CODEC);
        codecs.put(748, Bedrock_v748.CODEC);
        codecs.put(766, Bedrock_v766.CODEC);
        codecs.put(776, Bedrock_v776.CODEC);
        codecs.put(786, Bedrock_v786.CODEC);
        codecs.put(800, Bedrock_v800.CODEC);
        codecs.put(818, Bedrock_v818.CODEC);
        codecs.put(819, Bedrock_v819.CODEC);
        codecs.put(827, Bedrock_v827.CODEC);
        codecs.put(844, Bedrock_v844.CODEC);
        codecs.put(859, Bedrock_v859.CODEC);
        codecs.put(860, Bedrock_v860.CODEC);
        codecs.put(924, Bedrock_v924.CODEC);
        codecs.put(944, Bedrock_v944.CODEC);
        codecs.put(975, Bedrock_v975.CODEC);
        codecs.put(1001, Bedrock_v1001.CODEC);
        codecs.put(2168, Bedrock_v2168.CODEC);
        codecs.put(2169, Bedrock_v2169.CODEC);
        codecs.put(2192, Bedrock_v2192.CODEC);

    }
    public static final String ALGORITHM_TYPE = AlgorithmIdentifiers.ECDSA_USING_P384_CURVE_AND_SHA384;

    public void setSession(BedrockSession session){
        se=session;
    }

    public void setSessions(BedrockSession s,BedrockSession c){
        server=s;client=c;
    }
    @Override
    public PacketSignal handle(RequestNetworkSettingsPacket packet) {
        System.out.println("RECV : "+packet.getClass().getSimpleName());
        int protocolv = packet.getProtocolVersion();
        if(codecs.containsKey(protocolv)) {
            client.setCodec(codecs.get(protocolv));
            server.setCodec(codecs.get(protocolv));
        }
        else{
            System.out.println("Protocol Version "+protocolv+" not supported. check for updates.");
            client.disconnect();
            server.disconnect();
        }
        //Comes from Client
        client.sendPacketImmediately(packet);
        System.out.println("SENT : "+packet.getClass().getSimpleName());

        //Make keyPair, used further
        keyPair = EncryptionUtils.createKeyPair();
        return PacketSignal.HANDLED;
    }


    ///handlers
    @Override
    public PacketSignal handle(NetworkSettingsPacket packet) {
        System.out.println("RECV : "+packet.getClass().getSimpleName());
        //Comes from Server
        server.sendPacketImmediately(packet);
        System.out.println("SENT : "+packet.getClass().getSimpleName());


        server.getPeer().getChannel().eventLoop().execute(()->{
            server.setCompression(packet.getCompressionAlgorithm());
            client.setCompression(packet.getCompressionAlgorithm());
        });
        

        
        return PacketSignal.HANDLED;
    }

     @Override
    public PacketSignal handle(LoginPacket packet) {
        String authToken = "";
        try{
        if(CRACKED){
            JsonWebSignature jws = new JsonWebSignature();
            jws.setCompactSerialization(((TokenPayload)packet.getAuthPayload()).getToken());
            JSONObject payload = new JSONObject(JsonUtil.parseJson(jws.getUnverifiedPayload()));
            String cpk = (String) payload.get("cpk");
            System.out.println(payload.toString());
            if(cpk==null) throw new IllegalStateException("cpk missing.");
            clientKey = EncryptionUtils.parseKey(cpk);
            authToken = ForgeryUtils.forgeSelfSignedToken(keyPair,payload);

        }else{
            //ChainValidationResult chain = EncryptionUtils.validatePayload(packet.getAuthPayload());
            //clientKey = (ECPublicKey) chain.identityClaims().parsedIdentityPublicKey();
            //authToken = ForgeryUtils.forgeToken(keyPair, this.chain.identityClaims().extraData);
        }

        clientJwt = packet.getClientJwt();
        String clientJwt = packet.getClientJwt();
        verifyJwt(clientJwt, clientKey);
        JsonWebSignature jws = new JsonWebSignature();
        jws.setCompactSerialization(clientJwt);

        skinData = new JSONObject(JsonUtil.parseJson(jws.getUnverifiedPayload()));
        String skin = ForgeryUtils.forgeSkinData(keyPair, this.skinData);

        LoginPacket login = new LoginPacket();
        login.setAuthPayload(new TokenPayload(authToken, AuthType.SELF_SIGNED));
        login.setClientJwt(skin);
        login.setProtocolVersion(packet.getProtocolVersion());
        System.out.println("CLIENT PROTOCOL VERSION : "+packet.getProtocolVersion());

        if(clientKey==null) throw new IllegalStateException("Unable to obtain client's EC public key.");
        client.sendPacketImmediately(login);
        System.out.println("SENT : "+packet.getClass().getSimpleName());

        }catch(Exception e){e.printStackTrace();}
        //Create another login packet WITH our key
        


        
        return PacketSignal.HANDLED;
    }
    
    
    public PacketSignal handle(ServerToClientHandshakePacket packet) {
        System.out.println("RECV : "+packet.getClass().getSimpleName());
        SecretKey key;
        try {
            JsonWebSignature jws = new JsonWebSignature();
            jws.setCompactSerialization(packet.getJwt());
            JSONObject saltJwt = new JSONObject(JsonUtil.parseJson(jws.getUnverifiedPayload()));
            String x5u = jws.getHeader(HeaderParameterNames.X509_URL);
            ECPublicKey serverKey = EncryptionUtils.parseKey(x5u);
            Salt=Base64.getDecoder().decode(JsonUtils.childAsType(saltJwt, "salt", String.class));
            key = EncryptionUtils.getSecretKey(keyPair.getPrivate(), serverKey,Salt);
            client.enableEncryption(key);

        } catch (JoseException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        

        ClientToServerHandshakePacket clientToServerHandshake = new ClientToServerHandshakePacket();
        client.sendPacketImmediately(clientToServerHandshake);
        System.out.println("SENT : "+clientToServerHandshake.getClass().getSimpleName());
        
        return PacketSignal.HANDLED;
    }
    @Override
    public PacketSignal handle(ClientToServerHandshakePacket packet){
        System.out.println("RECV : "+packet.getClass().getSimpleName());
        System.out.println("Wont send it because alredy sent a fake one.");

        return PacketSignal.HANDLED;
    }
    




    public static String createHandshakeJwt(KeyPair serverKeyPair, byte[] token) throws JoseException {
        JsonWebSignature signature = new JsonWebSignature();
        signature.setAlgorithmHeaderValue(ALGORITHM_TYPE);
        signature.setHeader(
                HeaderParameterNames.X509_URL,
                Base64.getEncoder().encodeToString(serverKeyPair.getPublic().getEncoded())
        );
        signature.setKey(serverKeyPair.getPrivate());

        JwtClaims claims = new JwtClaims();
        claims.setClaim("salt", Base64.getEncoder().encodeToString(token));
        signature.setPayload(claims.toJson());

        return signature.getCompactSerialization();
    }

record HandshakeData(ServerToClientHandshakePacket packet,SecretKey secretKey) {}
public static HandshakeData createServerHandshake(ECPublicKey clientIdentityKey)
        throws Exception {

    KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
    generator.initialize(new ECGenParameterSpec("secp384r1"));

    //KeyPair serverKeyPair = generator.generateKeyPair();
    KeyPair serverKeyPair = keyPair;
    //byte[] salt = EncryptionUtils.generateRandomToken();

    SecretKey secretKey = EncryptionUtils.getSecretKey(
            serverKeyPair.getPrivate(),
            clientIdentityKey,
            Salt
    );

    ServerToClientHandshakePacket packet = new ServerToClientHandshakePacket();
    packet.setJwt(createHandshakeJwt(serverKeyPair, Salt));


    return new HandshakeData(packet,secretKey);
}

private static boolean verifyJwt(String jwt, PublicKey key) throws JoseException {
        JsonWebSignature jws = new JsonWebSignature();
        jws.setKey(key);
        jws.setCompactSerialization(jwt);

        return jws.verifySignature();
    }


}