package logic

import com.google.firebase.firestore.DocumentSnapshot
import kotlin.random.Random

abstract class  SortAlgorithm{

    var correctAns: Int = 0

    abstract fun randWords(wordsList: MutableList<DocumentSnapshot>): MutableList<DocumentSnapshot>

    abstract fun generateAns()

    abstract fun checkResult(myAns: Int): Boolean

    @JvmName("getCorrectAns1")
    fun getCorrectAns(): Int{
        return correctAns
    }
}

