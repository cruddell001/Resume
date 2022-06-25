package com.ruddell.resume.extensions

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val jsonSerializer = Json { ignoreUnknownKeys = true; isLenient = true }
val jsonDeserializer = Json { ignoreUnknownKeys = true; isLenient = true }

inline fun <reified T>T.toJson(): String = jsonSerializer.encodeToString(this)
inline fun <reified T>String.fromJson(): T? = try { jsonDeserializer.decodeFromString(this) } catch (e: Exception) { e.printStackTrace(); null }
