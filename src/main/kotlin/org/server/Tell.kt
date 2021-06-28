package org.server

class Tell: ICommand {
    override val invoke: String
        get() = "tell"

    override fun run(client: Client, args: List<String>) {
        if(args.size < 2){
            client.sendMessage("ERRO SINTAXE!!! Utilize: /tell <user> <mensagem>")
            return
        }

        for(cclient in Client.connectedClients){
            if(cclient.clientName == args[0]){
                client.tellTo(cclient, args.subList(1,args.size).joinToString(" "))
                break
            }else if (client.clientName == args[0]){
                client.sendMessage("Você deve ser solitario, está falando com você proprio, tente achar um especialista para tratar do caso!")
            }
        }
    }

}
