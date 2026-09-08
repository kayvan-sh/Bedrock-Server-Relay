# BedrockServerRelay

**BedrockServerRelay** is a relay application that allows Minecraft Bedrock clients to connect to servers that permit non-authenticated clients, even when the Bedrock client's server browser requires an Xbox account.

The relay works by exposing the target server as a local/LAN game. The Bedrock client connects to the relay through its LAN-world interface, while the relay forwards the network packets between the client and the actual server.

Download Jar file or Android App from [Release page](/releases)
## ✨ Features

- Connect to Bedrock servers through the client's LAN-world interface
- No Xbox account required for the client-side server connection
- Android application for Android Bedrock clients
- Standalone JAR version for desktop environments
- Packet forwarding using CloudburstMC protocol libraries

## 📱 Android

BedrockServerRelay includes an Android application designed for Android Minecraft Bedrock clients.

The application allows you to enter a server address and expose it to Minecraft as a local/LAN game.

«Status: Experimental — the Android implementation is working, but further testing is still required.»

## ☕ JAR

A standalone JAR version is also available.

Run it with:

`java -jar bedrockserverrelay.jar <server-ip>`

The relay then exposes the specified server through the LAN interface.

## ⚙️ How It Works

Minecraft Bedrock normally provides access to external servers through its server interface, which can require Xbox authentication.

BedrockServerRelay uses a different path:

```
Minecraft Bedrock Client
        │
        │ LAN connection
        ▼
BedrockServerRelay
        │
        │ Packet forwarding
        ▼
   Real Bedrock Server
```

The relay uses CloudburstMC protocol libraries to receive and transmit Bedrock network packets.

From Minecraft's perspective, the relay appears as a local/LAN game. The relay then forwards the relevant packets to the configured remote server.

This avoids relying on the client's authenticated external-server browser.

## 🛠️ Technologies

- Java
- Android
- [CloudburstMC Protocol](https://github.com/CloudburstMC/protocol)
- Minecraft Bedrock network protocol

## 🚧 Current Status

**BedrockServerRelay** is currently an experimental project.

The core relay functionality works, and the Android implementation has successfully been integrated with the relay. More extensive testing and edge-case handling are still planned.

## 📌 Why I Built This

Minecraft Bedrock's client-side restrictions can make connecting to certain community servers difficult when an Xbox account isn't available.

This project explores an alternative connection path by making the remote server appear as a LAN game and forwarding the underlying Bedrock protocol traffic.

## 📄 License
[MIT License](/LICENSE)