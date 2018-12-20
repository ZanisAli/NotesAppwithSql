package startup.softflix.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listNotes= ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add dummy data in listNotes
        listNotes.add(Note(1," meet professor","Create any pattern of your own - tiles, texture, skin, wallpaper, comic effect, website background and more.  Change any artwork of pattern you found into different flavors and call them your own."))
        listNotes.add(Note(2," meet doctor","Create any pattern of your own - tiles, texture, skin, wallpaper, comic effect, website background and more.  Change any artwork of pattern you found into different flavors and call them your own."))
        listNotes.add(Note(3," meet friend","Create any pattern of your own - tiles, texture, skin, wallpaper, comic effect, website background and more.  Change any artwork of pattern you found into different flavors and call them your own."))


        var myNotesAdapter=MyNotesAdapter(listNotes )
        //lvNotes is the id to main activity list view to show tickets there so have to set adapter to that activity
        lvNotes.adapter=myNotesAdapter

    }

    inner class MyNotesAdapter:BaseAdapter{
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //to connect between code and layout, we need our view and it is then inflator, ticket to show in main activity, this method will run =size of list
            var myView=layoutInflater.inflate(R.layout.ticket, null)
            var myNote=listNotesAdapter[position]
            //showing ticket elements here from layout and putting des of listNotesAdapter data
            myView.tvTitle.text= myNote.noteName
            myView.tvDes.text=myNote.noteDes

            return myView
        }

        override fun getItem(position: Int): Any {
            return listNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }

        //super is for calling baseadapter constructor
        var listNotesAdapter= ArrayList<Note>()
        constructor(listNotes:ArrayList<Note>):super(){
            this.listNotesAdapter=listNotes
        }
    }
}
