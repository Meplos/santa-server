package santa.server.services

import io.ktor.util.date.*
import org.junit.Test
import santa.server.strategy.IDrawStrategy
import java.util.*

class DrawServiceTest {
    val drawService : IDrawStrategy<String> = NaiveDrawService()

        @Test
    fun draw2inputTest() {
        val list1 = mutableListOf<String>("1", "2");
        for (i in 0..100) {
            val association = drawService.draw(list1);
            for (item in association) {
                assert(item.key !== item.value);
            }
        }
    }

    @Test
    fun drawImpairTest() {
        val list1 = mutableListOf<String>("1", "2", "3");
        for (i in 0..100) {
            val association =drawService.draw(list1);
            for (item in association) {
                assert(item.key !== item.value);
            }
        }
    }

    @Test
    fun drawPairSup2Test() {
        val list1 = mutableListOf<String>("1", "2", "3", "4");
        for (i in 0..100) {
            val association =drawService.draw(list1);
            for (item in association) {
                assert(item.key !== item.value);
            }
        }
    }

    @Test
    fun drawHundredPerfTest() {
        val list1 = LinkedList<String>();
        for (i in 0..1000) {
            list1.push(i.toString())
        }
        for (i in 0..100) {
            val time1 = getTimeMillis()
            val association =drawService.draw(list1);
            val time2 = getTimeMillis()

            assert(time2 - time1 < 50)

            for (item in association) {
                assert(item.key !== item.value);
            }
        }
    }


    @Test
    fun drawHundredThousandPerfTest() {
        val list1 = LinkedList<String>();
        for (i in 0..100000) {
            list1.push(i.toString())
        }
        for (i in 0..100) {
            val time1 = getTimeMillis()
            val association =drawService.draw(list1);
            val time2 = getTimeMillis()

            assert(time2 - time1 < 1000)
            for (item in association) {
                assert(item.key !== item.value);
            }

        }
    }


}