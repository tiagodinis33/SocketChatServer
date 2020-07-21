package org.server

class Login: ICommand {
    override val invoke: String
        get() = "login"

    override fun run(client: Client, args: List<String>) {
        if(args.isEmpty()){
            client.sendMessage("Digite seu nome de usuario!!!")
            return
        }
        if(!client.isGuest){
            client.sendMessage("Você já esta logado!!!")
            return
        }
        for(cclient in Client.connectedClients){
            if(cclient.clientName == args[0]){
                client.sendMessage("Já existe um usuario logado com esse nick!!!")
                return
            }
        }
        client.clientName = args[0]
        for(cclient in Client.connectedClients){
            cclient.sendMessage("Client com o ip ${cclient.client.remoteSocketAddress} logou como ${client.clientName}")
        }
    }

}