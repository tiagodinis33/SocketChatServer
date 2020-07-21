package org.server

import kotlin.system.exitProcess


class StopCommand: ICommand {
    override val invoke: String
        get() = "stop"

    override fun run(client: Client, args: List<String>) {
        for(clients in Client.connectedClients){

            clients.client.close()
            Client.connectedClients.clear()
        }
        client.server.close()
        exitProcess(0)
    }
}