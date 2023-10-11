!sender.

+!sender : true <-
    makeArtifact("senderPort", "Port", [3001], _);
    send("Ping!", 3002);
    send("Pong!", 3002).

{ include("$jacamoJar/templates/common-cartago.asl") }
