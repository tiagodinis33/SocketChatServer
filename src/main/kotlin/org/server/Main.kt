package org.server
import java.io.OutputStream
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Main {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val socket = ServerSocket( 25565)
            var client: Socket
            while(!Thread.interrupted()){
                client = socket.accept()
                Client(client, socket)
            }
        }
    }
}
fun OutputStream.printWriter(): PrintWriter {
    return PrintWriter(this, true)
}