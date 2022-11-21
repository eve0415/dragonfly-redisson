import org.redisson.Redisson
import org.redisson.config.Config
import kotlin.system.exitProcess

fun main() {
    val client = Redisson.create(
        Config().apply {
            useSingleServer().address = "redis://localhost:6379"
            isUseScriptCache = true
        }
    )

    val follow = client.getSet<String>("follow")

    // Make sure it is in clean state
    follow.clear()

    follow.addAll(listOf(
        "123456789:123456789",
        "123456789:987654321",
        "987654321:123456789",
        "987654321:987654321",
        "1234567890:1234567890",
        "1234567890:9876543210",
        "9876543210:1234567890",
        "9876543210:9876543210",
    ))

    println(follow.count { it.startsWith("123456789") }) // Expect: 4

    client.shutdown()
    exitProcess(0)
}
