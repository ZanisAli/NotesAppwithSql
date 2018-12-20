package startup.softflix.com.startup

/**
 * Created by Zanis on 12/20/2018.
 */

class Note{
    var noteID:Int?=null
    var noteName:String?=null
    var noteDes:String?=null


    constructor(noteID:Int,noteName:String,noteDes:String)
    {
        this.noteID=noteID
        this.noteName=noteName
        this.noteDes=noteDes
    }
}