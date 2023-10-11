# JaCaMo by exercises — Exercise 07 — Await with blocking commands: Implementing artifacts for I/O

In this exercise,
you'll see two agents communicating by means of two artifacts that function as network ports based on UDP sockets.
These "port" artifacts expose a "send"
and "receive" operation,
the first to send a message through the port and the second to wait until a message is received on the socket,
blocking the execution.
Another operation that the artifact exposes is the "startReceiving" one,
which allows to be notified as soon as a new message has been received, if an agent decides to focus the artifact.
There's also the "stopReceiving" operation,
to stop being notified, and the internal operation "receiving"
to be used for continually checking if new messages have arrived.
The difference with the previous exercise is that the artifact is not "await"-ing for some time,
but rather for the completion of an operation.
It exists a particular method for doing so, which receives an object which must implement the "IBlockingCmd" interface.
You're asked also to implement a class deriving from the interface representing (and containing) the "receive"
operation made by the socket.
An already implemented class is given to wrap all the behavior needed to work with a UDP socket.

The agents in this exercise are a "sender" one,
sending two messages to another, and a "receiver" one, waiting for those messages.
The first message is received through the "receive" operation, the second as a signal from the "port" artifact.

## Solution

All solution files are marked with the "solution" suffix, don't open them before solving the exercise!
