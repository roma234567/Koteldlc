package com.koteldlc.client.commands.ai

import com.google.gson.JsonObject

class AIModel {
    private var averageDelayMs: Double = 90.0
    private var samples: Int = 0

    fun predictNextDelay(predictionWeight: Double): Double {
        val weight = predictionWeight.coerceIn(0.0, 1.0)
        return averageDelayMs * (1.0 - weight) + 55.0 * weight
    }

    fun learn(observedDelay: Double, learningSpeed: Double) {
        val lr = learningSpeed.coerceIn(0.001, 1.0)
        averageDelayMs += (observedDelay - averageDelayMs) * lr
        samples++
    }

    fun reset() {
        averageDelayMs = 90.0
        samples = 0
    }

    fun stats(): String = "AIModel(avgDelay=${"%.2f".format(averageDelayMs)}ms, samples=$samples)"

    fun save(): JsonObject {
        val obj = JsonObject()
        obj.addProperty("averageDelayMs", averageDelayMs)
        obj.addProperty("samples", samples)
        return obj
    }

    fun load(json: JsonObject) {
        if (json.has("averageDelayMs")) averageDelayMs = json.get("averageDelayMs").asDouble
        if (json.has("samples")) samples = json.get("samples").asInt
    }
}
