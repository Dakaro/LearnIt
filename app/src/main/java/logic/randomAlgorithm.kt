package logic

import com.google.firebase.firestore.DocumentSnapshot
import kotlin.random.Random

class randomSort:  SortAlgorithm(){

    override fun randWords(wordsList: MutableList<DocumentSnapshot>): MutableList<DocumentSnapshot> {
        generateAns()
        wordsList.shuffle()

        val result: MutableList<DocumentSnapshot> = arrayListOf(
            wordsList[0], wordsList[1], wordsList[2], wordsList[3]
        )

        return result
    }

    override fun generateAns() {
        correctAns = Random.nextInt(4)
    }

    override fun checkResult(myAns: Int): Boolean {
        if( myAns == getCorrectAns() ){
            return true
        }
        return false
    }

}
