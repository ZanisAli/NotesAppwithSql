package startup.softflix.com.startup

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
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

    //this method will be called automatically like oncreate(above) method when activity fire up because of override of above oncreate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //now have to show my menu created on main activiy so inflate that menu
        menuInflater.inflate(R.menu.main_menu, menu)
        //now for search view
        val sv= menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        //search manager
        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        //defining search view as searchable component
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                //todo search database
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    //THIS FUN is for when person click add icon on main activity it should go to add notes activity
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId) {
            //addNote is id for add note icon on main activity
                R.id.addNote->{
                    //goto add page
                    //intent is for interprocess comm. to send and receive data from other activites
                    //intent takes two param, "this" means this activity and addnotes to which you want to go
                    var intent=Intent(this, AddNotes::class.java)
                    //then have to start that activity
                    startActivity(intent)//pass this intent to Addnotes activity and send intent to that activity

                }
            }
        }
        return super.onOptionsItemSelected(item)
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
