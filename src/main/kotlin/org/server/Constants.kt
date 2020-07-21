package org.server

enum class Constants(val info: String) {
    PREFIX("/");

    override fun toString(): String {
        return info
    }
}