package org.server

interface ICommand {
    val invoke: String
    fun run(client: Client, args: List<String>)
}