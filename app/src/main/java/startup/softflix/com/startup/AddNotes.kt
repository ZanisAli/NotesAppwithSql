package startup.softflix.com.startup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import startup.softflix.com.startup.R

class AddNotes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    //when button will be clicked it will close the activity
    fun buAdd(view: View){

        /*
        //going back to main activity on button click
        var intent= Intent(this, MainActivity::class.java)
        startActivity(intent)//pass this intent to Addnotes activity and send intent to that activity
        */
        //don't do like above that makes copy and will need memory so finish the activity always
        finish()
    }
}
