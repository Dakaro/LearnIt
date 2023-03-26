package logic

import com.example.learnit.myAns
import com.google.firebase.firestore.DocumentSnapshot
import kotlin.random.Random

var correctAns: Int = 0

fun checkResult(myAns: Int): Boolean{
    if( myAns == correctAns ){
        return true
    }
    return false
}

fun randWords( wordsList: MutableList<DocumentSnapshot>): MutableList<DocumentSnapshot> {
    myAns = -1
    generateAns()
    wordsList.shuffle()

    val result: MutableList<DocumentSnapshot> = arrayListOf(
        wordsList[0], wordsList[1], wordsList[2], wordsList[3]
    )

    return result
}

fun generateAns(){
    correctAns = Random.nextInt(4)
}