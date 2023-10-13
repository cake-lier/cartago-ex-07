!receiver.

+!receiver : true <-
    makeArtifact("receiverPort", "io.github.cakelier.Port", [3002], PortId);
    receive(Content, Sender);
    println("Received a message from ", Sender, " which was: ", Content);
    focus(PortId);
    startReceiving.

+new_message(Content, Sender) : true <-
    println("Received a message from ", Sender, " which was: ", Content).

{ include("$jacamoJar/templates/common-cartago.asl") }
