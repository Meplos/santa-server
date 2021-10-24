package santa.server.services

import io.ktor.util.date.*
import org.junit.Test
import santa.server.strategy.IDrawStrategy
import java.util.*

class FilteredDrawServiceTest {
    val drawService: IDrawStrategy<String> = FilteredDrawService()

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
            val association = drawService.draw(list1);
            for (item in association) {
                assert(item.key !== item.value);
            }
        }
    }

    @Test
    fun drawPairSup2Test() {
        val list1 = mutableListOf<String>("1", "2", "3", "4");
        for (i in 0..100) {
            val association = drawService.draw(list1);
            for (item in association) {
                assert(item.key !== item.value);
            }
        }
    }

}