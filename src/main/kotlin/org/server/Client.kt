package org.server

import com.google.gson.Gson
import java.net.ServerSocket
import java.net.Socket
import java.util.*

class Client(val client: Socket, val server: ServerSocket): Thread() {
    var clientName = "guest-${Random().nextInt(9999)}"
    set(value) {
        isGuest = false
        field = value
    }
    var isGuest = true
    private set
    companion object{
        val connectedClients = arrayListOf<Client>()
        private val checkClosedThread = Thread{
            while (!interrupted()) {
                for (client in connectedClients){
                    if (client.client.isConnected) {
                        client.client.close()
                        connectedClients.remove(client)
                        return@Thread
                    }
                }
            }
        }

    }

    init {
        connectedClients.add(this)
        if(!checkClosedThread.isAlive && checkClosedThread.state != State.TERMINATED){
            checkClosedThread.start()
        }
        start()
        for (cclient in connectedClients){
            sendMessage("O client com o ip ${client.remoteSocketAddress} entrou!!")
        }
    }
    fun tellTo(client: Client, msg: String){
        this.client.getOutputStream().printWriter().println("Você sussurrou para ${client.clientName}: $msg")
        client.client.getOutputStream().printWriter().println("$clientName sussurrou para você: $msg")
    }
    fun sendMessage(msg: String){

        client.getOutputStream().printWriter().println("Server disse: $msg")
    }
    fun sendMessageUser(msg: String, from: String){
        client.getOutputStream().printWriter().println("$from disse: $msg")
    }
    override fun run() {
        while (!this.isInterrupted){
            val scanner = Scanner(client.getInputStream())
            val received_msg = scanner.nextLine()
            if(!received_msg.startsWith(Constants.PREFIX.info, true)){
                for(client in connectedClients){
                    client.sendMessageUser(received_msg, this.clientName)
                }
            } else{
                val CH = CommandHandler()
                CH.handle(received_msg, this)
            }
        }
    }
}