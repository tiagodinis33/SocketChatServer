package org.server

class CommandHandler {
    val commands = HashMap<String, ICommand>()
    init{
        addCommand(StopCommand())
        addCommand(Login())
        addCommand(Tell())
    }
    fun addCommand(command: ICommand){
        commands[command.invoke] = command
    }
    fun handle(received_command: String, client: Client){
        var command_splitted = received_command.split(" ")
        val command = commands[command_splitted[0].replaceFirst(Constants.PREFIX.info, "")]
        println("${client.clientName} usou o comando $received_command" )
        if(command == null){
            client.sendMessage("Esse commando não existe!!!");
        } else{

            command.run(client, command_splitted.subList(1, command_splitted.size))
        }
    }
}