package com.koteldlc.client.commands

abstract class Command(
    val name: String,
    val usage: String,
    val description: String
) {
    abstract fun execute(args: List<String>): String
}
