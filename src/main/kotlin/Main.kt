import org.redisson.Redisson
import org.redisson.client.codec.Codec
import org.redisson.codec.Kryo5Codec
import org.redisson.config.Config
import java.util.BitSet
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

fun main() {
    val client = Redisson.create(
        Config().apply {
            useSingleServer().address = "redis://localhost:6379"
            isUseScriptCache = true
        }
    )

    val tempCache = client.getMapCache<String, String>("map:test")
    tempCache.put("test1", "hello", 1, TimeUnit.MINUTES)

    // should be -2, -1 or ttl
    // but cannot serialize error (maybe the script not returning Long?)
    println(tempCache.remainTimeToLive("test1"))

    client.shutdown()
    exitProcess(0)
}
