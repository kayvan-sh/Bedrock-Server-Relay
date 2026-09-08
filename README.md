###Bedrock Server Relay

An application that allows Minecraft Bedrock clients to join cracked servers without having Xbox account. 

You can use Bedrock Server Relay to join servers that allow non-authenticated clients, but client's restrictions doesn't allow to add and join servers without Xbox account.

##How to use
#Android App
This app is available on android. For android clients. 
#JAR file
You can download .jar and execute with:
`java -jar bedrockserverrelay.jar <server ip>`

##How it works
This project uses CloudburstMC protocl libraries and transmits packets. It shows a fake LAN game in worlds tab (friends tab on older versions), it sends packets through the LAN world to the real server, without needing to use servers tabs which requires Xbox authentication. 